@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.gp.glide"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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

    compileOnly(libs.core.ktx)
    compileOnly(libs.appcompat)
    compileOnly(libs.material)
    testCompileOnly(libs.junit)
    androidTestCompileOnly(libs.androidx.test.ext.junit)
    androidTestCompileOnly(libs.espresso.core)

    compileOnly(libs.glide)
    kapt(libs.glide.compiler)

    compileOnly(project(":lib_framework"))


}