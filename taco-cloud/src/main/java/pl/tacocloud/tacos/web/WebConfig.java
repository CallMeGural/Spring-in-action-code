package pl.tacocloud.tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // metoda ta zastepuje HomeController - na endpoincie '/' ustawiany jest widok home.html
        registry.addViewController("/").setViewName("home");
    }
}
