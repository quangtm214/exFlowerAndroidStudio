plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") version "4.4.1"}

android {
    namespace = "com.example.exflower"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.exflower"
        minSdk = 31
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")

    // Network & API
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)

    // Image Loading
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // Local Storage
    implementation(libs.datastore.preferences)

    // DateTime
    implementation(libs.threetenbp)

    // ViewPager
    implementation(libs.viewpager2)

    // Work Manager
    implementation(libs.work.runtime)

    // Image Picker
    implementation(libs.activity)
    implementation(libs.fragment)

    // Blur Effect
//    implementation("com.eightbitlab:blurview:1.6.6")

}