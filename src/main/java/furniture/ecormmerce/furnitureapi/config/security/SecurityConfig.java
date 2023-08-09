package furniture.ecommerce.furnitureecommerce.config.security;

import furniture.ecommerce.furnitureecommerce.config.security.filter.JwtAuthenticationFilter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtAuthenticationFilter filter;
    private final AccessDeniedHandler accessDeniedHandler;
    private final LogoutHandler logoutHandler;


    private final String[] allowedEndpoints = {
            "/api/v1/users/**", "/api/v1/users/reset-password/**","/api/v1/users/profilepicture/**",
            "/swagger-ui/index.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.accessDeniedHandler (accessDeniedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .authorizeHttpRequests((authz -> authz.requestMatchers(allowedEndpoints)
                        .permitAll()
                        .anyRequest()
                        .authenticated()))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling (e -> e.accessDeniedHandler (accessDeniedHandler))
                .logout (out -> out.logoutUrl ("http://localhost"))
                .logout (out -> out.addLogoutHandler (logoutHandler))
                .logout (out -> out.logoutSuccessHandler ((
                        (request, response, authentication)
                                -> SecurityContextHolder.clearContext ())));
        return security.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "PUT", "PATCH", "DELETE");
            }
        };
    }

}
