import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.io.println

group = "com.evacipated.cardcrawl"
var projectName = "VelkhanaMod"
val slayTheSpireInstallDir = "${System.getenv("STS_HOME")}"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
        maven("http://maven.aliyun.com/nexus/content/repositories/jcenter")
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
        mavenLocal()
        jcenter()
    }

    dependencies {
        fileTree("libs/gradle-plugin/kotlin")
        classpath(kotlin("gradle-plugin"))
    }
}

plugins {
    java
    kotlin("jvm") version "1.3.72"
}

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("http://maven.aliyun.com/nexus/content/repositories/jcenter")
    maven("https://plugins.gradle.org/m2/")
    mavenCentral()
    mavenLocal()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.apache.logging.log4j:log4j-api:2.11.1")
    implementation("org.apache.logging.log4j:log4j-core:2.11.1")
    compileOnly(fileTree("lib"))
//    implementation(fileTree(mapOf("dir" to "lib", "include" to listOf("*.jar"))))
    testImplementation("junit:junit:4.13")
}

sourceSets {
    main {
        java {
            srcDirs.add(file("src/main/kotlin/"))
        }
        resources {
            srcDir("src/resources")
        }
    }
}


tasks.register<Jar>("buildJar") {
    archiveName = "$projectName.jar"
    from(sourceSets.main.get().output) {}
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })
    println("runtime")
    println(configurations.runtimeClasspath)
    configurations.runtimeClasspath.get().forEach {
        println(it.name)
    }
}

tasks.register<Copy>("copyToMods") {
//    dependsOn("clean")
    dependsOn("buildJar")
    if (slayTheSpireInstallDir == "" || slayTheSpireInstallDir == "null") {
        throw Exception("STS_HOME is not set.")
    }
    from("build/libs/$projectName.jar")
    into("$slayTheSpireInstallDir\\mods")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}