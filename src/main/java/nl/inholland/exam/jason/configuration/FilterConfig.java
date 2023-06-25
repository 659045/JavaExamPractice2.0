package nl.inholland.exam.jason.configuration;

import nl.inholland.exam.jason.filter.APIFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<APIFilter> apiKeyFilterRegistration() {
        FilterRegistrationBean<APIFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new APIFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
