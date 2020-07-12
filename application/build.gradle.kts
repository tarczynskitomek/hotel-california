plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":domain:booking"))
    implementation(project(":service"))
    implementation(project(":repository"))
}
