plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.gp.mod_recite"
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minsdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        // ARouter
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.getName())
            }
        }
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
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

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testCompileOnly(libs.junit)
    androidTestCompileOnly(libs.androidx.test.ext.junit)
    androidTestCompileOnly(libs.espresso.core)

    // 路由
    compileOnly(libs.arouter.api)
    kapt(libs.arouter.compiler)

    compileOnly(project(":lib_common"))
    compileOnly(project(":lib_framework"))
    compileOnly(project(":lib_starter"))
    compileOnly(project(":lib_network"))
    compileOnly(project(":lib_room"))
    compileOnly(project(":lib_widget"))
    compileOnly(project(":lib_glide"))
}