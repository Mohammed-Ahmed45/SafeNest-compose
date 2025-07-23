plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
//    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mohamed.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Navigation
    implementation(libs.androidx.navigation.compose)
    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //viewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.glide)

    kapt(libs.compiler)
    implementation(libs.coil.compose)


    // Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // pager
    implementation(libs.accompanist.pager)

    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.core:core-ktx:1.12.0")

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    // Firebase Messaging
    implementation("com.google.firebase:firebase-messaging:24.0.0")

    implementation(libs.work.runtime.ktx)

    // For notification handling
    implementation("androidx.core:core-ktx:1.16.0")
    // Your existing Compose and other dependencies...
    implementation("androidx.compose.runtime:runtime-livedata:1.8.2")

    // For SharedPreferences
    implementation("androidx.preference:preference-ktx:1.2.1")
}

kapt {
    correctErrorTypes = true
}