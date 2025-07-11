plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
}

group = "net.verdox"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation("org.projectlombok:lombok:1.18.34")
    implementation("org.mongodb:mongodb-driver-sync:5.1.0")
    annotationProcessor("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    annotationProcessor ("org.projectlombok:lombok:1.18.34")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}


tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        minimize()
        relocate("co.aikar", "net.verdox.lib")
    }

    compileJava {
        options.encoding = "UTF-8"
    }
}