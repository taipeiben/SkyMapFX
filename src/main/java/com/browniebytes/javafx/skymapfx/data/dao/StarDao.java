package com.browniebytes.javafx.skymapfx.data.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.browniebytes.javafx.skymapfx.data.domain.entities.Star;
import com.google.inject.Inject;

public class StarDao {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(StarDao.class);

	/**
	 * Hibernate session factory to get the DataSource from for Spring's JdbcTemplate
	 */
	private final SessionFactory sessionFactory;

	@Inject
	public StarDao(final SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	public Star findByCatalogId(final Long catalogId) {
		final Session session = sessionFactory.openSession();

		final Query query = session.getNamedQuery("findStarByCatalogId").setLong("catalogNumber", catalogId);
		final Star star = (Star) query.uniqueResult();
		session.close();

		return star;
	}

	public Map<Long, Star> findAllByPositiveAltitude() {

		final long start = System.currentTimeMillis();

		final Session session = sessionFactory.openSession();
		final Query query = session.getNamedQuery("findAllStarByPositiveAltitude");
		@SuppressWarnings("unchecked")
		final List<Star> starList = (List<Star>) query.list();
		session.close();

		LOGGER.debug(
				String.format(
						"Found %d stars with positive altitude found in %d ms",
						starList.size(),
						System.currentTimeMillis() - start));

		final Map<Long, Star> starMap = new HashMap<>();
		for (Star s : starList) {
			starMap.put(s.getCatalogNumber(), s);
		}
		return starMap;
	}

	public void saveStars(final List<Star> starList) {
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
						"Batch inserting %d stars ...",
						starList.size()));

		final long start = System.currentTimeMillis();

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
						"Star table created in %d ms",
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
				"update STARS set " +
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
						"Star table updated in %d ms",
						System.currentTimeMillis() - start));
	}
}
