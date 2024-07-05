plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.forex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.forex"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.loopj.android:android-async-http:1.4.9")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}