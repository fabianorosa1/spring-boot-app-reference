package com.sample.springboot.persistence.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import lombok.extern.slf4j.Slf4j;

/**
 * Class Responsible for Load SQL Scripts in Local Enviroment 
 *
 */
@Configuration
@Slf4j
public class LocalDatabasePopulatorConfig {

	/**
	 * Class Field SQL Scripts Files
	 */
	@Value("classpath*:db/scripts/*.sql")
	private Resource[] scripts;

	/**
	 * This method is responsible to create DatabasePopulator
	 * 
	 * @return DatabasePopulator
	 */
	@Bean
	public DatabasePopulator databasePopulator() {
		return new ResourceDatabasePopulator(scripts);
	}

	/**
	 * This method is responsible create ApplicationListener for ApplicationReadyEvent 
	 * 
	 * @param databasePopulator
	 * @return ApplicationListener
	 */
	@Bean
	public ApplicationListener<ApplicationReadyEvent> applicationListener(final DatabasePopulator databasePopulator){
		return new ApplicationListener<ApplicationReadyEvent>() {
			
			/**
			 * This method is responsible for execute sql scripts when ApplicationReadyEvent is fire
			 * 
			 */
			@Override
			public void onApplicationEvent(ApplicationReadyEvent event) {
				ConfigurableApplicationContext context = event.getApplicationContext();
				DataSource dataSource = context.getBean(DataSource.class);
				try {
					databasePopulator.populate(dataSource.getConnection());					
				} catch (Exception e) {
					log.error("Error on populate database", e);
				}
		
			}
		};
	}
}
