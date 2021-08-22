package Moon.Suniypoliya.security;


import Moon.Suniypoliya.entity.Lavozim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //
//    @Autowired
//CorsFilter corsFilter;
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    UserProvider userProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userProvider).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
         http
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests()
        .antMatchers("/").permitAll().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                .antMatchers("/authenticate").permitAll()
                .antMatchers("/api/poliya/people").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString(), Lavozim.PEOPLE.toString())
                .antMatchers("/api/buyurtma/").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/buyurtma/kuntartibi/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/buyurtma/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/buyurtma/month").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/buyurtma/week").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/buyurtma/today").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/buyurtma/{sana1}/{sana2}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/buyurtma/delete/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/chiqim/").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/chiqim/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/chiqim/today").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/chiqim/week").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/chiqim/month").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/kirim/").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/kirim/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/mijoz/").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/mijoz/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/mijoz/delete/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/poliya/").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString(),Lavozim.PEOPLE.toString())
                .antMatchers("/api/poliya/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/poliya/delete/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/poliya/people").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.PEOPLE.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/tulov-qilish/").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/tulov-qilish/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/tulov-qilish/buyurtma/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/user").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/user/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/user/profile").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/user/profile/parol").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.ADMIN.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/user/profile/creator").hasAnyRole(Lavozim.CREATOR.toString())
                .antMatchers("/api/xabar/").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/xabar/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/xabar/kirim-chiqim").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/xabar/oqildi/{id}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .antMatchers("/api/xabar/sana/{sana}").hasAnyRole(Lavozim.CREATOR.toString(), Lavozim.BOSS.toString())
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and().
                 httpBasic().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 		Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/static/**");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("http://localhost:5500");
        config.addAllowedOrigin("http://localhost:5501");
        config.addAllowedOrigin("http://localhost:5502");
        config.addAllowedOrigin("http://192.168.43.190:5500");
        config.addAllowedOrigin("http://192.168.43.1:5500");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
