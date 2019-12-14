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
                .antMatchers(SecurityFinals.LOGIN_URL, SecurityFinals.FRIENDS_ACCEPT_URL, SecurityFinals.GET_SETTINGS_URL, SecurityFinals.UPDATE_SETTINGS_URL, SecurityFinals.USER_BOOK_URL).hasAuthority(SecurityFinals.DEFAULT_AUTHORITY)
                .antMatchers(SecurityFinals.ANNOUNCEMENT_PUBLISH_URL).hasAuthority(SecurityFinals.PUBLISH_ANNOUNCEMENT)
                .antMatchers(SecurityFinals.REVIEW_ACCEPT_URL, SecurityFinals.REVIEW_NOT_ACCEPTED_URL).hasAuthority(SecurityFinals.PUBLISH_REVIEWS)
                .antMatchers(SecurityFinals.BOOK_UPDATE_URL, SecurityFinals.BOOK_ADD_FILE_URL, SecurityFinals.BOOK_ADD_IMAGE_URL).hasAuthority(SecurityFinals.PUBLISH_BOOK_OVERWIEWS)
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
