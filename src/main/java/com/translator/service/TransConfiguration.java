package com.translator.service;

import com.translator.repository.Dictionary;
import com.translator.repository.EnRankDictionary;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransConfiguration {
    
    @Bean
    TransService transService(){
        return new TransServiceImpl();
    }
    
    @Bean
    EnRankDictionary enRankDictionary() throws IOException{
        return new Dictionary();
    }    
}