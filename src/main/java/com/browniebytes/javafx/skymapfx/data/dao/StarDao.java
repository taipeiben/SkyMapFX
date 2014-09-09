package com.browniebytes.javafx.skymapfx.data.dao;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.browniebytes.javafx.skymapfx.data.entities.Star;
import com.google.inject.Inject;

public class StarDao {

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

	public void updateAltitudeAzimuth(final double latitude, final double lmst) {
		// Get the datasource from Hibernate
		final DataSource dataSource = SessionFactoryUtils.getDataSource(sessionFactory);

		// Setup DB objects
		final JdbcTemplate jt = new JdbcTemplate(dataSource);
		final PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		final TransactionDefinition def = new DefaultTransactionDefinition();
		final TransactionStatus status = transactionManager.getTransaction(def);

		
	}
}
