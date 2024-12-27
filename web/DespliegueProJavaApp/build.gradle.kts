plugins {
    id("java")
    // jacoco
    id("jacoco")
}

group = "dev.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

// Jacoco

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        html.required.set(true)
    }
}

// Generar informe de cobertura
tasks.build {
    // Informe de cobertura
    dependsOn(tasks.jacocoTestReport) // Ejecutar el informe de cobertura
    dependsOn(tasks.javadoc) // Ejecutar los test
}

// Generar el Jdoc


// Jar executable
tasks.jar {
    manifest {
        attributes["Main-Class"] = "dev.joseluisgs.Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}