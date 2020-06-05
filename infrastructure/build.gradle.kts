plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
}

apply(plugin = "io.spring.dependency-management")
apply(plugin = "groovy")

val groovyVersion: String by project
val spockBomVersion: String by project

dependencies {
    implementation(project(":domain:booking"))
    implementation(project(":repository"))
    implementation(project(":service"))
    implementation(project(":application"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.codehaus.groovy:groovy-all:$groovyVersion")

    testImplementation(platform("org.spockframework:spock-bom:$spockBomVersion"))
    testImplementation("org.spockframework:spock-core")
    testImplementation("org.spockframework:spock-junit4")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
