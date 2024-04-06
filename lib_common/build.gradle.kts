plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.gp.lib_common"
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minsdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.getName())
            }
        }

    }

    buildFeatures {
        dataBinding = true
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
    compileOnly(libs.constraintlayout)
    compileOnly(libs.gson)

    compileOnly(libs.lifecycle.viewmodel.ktx)
    compileOnly(libs.lifecycle.livedata.ktx)

    compileOnly(libs.arouter.api)
    kapt(libs.arouter.compiler)

    compileOnly(libs.mmkv)
}