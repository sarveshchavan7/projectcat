import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    kotlin("kapt")
    id("kotlin-parcelize")
    id(libs.plugins.secrets.gradle.plugin.get().pluginId)
    id(libs.plugins.google.services.get().pluginId)
    id(libs.plugins.firebase.crashlytics.get().pluginId)
}

android {
    namespace = "com.catcompany.projectcat"
    compileSdk = 34

    secrets {
        propertiesFileName = "local.properties"
    }
    defaultConfig {
        applicationId = "com.catcompany.projectcat"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()

        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "CAT_API_KEY", properties.getProperty("CAT_API_KEY"))
        buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/\"")
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
        dataBinding = true
        buildConfig = true
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
    implementation(libs.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)

    // Glide
    implementation(libs.glide)
    kapt(libs.glide.complier)

    // LiveData
    implementation(libs.livedata)

    // Activity ktx
    implementation(libs.activity.ktx)

    // RecyclerView
    implementation(libs.recyclerview)

    // Gson
    implementation(libs.gson)

    // Lifecycle
    implementation(libs.androidx.lifecycle)

    // Coroutines
    implementation(libs.coroutines)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    // Layout
    implementation(libs.constraintlayout)
    implementation(libs.material)

    // Chuck
    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    // Paging 3
    implementation(libs.paging)

    // Dependent
    implementation(project(":breedlist"))
    implementation(project(":base"))
    implementation(project(":analytics"))
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}