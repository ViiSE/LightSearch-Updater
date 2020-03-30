package lightsearch.updater.configuration;

import lightsearch.updater.security.CustomRequestCache;
import lightsearch.updater.security.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL    = "/login?error";
    private static final String LOGIN_URL            = "/login";
    private static final String LOGOUT_SUCCESS_URL   = "/login";

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password("{bcrypt}$2a$15$9GEmgFJ7iyj.B2SKzrfIp./nJv.pbxwAu9v/anmPj9ZPt4zSN5Q/u")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/robots.txt",
                "/static/**",
                "META-INF/resources/**",
                "WEB-INF/images/**",
                "webapp/**",

                "/manifest.webmanifest",
                "/sw.js",
                "/offline-page.html",

                "/icons/**",
                "/images/**",

                "/update/info/**",
                "/update/releases/**",

                "/frontend/**",
                "/webjars/**",
                "/h2-console/**",
                "/app/**",
                "/app",
                "/app/versions/latest/**",

                "/frontend-es5/**", "/frontend-es6/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestCache().requestCache(new CustomRequestCache())
                .and().authorizeRequests()
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(LOGIN_URL).permitAll().loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL)
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }
}
