plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.gp.vietnamesedictionaryplus"
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.applicationid.get()
        minSdk = libs.versions.minsdk.get().toInt()
        targetSdk = libs.versions.targetsdk.get().toInt()
        versionCode = libs.versions.versioncode.get().toInt()
        versionName = libs.versions.versionname.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true

        // ARouter
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.getName())
            }
        }
    }
    dexOptions {
        javaMaxHeapSize = "4g"
    }

//    signingConfigs {
//        getByName("debug") {
//            keyAlias = "debug"
//            keyPassword = "my debug key password"
//            storeFile = file("/home/miles/keystore.jks")
//            storePassword = "my keystore password"
//        }
//        create("release") {
//            keyAlias = "release"
//            keyPassword = "my release key password"
//            storeFile = file("/home/miles/keystore.jks")
//            storePassword = "my keystore password"
//        }
//    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            signingConfig = signingConfigs.getByName("release")
//            isDebuggable = false
        }
        debug {
//            signingConfig = signingConfigs.getByName("debug")
//            isDebuggable = true
        }
    }

    //jniLibs目录指向libs目录
    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(files("libs/online_auth.jar"))
    implementation(files("libs/YoudaoBase_v20230803.jar"))
    implementation(files("libs/YoudaoTranslateOnline_v2.0.1.jar"))
    implementation(files("libs/zhiyun_offline_common.jar"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(files("libs/YoudaoBase_v20230803.jar") )
    implementation(files("libs/online_auth.jar"))
    implementation(files("libs/zhiyun_offline_common.jar"))
    implementation(files("libs/YoudaoTranslateOnline_v2.0.1.jar"))

    // 路由
    implementation(libs.arouter.api)
    kapt(libs.arouter.compiler)

    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.common)

    implementation(libs.retrofit2)
    implementation(libs.retrofit2.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    implementation(libs.flexbox)

    implementation(libs.room)

    implementation(libs.mmkv)

    implementation(libs.multidex)

    implementation(libs.circleimageview)

    implementation(libs.glide)
    kapt(libs.glide.compiler)

    implementation(libs.ffmpeg.core)
    implementation(libs.ffmpeg.native)

    implementation(libs.jtransforms)

    implementation(project(":lib_framework"))
    implementation(project(":lib_starter"))
    implementation(project(":lib_common"))
    implementation(project(":lib_network"))
    implementation(project(":lib_room"))
    implementation(project(":lib_widget"))
    implementation(project(":lib_glide"))
    implementation(project(":lib_starter"))
    implementation(project(":mod_learn"))
    implementation(project(":mod_login"))
    implementation(project(":mod_main"))
    implementation(project(":mod_recite"))
    implementation(project(":mod_search"))
    implementation(project(":mod_user"))


}