package com.surjo.admin.config;

import com.fasterxml.jackson.databind.*;
import com.surjo.security.core.CustomSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.security.Principal;
import java.util.Optional;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.surjo"})
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class StartupConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomSession session;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST","PUT","GET","DELETE", "HEAD");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

//    @Bean
//    public CustomSession customSession() {
//        return new CustomSession() {
//            @Override
//            public Principal getPrincipal() {
//                return (SecurityContextHolder.getContext().getAuthentication() == null)?
//                        null :
//                        (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            }
//
//            @Override
//            public String getUsername() {
//                Principal principal = getPrincipal();
//                return (principal == null)? "public" : principal.getName();
//            }
//
//            @Override
//            public String getTerminal() {
//                return null;
//            }
//
//            @Override
//            public Long getUserBranch() {
//                return null;
//            }
//
//            @Override
//            public String[] getAuthorities() {
//                return new String[0];
//            }
//        };
//    }

    private class AuditorAwareImpl implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.of(session.getUsername()) ;
        }
    }
}