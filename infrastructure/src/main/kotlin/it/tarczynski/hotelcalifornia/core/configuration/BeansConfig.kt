package it.tarczynski.hotelcalifornia.core.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import it.tarczynski.hotelcalifornia.core.conversion.BsonBinaryToUUIDConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.support.GenericConversionService
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import javax.annotation.PostConstruct

@Configuration
class BeansConfig(private val mappingMongoConverter: MappingMongoConverter) {

    @PostConstruct
    fun addBinaryUUIDConverter() {
        val conversionService = mappingMongoConverter.conversionService
        if (conversionService is GenericConversionService) {
            conversionService.addConverter(BsonBinaryToUUIDConverter())
        }
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
                .registerModule(JavaTimeModule())
                .registerModule(KotlinModule())
    }
}
