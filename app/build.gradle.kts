plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.safeargs.plugin)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kotlin.parcelize)

}

android {
    namespace = "com.mehdisekoba.filimo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mehdisekoba.filimo"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //Data Store
    implementation(libs.androidx.datastore.preferences)
    // Dagger - Hilt
    implementation(libs.hilt)
    ksp(libs.hiltcompiler)
    // Navigation
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // OkHTTP client
    implementation(libs.okhttp)
    implementation(libs.interceptor)
    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    // Lifecycle
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)
    // Image Loading
    implementation(libs.glide)

    // Gson
    implementation(libs.google.gson)
    // Calligraphy
    implementation(libs.calligraphy)
    implementation(libs.viewpump)
    //dynamic size
    implementation(libs.dynamicsizes)
    //shimmer
    implementation(libs.androidveil)
    //player
    implementation(libs.kohii.core)
    implementation(libs.kohii.exoplayer)
    implementation(libs.exoplayer)
    //notification
    implementation(libs.firebase.messaging)
    //permissions
    implementation(libs.permissionx)
    //log
    implementation(libs.timber)
    //decoration
    implementation (libs.decorator)
    //animation
    implementation (libs.lottie)


}