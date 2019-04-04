package springboot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.filter.ThreadContextFilter;

/**
 * Created by Danylo on 02.04.2019
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ThreadContextFilter> loggingFilter() {
        FilterRegistrationBean<ThreadContextFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new ThreadContextFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
