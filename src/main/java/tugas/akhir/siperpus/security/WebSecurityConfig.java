package tugas.akhir.siperpus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll()
                .antMatchers("/book/add/**").hasAnyAuthority("Pustakawan")
                .antMatchers("/book/update/**").hasAnyAuthority("Pustakawan")
                .antMatchers("/book/delete/**").hasAnyAuthority("Pustakawan")
                .antMatchers("/loan/view/**").hasAnyAuthority("Pustakawan", "Guru", "Siswa")
                .antMatchers("/loan/update/**").hasAnyAuthority("Pustakawan")
                .antMatchers("/loan/add/**").hasAnyAuthority("Guru", "Siswa")
                .antMatchers("/procurement/delete/**").hasAnyAuthority("Pustakawan", "Guru", "Siswa")
                .antMatchers("/procurement/view/**").hasAnyAuthority("Pustakawan","Guru","Siswa")
                .antMatchers("/user/addUser/**").hasAnyAuthority("Pustakawan")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable();

    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // @Autowired
    // public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception{
    //     auth.inMemoryAuthentication()
    //     . passwordEncoder(encoder())
    //     .withUser("nadiem").password(encoder().encode("makarim"))
    //     .roles("Pustakawan");
    // }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}