package ru.otus.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.otus.spring.dao.SecurityDao;
import ru.otus.spring.model.Security;

import java.util.List;
import java.util.Properties;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final SecurityDao securityDao;

    public SecurityConfiguration(SecurityDao securityDao) {
        this.securityDao = securityDao;
    }

    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/").anonymous()
                .and().authorizeRequests().antMatchers("/insert", "/edit", "/bookDelete").authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        Properties users = new Properties();
        List<Security> sec = this.securityDao.findAll();
        sec.forEach(e -> users.put(e.getUser(), e.getPassword() + "," + e.getRole() + ",enabled"));
        return new InMemoryUserDetailsManager(users);
    }
}
