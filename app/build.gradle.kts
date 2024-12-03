plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin) // Add Kotlin plugin
    alias(libs.plugins.ksp) // Add KSP plugin
}

android {
    namespace = "com.example.ispconnect"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ispconnect"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // Use JVM Toolchain for consistent Java version
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
    }

    kotlinOptions {
        jvmTarget = "20" // Match Kotlin's target with Java's version
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21)) // Ensure Java 20 is used
        }
    }
}

dependencies {
    implementation(libs.glide)
    //ksp(libs.glide.compiler)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.google.android.gms:play-services-maps:18.1.0")
}
