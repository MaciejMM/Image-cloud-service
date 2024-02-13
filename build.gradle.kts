import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}



repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.cloudinary:cloudinary-http44:1.37.0")
	implementation("io.github.cdimascio:java-dotenv:5.2.2")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:3.4.11")
	testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.21")
// https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "17"
	}

	tasks.withType<Test> {
	useJUnitPlatform()
}}
