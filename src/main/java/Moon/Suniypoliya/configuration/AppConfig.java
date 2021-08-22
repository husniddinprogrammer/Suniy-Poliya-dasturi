package Moon.Suniypoliya.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {




 //   @Bean
   // public PasswordEncoder passwordEncoder() {
     //   return new BCryptPasswordEncoder();
    //}


// @Bean
// public WebMvcConfigurer corsConfigurer() {
//     return new WebMvcConfigurerAdapter() {
//         @Override
//         public void addCorsMappings(CorsRegistry registry) {
//             registry.addMapping("/**")
//                     .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
//         }
//     };
// }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//
//            source.registerCorsConfiguration("/api/**", config);
//            source.registerCorsConfiguration("/management/**", config);
//            source.registerCorsConfiguration("/v2/api-docs", config);
//            source.registerCorsConfiguration("/v3/api-docs", config);
//            source.registerCorsConfiguration("/swagger-resources", config);
//            source.registerCorsConfiguration("/swagger-ui/**", config);
//
//        return new CorsFilter(source);
//    }
}
