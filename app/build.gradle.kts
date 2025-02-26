plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtoolsKsp)

    kotlin("plugin.serialization") version "2.1.10"
}

android {
    namespace = "com.example.lpg.android"
    compileSdk = 35

    //customize build apk output
    applicationVariants.all {
        val variant = this
        outputs.all {
            val output = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            val versionName = variant.versionName
            val variantName = variant.name

            output.outputFileName = "apk-$versionName-$variantName.apk"
        }
    }

    defaultConfig {
        applicationId = "com.example.lpg.android"
        minSdk = 24
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.retrofit) // retrofit
    implementation(libs.converter.gson) // converter gson

    implementation(libs.coil.compose) // coil
    implementation(libs.coil.network.okhttp) // coil net http

    implementation(libs.androidx.navigation.compose) // navigation

    implementation(libs.androidx.security.crypto) //for encrypted shared prefs

    implementation(libs.kotlinx.serialization.json) // serialization json

    implementation(libs.retrofit) // retrofit
    implementation(libs.converter.gson) // retrofit - gson

    implementation(libs.androidx.paging.runtime.ktx) // paging runtime
    implementation(libs.androidx.paging.compose) // paging compose

    implementation(libs.androidx.room.runtime)  //room
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // ViewModel // LiveData // Lifecycles 
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.runtime.livedata)

    implementation(libs.android.database.sqlcipher) // sqlcipher

    implementation(libs.androidx.constraintlayout.compose) // constraintlayout


    implementation(libs.androidx.datastore.preferences) // datastore


}