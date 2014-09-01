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

import com.browniebytes.javafx.skymapfx.data.entities.Star;
import com.browniebytes.javafx.skymapfx.exceptions.FatalRuntimeException;
import com.google.inject.Inject;

public class CatalogFileReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogFileReader.class);

	private final SessionFactory sessionFactory;

	@Inject
	public CatalogFileReader(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void buildStarDatabase() {
		final DataSource dataSource = SessionFactoryUtils.getDataSource(sessionFactory);

		final JdbcTemplate jt = new JdbcTemplate(dataSource);
		final PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		final TransactionDefinition def = new DefaultTransactionDefinition();
		final TransactionStatus status = transactionManager.getTransaction(def);

		try (final InputStream is = CatalogFileReader.class.getResourceAsStream("/hip.data")) {
			final ZipInputStream zis = new ZipInputStream(is);
			zis.getNextEntry();

			final ByteBuffer buffer = ByteBuffer.allocateDirect(60);
			final ReadableByteChannel channel = Channels.newChannel(zis);

			final List<Star> starList = new ArrayList<Star>();
			final long startTime = System.currentTimeMillis();

			LOGGER.debug("Reading hip.data ...");
			while (channel.read(buffer) != -1) {
				final Star star = new Star();
				buffer.flip();
				star.setCatalogNumber(buffer.getLong());
				star.setRa(buffer.getDouble());
				star.setDec(buffer.getDouble());
				star.setRaPm(buffer.getDouble());
				star.setDecPm(buffer.getDouble());
				star.setMagnitude(buffer.getDouble());

				final StringBuilder sb = new StringBuilder();
				for (int i = 0; i < 12; i++) {
					sb.append((char) buffer.get());
				}
				star.setSpectralType(sb.toString());
				starList.add(star);
				buffer.clear();
			}

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
							"Star data load complete in %d ms",
							System.currentTimeMillis() - startTime));
		} catch (IOException ex) {
			throw new FatalRuntimeException("Error encountered when trying to build star database", ex);
		}
	}

}
