@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL
import java.io.File

val kolor_version: String by project
val result_version: String by project

plugins {
    maven
    `java-library`
    kotlin("jvm") version "1.4.0"
    id("org.jetbrains.dokka") version "1.4.0-dev-49"
}

group = "dev.31416"
version = "0.2.0"

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
    maven("https://jitpack.io")
    maven { url = uri("https://dl.bintray.com/kittinunf/maven") }
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

    // Jackson

    implementation("com.fasterxml.jackson.core", "jackson-core", "2.10.2")
    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.10.2")
    implementation("com.fasterxml.jackson.core", "jackson-annotations", "2.10.2")
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.10.2")

    // Kolor

    implementation("com.andreapivetta.kolor", "kolor", kolor_version)

    // Result

    implementation("com.github.kittinunf.result", "result", result_version)
    implementation("com.github.kittinunf.result", "result-coroutines", result_version)

    // Logback

    implementation("ch.qos.logback:logback-classic:1.2.3")

    // Testing

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.5.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.5.2")
}

artifacts {
    archives(tasks.kotlinSourcesJar)
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

tasks {
    withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }

    withType<Test>().configureEach {
        useJUnitPlatform()

        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    dokkaHtml {
        outputDirectory.set(File("docs/dokka"))

        dokkaSourceSets {
            configureEach {
                skipDeprecated.set(true)

                reportUndocumented.set(false)

                skipEmptyPackages.set(true)

                includes.from("docs/reflectr.md")

                jdkVersion.set(8)

                perPackageOption {
                    prefix.set("reflectr.util")
                    suppress.set(true)
                }

                sourceLink {
                    localDirectory.set(projectDir)
                    remoteUrl.set(URL("https://github.com/blogify-dev/reflectr/blob/master/"))
                    remoteLineSuffix.set("#L")
                }
            }
        }
    }
}
