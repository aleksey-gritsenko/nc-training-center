package ua.com.nc.nctrainingproject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/announcements/**").access("hasAuthority('DEFAULT_AUTHORITY')")
                .antMatchers("/login/**").access("hasAuthority('DEFAULT_AUTHORITY')")
                .antMatchers("/users/**").access("hasAuthority('DEFAULT_AUTHORITY')")
                .antMatchers("/announcements/publish/**").access("hasAuthority('publish announcement')")
                .antMatchers("/review/accept/**").access("hasAuthority('publish reviews')")

                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
