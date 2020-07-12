plugins {
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
}

apply(plugin = "io.spring.dependency-management")

val springBootVersion: String by project

dependencies {
    implementation(project(":domain:booking"))
}
