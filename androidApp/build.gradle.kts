@file:Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixedplugins {
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    kotlin("kapt")
    id(libs.plugins.hilt.android.get().pluginId)
    id("template.coroutines")
}

android {
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "com.kents.snuh"

        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.kents.snuh.TestRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    hilt { enableAggregatingTask = true }

    buildFeatures { compose = true }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get().toString()
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("/META-INF/LICENSE*")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "com.kents.snuh"
}

dependencies {
    // Project Dependencies
    implementation(project(":core:domain"))
    implementation(project(":core:commons"))

    // Dependencies
    // Compose
    implementation(libs.bundles.compose)

    // Hilt
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Ktor Engine
    implementation(libs.ktor.core)
    implementation(libs.ktor.engine.android)

    // SplashScreen
    implementation(libs.splashscreen)

    // Datastore
    implementation(libs.datastore.android)

    // Debug Dependencies
    debugImplementation(libs.bundles.compose.debug)

    // Android Test Dependencies
    androidTestImplementation(libs.hilt.test)
    kaptAndroidTest(libs.hilt.test.compiler)

    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.bundles.compose.test)
    androidTestImplementation(libs.ktor.engine.mock)
    androidTestImplementation(project(":core:data-test"))

    // Test Dependencies
    testImplementation(libs.bundles.test.core)
    testImplementation(libs.mockk.agent)
    testImplementation(libs.mockk.android)
}
