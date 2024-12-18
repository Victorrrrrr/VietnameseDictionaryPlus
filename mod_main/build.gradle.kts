plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.gp.main"
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

    buildFeatures {
        dataBinding = true
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
        jvmTarget = "1.8"
    }
}

dependencies {

    compileOnly(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    compileOnly(libs.core.ktx)
//    compileOnly(libs.material)
//    compileOnly(libs.appcompat)
//    compileOnly(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)

    testCompileOnly(libs.junit)
    androidTestCompileOnly(libs.androidx.test.ext.junit)
    androidTestCompileOnly(libs.espresso.core)

    compileOnly(libs.lifecycle.livedata.ktx)
    compileOnly(libs.lifecycle.viewmodel.ktx)

    compileOnly(libs.ffmpeg.core)
    compileOnly(libs.ffmpeg.native)

    // 路由
    compileOnly(libs.arouter.api)
    kapt(libs.arouter.compiler)

    // 底部导航
    compileOnly(libs.navigation.ui.ktx)
    compileOnly(libs.navigation.fragment.ktx)

    compileOnly(libs.glide)
    kapt(libs.glide.compiler)

    // 动画
    compileOnly(libs.lottie)

    compileOnly(files("libs/online_auth.jar"))
    compileOnly(files("libs/YoudaoBase_v20230803.jar"))
    compileOnly(files("libs/YoudaoTranslateOnline_v2.0.1.jar"))
    compileOnly(files("libs/zhiyun_offline_common.jar"))

    compileOnly(libs.mmkv)

    compileOnly(project(":lib_common"))
    compileOnly(project(":lib_framework"))
    compileOnly(project(":lib_starter"))
    compileOnly(project(":lib_network"))
    compileOnly(project(":lib_room"))
    compileOnly(project(":lib_widget"))
    compileOnly(project(":lib_glide"))

}