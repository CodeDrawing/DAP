package com.pearadmin.common.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Component
    public class PutFilter extends FormContentFilter{

    }
}
