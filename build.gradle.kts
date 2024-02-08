plugins {
    id("java")
}

group = "org.longbox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.toedter:jcalendar:1.4")
    implementation("com.jgoodies:jgoodies-forms:1.8.0")
    implementation("org.hibernate:hibernate-core:6.4.2.Final")
    implementation("org.postgresql:postgresql:42.7.1")
    implementation("jakarta.mail:jakarta.mail-api:2.1.2")
    compileOnly("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}