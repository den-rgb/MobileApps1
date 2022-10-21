import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "org.setu.placemark"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

javafx {

    modules("javafx.controls", "javafx.graphics")
}


dependencies {
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation ("io.github.microutils:kotlin-logging:2.1.23")
    implementation ("no.tornado:tornadofx:1.7.19")
    implementation("com.google.code.gson:gson:2.9.0")
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}