plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.0"
    id("io.micronaut.test-resources") version "4.4.0"
    id("io.micronaut.aot") version "4.4.0"
}

version = "0.1"
group = "io.github.donatepix"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("io.micronaut.data:micronaut-data-document-processor")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.openapi:micronaut-openapi")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-websocket")
    implementation("io.micronaut.cache:micronaut-cache-caffeine")
    implementation("io.micronaut.data:micronaut-data-mongodb")
    implementation("io.micronaut.elasticsearch:micronaut-elasticsearch")
    implementation("io.micronaut.email:micronaut-email-javamail")
    implementation("io.micronaut.kafka:micronaut-kafka")
    implementation("io.micronaut.problem:micronaut-problem-json")
    implementation("io.micronaut.rabbitmq:micronaut-rabbitmq")
    implementation("io.micronaut.redis:micronaut-redis-lettuce")
    implementation("io.micronaut.serde:micronaut-serde-bson")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.serde:micronaut-serde-jsonp")

    implementation("io.micronaut.toml:micronaut-toml")

    implementation("io.micronaut.validation:micronaut-validation")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.apache.logging.log4j:log4j-api")
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.23.1"))
    compileOnly("io.micronaut.openapi:micronaut-openapi-annotations")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.apache.logging.log4j:log4j-core")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl")
    runtimeOnly("org.eclipse.angus:angus-mail")
    runtimeOnly("org.mongodb:mongodb-driver-sync")

    testImplementation("org.mockito:mockito-core")
}


application {
    mainClass = "io.github.donatepix.user.UserProfileApplication"
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}


graalvmNative.toolchainDetection = false
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("io.github.donatepix.*")
    }
    testResources {
        sharedServer = true
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}



