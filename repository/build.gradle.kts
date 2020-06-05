plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    project(":domain")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}
