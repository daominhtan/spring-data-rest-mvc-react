package com.laranevans.springrestreact.config;

import com.laranevans.springrestreact.security.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
// This global method security configuration enables the pre and post checks in the repositories.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // This permits access to all client-side code. This isn't an issue though because all sensitive data
                // is provided by the REST API, which IS secured.
                .antMatchers("/").permitAll()
                // Require authentication for all API calls.
                .antMatchers("/api/**").authenticated()
                .and()
            .formLogin()
                .loginPage("/#/login")
                .permitAll()
                .and()
            /*
                The bits here about CSRF I got from this reference about using spring-security with Angular.
                https://spring.io/guides/tutorials/spring-security-and-angular-js/
             */
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
            .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                /*
                    This is the simplest case, using in-memory authentication.
                    I'll add on to this to show jdbc authentication soon.
                 */
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        /**
         * You'll see references to the X-XSRF-TOKEN header in the javascript code.
         */
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

}
