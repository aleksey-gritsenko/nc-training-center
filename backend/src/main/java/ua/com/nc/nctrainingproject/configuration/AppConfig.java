package ua.com.nc.nctrainingproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class AppConfig {
	private final Environment environment;

	@Autowired
	public AppConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

		driverManagerDataSource.setUrl(environment.getProperty("spring.datasource.url"));
		driverManagerDataSource.setUsername(environment.getProperty("spring.datasource.username"));
		driverManagerDataSource.setPassword(environment.getProperty("spring.datasource.password"));
		driverManagerDataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		return driverManagerDataSource;
	}
}
