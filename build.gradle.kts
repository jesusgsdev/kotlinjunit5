@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kluent_version: String by project
val junit_version: String by project
val mockk_version: String by project


plugins {
	kotlin("jvm") version "1.3.70"
	idea
	eclipse
	java
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))

	testImplementation("org.junit.jupiter:junit-jupiter:$junit_version")
	testImplementation("org.amshove.kluent:kluent:$kluent_version")
	testImplementation("io.mockk:mockk:$mockk_version")
}

tasks.test {
	useJUnitPlatform()
	testLogging {
		events("passed", "skipped", "failed")
	}
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "1.8"
}
