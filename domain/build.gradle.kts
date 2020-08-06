plugins {
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
}

apply(plugin = "io.spring.dependency-management")

val springBootVersion: String by project


dependencies {
    implementation("org.hibernate:hibernate-validator:6.1.5.Final")
    api("org.springframework.boot:spring-boot-starter-data-mongodb:${springBootVersion}")
}

