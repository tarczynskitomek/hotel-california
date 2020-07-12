package it.tarczynski.hotelcalifornia.core.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeansConfig {

    @Bean
    fun objectMapper() = ObjectMapper()
}
