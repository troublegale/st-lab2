plugins {
    kotlin("jvm") version "2.1.0"
}

group = "itmo.tg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter:5.10.0")
    implementation("org.apache.commons:commons-csv:1.13.0")
    testImplementation("org.mockito:mockito-core:5.16.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}