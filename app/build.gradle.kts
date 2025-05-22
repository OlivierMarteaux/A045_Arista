plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

android {
    namespace = "com.openclassrooms.arista"
    compileSdk = 35

    room {
        schemaDirectory("$projectDir/schemas")
    }

    defaultConfig {
        applicationId = "com.openclassrooms.arista"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        ksp.apply {
            // Enables exporting database schemas into JSON files in the given directory.
            // arg("room.schemaLocation", "$projectDir/schemas")
            // Enables Gradle incremental annotation processor. Default value is true.
            arg("room.incremental", "true")
            // Generate Kotlin source files instead of Java. Requires KSP. Default value is true as of version 2.7.0.
            arg("room.generateKotlin", "true")
        }

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
    compileOptions {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Hilt
    implementation(libs.hilt)
    implementation(libs.androidx.hilt.navigation.fragment)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // room
    implementation(libs.androidx.room.runtime)
    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)
    // If this project only uses Java source, use the Java annotationProcessor
    // No additional plugins are necessary
    // annotationProcessor(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    //Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

