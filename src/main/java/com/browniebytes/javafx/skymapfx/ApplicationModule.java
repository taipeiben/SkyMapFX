package com.browniebytes.javafx.skymapfx;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * Application module used to instruct Guice on how to inject dependencies 
 */
public class ApplicationModule extends AbstractModule {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationModule.class);

	/**
	 * Application settings
	 */
	private final ApplicationSettings applicationSettings;

	/**
	 * Hibernate session factory
	 */
	private final SessionFactory sessionFactory;

	/**
	 * Executor
	 */
	private final ScheduledExecutorService executor;

	/**
	 * Initialize resources
	 */
	public ApplicationModule() {
		// Setup application settings
		this.applicationSettings = new ApplicationSettings();

		// Configure Hibernate
		final Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		final ServiceRegistry serviceRegistry =
				new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties())
						.build();

		sessionFactory =
				configuration.buildSessionFactory(serviceRegistry);

		final Session session = sessionFactory.openSession();
		session.doWork(
				new Work() {
					@Override
					public void execute(Connection conn) throws SQLException {
						try{
							final Dialect dialect = new H2Dialect();

							LOGGER.info("Validating database ...");
							final DatabaseMetadata metadata = new DatabaseMetadata(conn, dialect, configuration);
							configuration.validateSchema(dialect, metadata);
							LOGGER.info("Database validated");
						} catch (HibernateException e) {
							LOGGER.info("Database failed validation, creating schema ...");
							final SchemaExport export = new SchemaExport(configuration);
							export.create(false, true);
							LOGGER.info("Database schema creation completed");
						}
					}
				});
		session.close();

		// Setup executor
		final ThreadFactory tf = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				final Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		};
		this.executor = Executors.newScheduledThreadPool(10, tf);
	}

	/**
	 * Configure bindings for Guice
	 */
	@Override
	protected void configure() {
	}

	/**
	 * Provides the ApplicationSettings instance
	 * @return The ApplicationSettings instance
	 */
	@Provides
	@Singleton
	protected ApplicationSettings provideApplicationSettings() {
		return applicationSettings;
	}

	/**
	 * Provides the Hibernate session factory
	 * @return The Hiberate session factory
	 */
	@Provides
	@Singleton
	protected SessionFactory providesSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Provides a ScheduledExecutorService
	 * @return
	 */
	@Provides
	@Singleton
	protected ScheduledExecutorService providesExecutor() {
		return executor;
	}

	/**
	 * Shuts down resources
	 */
	public void shutdown() {
		if (sessionFactory != null) {
			LOGGER.info("Shutting down session factory...");
			sessionFactory.close();
		}

		if (executor != null && !executor.isShutdown()) {
			LOGGER.info("Shutting down executor...");
			executor.shutdown();
		}
	}

}
