package altamirano.hernandez.app_springsecurity_2025.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Clase de configuracion de SpringSecurity
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfif {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    //Metodo aunthenticationManager
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Hasheo de Password
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    //Metodo seucurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        //Rutas que no requieren proteccion
                        .requestMatchers(HttpMethod.GET, "/home").permitAll()
                        .requestMatchers(HttpMethod.GET, "/liberadas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/acceso/registro").permitAll()

                        //POST en /acceso/login
                        .requestMatchers(HttpMethod.GET, "/acceso/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/acceso/login").permitAll()

                        //Rutas que requieren proteccion por roles
                        .requestMatchers(HttpMethod.GET, "/protegido/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/restringido/**").hasRole("ADMIN")

                        //Archivos estaticos liberadoros de proteccion
                        .requestMatchers("/css/**", "/js/**", "/imgs/**", "/static/**").permitAll()

                        //Configuraciones generales
                        .anyRequest().authenticated()
                )
//                .formLogin(Customizer.withDefaults())
                //Personalizacion de estilos de login
                .formLogin(form -> form
                        .loginPage("/acceso/login") //Ruta de login
                        .loginProcessingUrl("/acceso/login") //peticion post para login
                        .usernameParameter("email")
                        .defaultSuccessUrl("/liberadas/home", true) //Ruta si pasa login
                        .failureUrl("/acceso/login?error=true") //Ruta si falla el login
                        .permitAll()
                )
                .logout(Customizer.withDefaults());
//                .logout(logout -> logout
//                        .logoutUrl("/acceso/logout")
//                        .logoutSuccessUrl("/acceso/login?logout=true")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll()
//                );
        return http.build();
    }
}
