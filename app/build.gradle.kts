plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.capstonebangkit.voishy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.capstonebangkit.voishy"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL_MOVIE", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "BASE_URL_ARTICLE", "\"https://us-central1-restapifirebase-70de4.cloudfunctions.net/app/api/\"")
        buildConfigField("String", "API_KEY_MOVIE", "\"628bdf389366510b6002d3be8e4340d2\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
// Retrofit for network requests - handles REST API communication
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

// Gson Converter for Retrofit - converts JSON to Java/Kotlin objects
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp Logging Interceptor - logs HTTP request and response data
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

// ViewModel KTX - provides viewModelScope and other ViewModel features
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

// LiveData KTX - simplifies the use of LiveData in Kotlin
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0")

// Core KTX - Kotlin extensions for standard Android classes
    implementation("androidx.core:core-ktx:1.9.0")

// Lifecycle runtime KTX - Kotlin extensions for lifecycle runtime
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

// Activity Compose - integration of Compose with Activities
    implementation("androidx.activity:activity-compose:1.8.1")

// Compose BOM - Bill of Materials, manages Compose library versions
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))

// Compose UI - fundamental UI building block set for Compose
    implementation("androidx.compose.ui:ui")

// Compose UI Graphics - graphical and animation components for Compose
    implementation("androidx.compose.ui:ui-graphics")

// Compose UI Tooling Preview - preview tools for Compose layouts
    implementation("androidx.compose.ui:ui-tooling-preview")

// Material 3 - Material Design components for Compose
    implementation("androidx.compose.material3:material3")

// Material Icons Extended - extended set of Material icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

// JUnit - unit testing framework for Java
    testImplementation("junit:junit:4.13.2")

// AndroidX Test JUnit - JUnit extensions for AndroidX
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

// Espresso Core - UI testing framework for Android
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



// Compose UI Tooling - tooling support for Compose (debugging)
    debugImplementation("androidx.compose.ui:ui-tooling")

// Compose UI Test Manifest - support for test manifests in Compose
    debugImplementation("androidx.compose.ui:ui-test-manifest")

// ViewModel Compose - ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

// Navigation Compose - navigation component for Compose applications
    implementation("androidx.navigation:navigation-compose:2.6.0")

// Coil for Compose - image loading library for Compose
    implementation("io.coil-kt:coil-compose:2.2.0")
// Data Store
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}