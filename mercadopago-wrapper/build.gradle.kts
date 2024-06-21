plugins {
    id("java")
}

version = "0.1"
group = "io.github.donatepix"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.retrofit2:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
}