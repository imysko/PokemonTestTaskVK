plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.dagger.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.imysko.pokemontesttaskvk"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.imysko.pokemontesttaskvk"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.converter.scalars)
    implementation(libs.okhttp)
    implementation(libs.okhttp.coroutines)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.coil.gif)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.dagger.hilt.android)
    implementation(libs.hilt.navigation.compose)

    ksp(libs.dagger.hilt.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.assertj.core)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}