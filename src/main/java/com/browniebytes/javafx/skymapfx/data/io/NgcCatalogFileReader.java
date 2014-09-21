package com.browniebytes.javafx.skymapfx.data.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browniebytes.javafx.skymapfx.data.dao.DeepSkyObjectDao;
import com.browniebytes.javafx.skymapfx.data.domain.entities.DeepSkyObject;
import com.browniebytes.javafx.skymapfx.data.domain.entities.DeepSkyObject.DsoCatalog;
import com.browniebytes.javafx.skymapfx.data.domain.entities.DeepSkyObject.DsoType;
import com.browniebytes.javafx.skymapfx.exceptions.FatalRuntimeException;
import com.google.inject.Inject;

public class NgcCatalogFileReader {

	private static final String NGC_CATALOG_FILE_NAME = "ngc2000.dat";

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(NgcCatalogFileReader.class);

	private final DeepSkyObjectDao dsoDao;

	@Inject
	public NgcCatalogFileReader(final DeepSkyObjectDao dsoDao) {
		this.dsoDao = dsoDao;
	}

	/**
	 * Builds the DSO database
	 */
	public void buildDeepSkyObjectDatabase() {
		// Create input stream from zip file "hip.data"
		try (final InputStream is = NgcCatalogFileReader.class.getResourceAsStream("/ngc.data")) {
			final ZipInputStream zis = new ZipInputStream(is);

			// Grab timestamp for debug message
			final long startTime = System.currentTimeMillis();

			for (int i = 0; i < 1; i++) {
				final ZipEntry zipEntry = zis.getNextEntry();
				final String entryName = zipEntry.getName();
				LOGGER.info(String.format("Processing file: %s", entryName));
				if (entryName.equals(NGC_CATALOG_FILE_NAME)) {

					// Each record is 32 bytes
					final ByteBuffer buffer = ByteBuffer.allocateDirect(32);
					// Use NIO's ReadableByteChannel to pull data from zip stream
					final ReadableByteChannel channel = Channels.newChannel(zis);

					// Store stars in temp array to batch insert
					final List<DeepSkyObject> dsoList = new ArrayList<>();

					int line = 0;
					// Read until end of channel
					while (channel.read(buffer) != -1) {
						buffer.flip();

						line++;
						final DeepSkyObject dso = new DeepSkyObject();
						dso.setCatalog(DsoCatalog.getValueOf((char) buffer.get()));
						dso.setCatalogNumber((long) buffer.getInt());

						final StringBuilder sb = new StringBuilder();
						sb.append((char) buffer.get()).append((char) buffer.get()).append((char) buffer.get());
						final DsoType type = DsoType.getValueOf(sb.toString().trim());
						dso.setType(type);
						dso.setRa(Math.toRadians(buffer.getDouble()));
						dso.setDec(Math.toRadians(buffer.getDouble()));
						dso.setMagnitude(buffer.getDouble());

						if (type == null) {
							LOGGER.debug(
									String.format(
											"Line #%d: Skipping %s %d of type %s and RA/DEC/MAG: %f/%f/%f",
											line,
											dso.getCatalog(),
											dso.getCatalogNumber(),
											sb.toString(),
											dso.getRa(),
											dso.getDec(),
											dso.getMagnitude()));
							buffer.clear();
							continue;
						}

						dsoList.add(dso);
						buffer.clear();
					}
					channel.close();

					dsoDao.saveDeepSkyObjects(dsoList);
				} else if (entryName.equals("")) {
					
				}
			}

			LOGGER.debug(
					String.format(
							"Deep Sky Object data load complete in %d ms, %d DSOs filtered by magnitude",
							System.currentTimeMillis() - startTime,
							0));
		} catch (IOException ex) {
			throw new FatalRuntimeException("Error encountered when trying to build deep sky object database", ex);
		} catch (Throwable th) {
			LOGGER.error("Error encountered when trying to build deep sky object database", th);
		}
	}
}
