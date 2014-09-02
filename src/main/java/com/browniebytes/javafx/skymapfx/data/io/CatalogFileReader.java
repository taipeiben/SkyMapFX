package com.browniebytes.javafx.skymapfx.data.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.browniebytes.javafx.skymapfx.ApplicationSettings;
import com.browniebytes.javafx.skymapfx.ApplicationSettings.Settings;
import com.browniebytes.javafx.skymapfx.data.entities.Star;
import com.browniebytes.javafx.skymapfx.exceptions.FatalRuntimeException;
import com.google.inject.Inject;

/**
 * Reads zip file and stores data using JDBC, since using hibernate to populate
 * the entire database on startup was too slow.  Used Spring's JDBC template
 * for easy JDBC.
 */
public class CatalogFileReader {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogFileReader.class);

	/**
	 * Application settings
	 */
	private final ApplicationSettings applicationSettings;

	/**
	 * Hibernate session factory to get the DataSource from for Spring's JdbcTemplate
	 */
	private final SessionFactory sessionFactory;

	@Inject
	public CatalogFileReader(
			final ApplicationSettings applicationSettings,
			final SessionFactory sessionFactory) {

		this.applicationSettings = applicationSettings;
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Builds the star database using Spring's JdbcTemplate
	 */
	public void buildStarDatabase() {
		// Get the datasource from Hibernate
		final DataSource dataSource = SessionFactoryUtils.getDataSource(sessionFactory);

		// Setup DB objects
		final JdbcTemplate jt = new JdbcTemplate(dataSource);
		final PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		final TransactionDefinition def = new DefaultTransactionDefinition();
		final TransactionStatus status = transactionManager.getTransaction(def);

		// Create input stream from zip file "hip.data"
		try (final InputStream is = CatalogFileReader.class.getResourceAsStream("/hip.data")) {
			final ZipInputStream zis = new ZipInputStream(is);
			zis.getNextEntry();

			// Each record is 60 bytes
			final ByteBuffer buffer = ByteBuffer.allocateDirect(60);
			// Use NIO's ReadableByteChannel to pull data from zip stream
			final ReadableByteChannel channel = Channels.newChannel(zis);

			// Store stars in temp array to batch insert
			final List<Star> starList = new ArrayList<Star>();

			// Filter by magnitude to filter out the dimmest stars
			final double magLimit = applicationSettings.getSettingAsDouble(Settings.MIN_MAGNITUDE);
			int filterCount = 0;

			// Grab timestamp for debug message
			final long startTime = System.currentTimeMillis();

			LOGGER.debug("Reading hip.data ...");

			// Read until end of channel
			while (channel.read(buffer) != -1) {
				final Star star = new Star();
				buffer.flip();
				star.setCatalogNumber(buffer.getLong());
				star.setRa(buffer.getDouble());
				star.setDec(buffer.getDouble());
				star.setRaPm(buffer.getDouble());
				star.setDecPm(buffer.getDouble());
				star.setMagnitude(buffer.getDouble());

				// Skip further processing for stars dimmer than star limit
				if (star.getMagnitude() > magLimit) {
					buffer.clear();
					filterCount++;
					continue;
				}

				// Build spectral type
				final StringBuilder sb = new StringBuilder();
				for (int i = 0; i < 12; i++) {
					sb.append((char) buffer.get());
				}
				star.setSpectralType(sb.toString());

				// Add star to starList for batch insert later
				starList.add(star);

				// Clear buffer for next iteration
				buffer.clear();
			}

			// Batch JDBC insert using JdbcTemplate from Spring
			jt.batchUpdate(
					"insert into STARS (catalogNumber, ra, dec, raPm, decPm, magnitude, spectralType) values (?, ?, ?, ?, ?, ?, ?)",
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							final Star s = starList.get(i);
							ps.setLong(1, s.getCatalogNumber());
							ps.setDouble(2, s.getRa());
							ps.setDouble(3, s.getDec());
							ps.setDouble(4, s.getRaPm());
							ps.setDouble(5, s.getDecPm());
							ps.setDouble(6, s.getMagnitude());
							ps.setString(7, s.getSpectralType());
						}

						@Override
						public int getBatchSize() {
							return starList.size();
						}
					});

			LOGGER.debug("Committing ...");
			transactionManager.commit(status);

			LOGGER.debug(
					String.format(
							"Star data load complete in %d ms, %d stars filtered by magnitude",
							System.currentTimeMillis() - startTime,
							filterCount));
		} catch (IOException ex) {
			throw new FatalRuntimeException("Error encountered when trying to build star database", ex);
		}
	}

}
