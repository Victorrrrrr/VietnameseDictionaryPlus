plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.gp.mod_demo"
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.applicationid.get()
        minSdk = libs.versions.minsdk.get().toInt()
        targetSdk = libs.versions.targetsdk.get().toInt()
        versionCode = libs.versions.versioncode.get().toInt()
        versionName = libs.versions.versionname.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
    }


    buildFeatures {
        viewBinding = true
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

    // ARouter
    kapt {
        arguments {
            arg("AROUTER_MODULE_NAME", project.getName())
        }
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // 路由
    implementation(libs.arouter.api)
    kapt(libs.arouter.compiler)

    implementation(libs.mmkv)

    implementation(libs.multidex)

    implementation(project(":lib_framework"))
    implementation(project(":lib_starter"))
    implementation(project(":lib_common"))
    implementation(project(":lib_network"))
    implementation(project(":lib_room"))
    implementation(project(":lib_widget"))
    implementation(project(":lib_starter"))
    implementation(project(":mod_learn"))
    implementation(project(":mod_login"))
    implementation(project(":mod_main"))
    implementation(project(":mod_recite"))
    implementation(project(":mod_search"))
    implementation(project(":mod_user"))
}