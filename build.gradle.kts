plugins {
    java
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
}

group = "com.github.kimcore"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.5.2-native-mt")
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", "1.5.2-native-mt")
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.3.0")
    implementation("io.ktor", "ktor-client-cio", "1.6.4")
    implementation("io.ktor", "ktor-client-serialization", "1.6.4")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}