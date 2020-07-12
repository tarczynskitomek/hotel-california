plugins {
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
}

apply(plugin = "io.spring.dependency-management")

val springBootVersion: String by project

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb:${springBootVersion}")
}
