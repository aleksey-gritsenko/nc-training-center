package ua.com.nc.nctrainingproject;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
	@Bean
	public DataSource datasource() {
		return DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://localhost:5432/bookNet")
				.username("postgres")
				.password("postgres")
				.build();
	}
}
