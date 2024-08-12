plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.serialization)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.kkjang.petconnect"

    signingConfigs {
        create("release") {
            storePassword = "petconnect001!@"
            keyAlias = "petconnect"
            keyPassword = "petconnect001!@"
            storeFile = file("../keystore")
        }
    }

    compileSdk = 34

    defaultConfig {
        applicationId = "com.kkjang.petconnect"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
        renderscriptTargetApi = 21
        renderscriptSupportModeEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        all {
            isCrunchPngs = false
        }

        getByName("debug") {
            isDebuggable = true
            isShrinkResources = false
            isMinifyEnabled = false
            multiDexEnabled = true
            signingConfig = signingConfigs.getByName("debug")

            proguardFiles(getDefaultProguardFile("proguard-android.txt"),"proguard-rules.pro")
            buildConfigField("boolean","DEBUG_VALUE","false")
        }

        getByName("release") {
            isDebuggable = false
            isShrinkResources = true
            isMinifyEnabled = true
            multiDexEnabled = true
            signingConfig = signingConfigs.getByName("release")
            multiDexKeepProguard = file("multidex-config.pro")
            proguardFiles(getDefaultProguardFile("proguard-android.txt"),"proguard-rules.pro")
            buildConfigField("boolean","DEBUG_VALUE","true")
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            manifestPlaceholders["app_label"] = "PetConnect(Dev)"
        }

        create("stage") {
            dimension = "version"
            applicationIdSuffix = ".stage"
            manifestPlaceholders["app_label"] = "PetConnect(Stage)"
        }

        create("prod") {
            dimension = "version"
            manifestPlaceholders["app_label"] = "PetConnect"
        }
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("x86","x86_64","armeabi-v7a","arm64-v8a")
            isUniversalApk = false
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += setOf(
                "META-INF/DEPENDENCIES",
                "META-INF/DEPENDENCIES.txt",
                "META-INF/dependencies.txt",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/LGPL2.1",
                "META-INF/ASL2.0",
                "/META-INF/{AL2.0,LGPL2.1}",
                "build.xml",
                "META-INF/rxjava.properties",
                "META-INF/proguard/androidx-annotations.pro",
            )
        }
    }


}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.android.support.multidex)
    implementation(libs.multidex)
    coreLibraryDesugaring(libs.desugar.jdk)

    // Default
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    // AndroidX Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.dynamic.feature.fragment)
    implementation(libs.navigation.testing)

    // Hilt
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.navigation.fragment)
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.comiler)


    // AndroidX
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.dagger.hilt)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Coil
    implementation(libs.coil)

    // Timber
    implementation(libs.timber)

    // Etc Lib
    implementation(libs.google.play.services.location)
    implementation(libs.ted.park.permission)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}