plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gp.lib_network"
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minsdk.get().toInt()

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
        jvmTarget = libs.versions.jvmtarget.get()
    }
}

dependencies {

    compileOnly(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    compileOnly(libs.core.ktx)
    compileOnly(libs.appcompat)
    compileOnly(libs.material)

    compileOnly(libs.retrofit2)
    compileOnly(libs.retrofit2.gson)
    compileOnly(libs.logging.interceptor)

    compileOnly(libs.mmkv)

    compileOnly(libs.gson)
    compileOnly(libs.lifecycle.livedata.ktx)
    compileOnly(libs.lifecycle.viewmodel.ktx)

    compileOnly(project(":lib_framework"))
    compileOnly(project(":lib_common"))


}