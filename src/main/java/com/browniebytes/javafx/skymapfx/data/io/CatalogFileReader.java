package com.browniebytes.javafx.skymapfx.data.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browniebytes.javafx.skymapfx.ApplicationSettings;
import com.browniebytes.javafx.skymapfx.ApplicationSettings.Settings;
import com.browniebytes.javafx.skymapfx.data.dao.StarDao;
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
	private final StarDao starDao;

	@Inject
	public CatalogFileReader(
			final ApplicationSettings applicationSettings,
			final StarDao starDao) {

		this.applicationSettings = applicationSettings;
		this.starDao = starDao;
	}

	/**
	 * Builds the star database
	 */
	public void buildStarDatabase() {

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
				star.setRa(Math.toRadians(buffer.getDouble()));
				star.setDec(Math.toRadians(buffer.getDouble()));
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

			starDao.saveStars(starList);

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
