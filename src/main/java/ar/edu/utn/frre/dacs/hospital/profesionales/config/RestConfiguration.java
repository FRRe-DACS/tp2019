/*
 * Copyright (C) 2015-2019 UTN-FRRe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.edu.utn.frre.dacs.hospital.profesionales.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 
 * 
 * @author Dr. Jorge Villaverde
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories(
		basePackages = "ar.edu.utn.frre.dacs.hospital.profesionales.dao", 
		entityManagerFactoryRef = "defaultEntityManagerFactory", 
		transactionManagerRef = "defaultTransactionManager")
public class RestConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestConfiguration.class);

    @Autowired
    private Environment env;

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPersistenceUnitName("dacs-pu");
		em.setDataSource(defaultDataSource());
		em.setPackagesToScan(new String[] { "ar.edu.utn.frre.dacs.hospital.profesionales.model" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean
	@Primary
	public DataSource defaultDataSource() {
		LOGGER.info("User URL: " + env.getProperty("spring.datasource.url"));
		LOGGER.info("User Username: " + env.getProperty("spring.datasource.username"));
		LOGGER.info("User Password: " + env.getProperty("spring.datasource.password"));
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}

	@Bean
	@Primary
	public PlatformTransactionManager defaultTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(defaultEntityManagerFactory().getObject());
		return transactionManager;
	}

    @Bean
    public ModelMapper modelMapper() {
    	return new ModelMapper();
    }
	
}
