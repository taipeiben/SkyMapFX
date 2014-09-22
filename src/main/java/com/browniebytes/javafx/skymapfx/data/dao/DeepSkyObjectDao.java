package com.browniebytes.javafx.skymapfx.data.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
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

import com.browniebytes.javafx.skymapfx.data.domain.entities.DeepSkyObject;
import com.google.inject.Inject;

public class DeepSkyObjectDao {

	/*
		TABLE DEFINITION:

		create table DEEP_SKY_OBJECTS (
			id bigint generated by default as identity (start with 1),
			altitude double,
			azimuth double,
			catalog integer,
			catalogNumber bigint not null,
			dec double not null,
			magnitude double not null,
			ra double not null,
			type integer,
			primary key (id)
		)
	*/

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DeepSkyObjectDao.class);

	/**
	 * Hibernate session factory to get the DataSource from for Spring's JdbcTemplate
	 */
	private final SessionFactory sessionFactory;

	@Inject
	public DeepSkyObjectDao(final SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	public List<DeepSkyObject> findAllByPositiveAltitude() {

		final long start = System.currentTimeMillis();

		final Session session = sessionFactory.openSession();
		final Query query = session.getNamedQuery("findAllDsoByPositiveAltitude");
		@SuppressWarnings("unchecked")
		final List<DeepSkyObject> dsoList = (List<DeepSkyObject>) query.list();
		session.close();

		LOGGER.debug(
				String.format(
						"%d deep sky objects with positive altitude found in %d ms",
						dsoList.size(),
						System.currentTimeMillis() - start));
		return dsoList;
	}

	public void saveDeepSkyObjects(final List<DeepSkyObject> dsoList) {
		// Get the datasource from Hibernate
		final DataSource dataSource = SessionFactoryUtils.getDataSource(sessionFactory);

		// Setup DB objects
		final JdbcTemplate jt = new JdbcTemplate(dataSource);
		final PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		final TransactionDefinition def = new DefaultTransactionDefinition();
		final TransactionStatus status = transactionManager.getTransaction(def);

		// Batch JDBC insert using JdbcTemplate from Spring
		LOGGER.debug(
				String.format(
						"Batch inserting %d deep sky objects ...",
						dsoList.size()));

		final long start = System.currentTimeMillis();

		double[] max = new double[] { Double.MIN_VALUE };
		jt.batchUpdate(
				"insert into DEEP_SKY_OBJECTS (catalog, catalogNumber, type, ra, dec, magnitude) VALUES (?, ?, ?, ?, ?, ?)",
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(final PreparedStatement ps, final int i) throws SQLException {
						final DeepSkyObject dso = dsoList.get(i);
						ps.setInt(1, dso.getCatalog().ordinal());
						ps.setLong(2, dso.getCatalogNumber());
						try {
						ps.setInt(3, dso.getType().ordinal());
						} catch (NullPointerException ex) {
							ex.printStackTrace();
						}
						ps.setDouble(4, dso.getRa());
						ps.setDouble(5, dso.getDec());
						ps.setDouble(6, dso.getMagnitude());
						if (dso.getMagnitude() > max[0]) {
							max[0] = dso.getMagnitude();
						}
					}

					@Override
					public int getBatchSize() {
						return dsoList.size();
					}
				});

		LOGGER.debug("Committing ...");
		LOGGER.debug("Max value: " + max[0]);
		transactionManager.commit(status);

		LOGGER.debug(
				String.format(
						"Deep Sky Object table created in %d ms",
						System.currentTimeMillis() - start));
	}

	public void updateAltitudeAzimuth(final double latitude, final double lmst) {
		// Get the datasource from Hibernate
		final DataSource dataSource = SessionFactoryUtils.getDataSource(sessionFactory);

		// Setup DB objects
		final JdbcTemplate jt = new JdbcTemplate(dataSource);
		final PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		final TransactionDefinition def = new DefaultTransactionDefinition();
		final TransactionStatus status = transactionManager.getTransaction(def);

		LOGGER.debug("Updating azimuth/altitude columns ...");

		final long start = System.currentTimeMillis();

		jt.update(
				"update DEEP_SKY_OBJECTS set " +
						"azimuth = DEGREES(ATAN2(SIN(? - ra), COS(? - ra) * SIN(?) - TAN(dec) * COS(?)) + PI()), " +
						"altitude = DEGREES(ASIN(SIN(?) * SIN(dec) + COS(?) * COS(dec) * COS(? - ra)))",
				(ps) -> {
					ps.setDouble(1, lmst);
					ps.setDouble(2, lmst);
					ps.setDouble(3, latitude);
					ps.setDouble(4, latitude);

					ps.setDouble(5, latitude);
					ps.setDouble(6, latitude);
					ps.setDouble(7, lmst);
				});

		LOGGER.debug("Committing ...");
		transactionManager.commit(status);

		LOGGER.debug(
				String.format(
						"Deep Sky Object table updated in %d ms",
						System.currentTimeMillis() - start));
	}
}