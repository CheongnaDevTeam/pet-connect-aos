plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.jvm) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
    alias(libs.plugins.androidx.navigation.safeargs.kotlin) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.org.jetbrains.kotlin.kapt) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
}