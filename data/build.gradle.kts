plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.serialization)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.kkjang.data"
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
    }

    flavorDimensions += "version"
    productFlavors {
        register("dev") {
            buildConfigField("String","PET_API","\"https://fdev-gateway.piece.la/v3/\"")
        }
        register("stage") {
            buildConfigField("String","PET_API","\"https://fdev-gateway.piece.la/v3/\"")
        }
        register("prod") {
            buildConfigField("String","PET_API","\"https://fdev-gateway.piece.la/v3/\"")
        }
    }


}

dependencies {

    implementation(project(":domain"))
    implementation(libs.multidex)
    coreLibraryDesugaring(libs.desugar.jdk)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.dagger.hilt)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.navigation.fragment)
    kapt(libs.dagger.hilt.comiler)

    implementation(libs.org.jetbrains.kotlinx.coroutines.android)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)

    api(libs.timber)

    // Room
    implementation(libs.room.common)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.rxjava2)
    implementation(libs.room.rxjava3)
    implementation(libs.room.guava)
    implementation(libs.room.testing)
    implementation(libs.room.paging)

    implementation(libs.okhttp3)
    implementation(libs.okhttp.urlconnection)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.gson)

    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.converter)
    implementation(libs.retrofit.scalars)
    implementation(libs.retrofit.simplexml)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.adapter.rxjava2)
    implementation(libs.retrofit.jaxb)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}