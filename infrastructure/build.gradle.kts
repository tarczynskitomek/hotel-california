plugins {
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    id("com.avast.gradle.docker-compose") version "0.13.0"
}

apply(plugin = "io.spring.dependency-management")
apply(plugin = "groovy")

val groovyVersion: String by project
val spockVersion: String by project
val mongoDbTestcontainersVersion: String by project

dependencies {
    implementation(project(":domain"))
    implementation(project(":repository"))
    implementation(project(":service"))
    implementation(project(":application"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.codehaus.groovy:groovy-all:$groovyVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test")
    testImplementation("org.spockframework:spock-spring:${spockVersion}")
    testImplementation("org.spockframework:spock-core:${spockVersion}")
    testImplementation("org.testcontainers:mongodb:${mongoDbTestcontainersVersion}")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dockerCompose {
    useComposeFiles = mutableListOf("tools/docker-compose/docker-compose.yml")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
