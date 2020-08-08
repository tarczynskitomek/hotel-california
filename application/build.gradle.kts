plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":service"))
    implementation(project(":repository"))
    implementation("javax.validation:validation-api:2.0.1.Final")
}
