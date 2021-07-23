package br.com.zup.bootcampapp.config.securit;

import br.com.zup.bootcampapp.repository.AlunoLoguinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecuritConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlunoLoguinRepository alunoLoguinRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override//Configuracoes de autenticacao
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override//Configuracoes de autorizacao
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers(HttpMethod.GET, "/listaDeAlunos/**").permitAll()
               .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .anyRequest().authenticated().
               and().csrf().disable()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, alunoLoguinRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Override//Configuracoes de recursos estaticos(js, css, imagens, etc.)
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
