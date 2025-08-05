plugins {
    id ("org.jetbrains.kotlin.jvm")
    id ("org.jetbrains.kotlin.plugin.spring")
    id ("org.springframework.boot")
    id ("io.spring.dependency-management")
}

group = "com.delivery"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val springCloudVersion = "2024.0.0"

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll ("-Xjsr305=strict")
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}