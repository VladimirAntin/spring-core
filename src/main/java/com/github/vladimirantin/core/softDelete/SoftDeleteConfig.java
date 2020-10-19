package com.github.vladimirantin.core.softDelete;

import com.github.vladimirantin.core.softDelete.event.SoftDeletePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 17.10.2020
 * Time: 18:28
 */
@Configuration
public class SoftDeleteConfig {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public SoftDeletePublisher softDeletePublisher() {
        return new SoftDeletePublisher(applicationEventPublisher);
    }

}
