// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.androidLibrary) apply false
    id(libs.plugins.google.services.get().pluginId) version "4.4.1" apply false
    id(libs.plugins.firebase.crashlytics.get().pluginId) version "2.9.9" apply false
}
buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}