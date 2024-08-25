plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.serialization)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.androidx.navigation.safeargs)
}

android {
    namespace = "dev.kkjang.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Hilt
    kapt(libs.dagger.hilt.comiler)
    implementation(libs.dagger.hilt)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.navigation.fragment)

    // Okhttp
    implementation(libs.okhttp3)
    implementation(libs.okhttp.urlconnection)
    implementation(libs.okhttp.logging.interceptor)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.converter)
    implementation(libs.retrofit.scalars)
    implementation(libs.retrofit.simplexml)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.adapter.rxjava2)
    implementation(libs.retrofit.jaxb)
    implementation(libs.gson)

    // room
    implementation(libs.room)
    implementation(libs.room.common)
    implementation(libs.room.ktx)
    implementation(libs.room.compiler)
    implementation(libs.room.rxjava2)
    implementation(libs.room.rxjava3)
    implementation(libs.room.guava)
    implementation(libs.room.testing)
    implementation(libs.room.paging)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}