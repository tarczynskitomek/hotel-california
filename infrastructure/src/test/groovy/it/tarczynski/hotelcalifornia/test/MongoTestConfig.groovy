package it.tarczynski.hotelcalifornia.test

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.bson.UuidRepresentation
import org.bson.codecs.UuidCodec
import org.bson.codecs.configuration.CodecRegistries
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MongoDBContainer

import javax.annotation.PostConstruct

@TestConfiguration
class MongoTestConfig {
    private final MongoDBContainer mongoDBContainer = new MongoDBContainer()

    @PostConstruct
    def setupMongo() {
        mongoDBContainer.withExposedPorts(27017).start()
    }

    @Bean
    MongoClient mongo() {
        def mappedPort = mongoDBContainer.getFirstMappedPort()
        def mongoSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:${mappedPort}"))
                .codecRegistry(
                        CodecRegistries.fromRegistries(
                                CodecRegistries.fromCodecs(new UuidCodec(UuidRepresentation.STANDARD))))
                .build()
        MongoClients.create(mongoSettings)
    }
}
