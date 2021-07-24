package br.com.zup.bootcampapp.config.securit;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
@Profile(value = {"prod", "test"})
public class DevSecuritConfiguration extends WebSecurityConfigurerAdapter {

    @Override//Configuracoes de autorizacao
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers( "/**").permitAll()
                .anyRequest().authenticated().
               and().csrf().disable();
    }

}
