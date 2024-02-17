plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.gp.vietnamesedictionaryplus"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.gp.vietnamesedictionaryplus"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation(
        project(
            ":lib_framework"
        )
    )

    implementation(
        project(
            ":lib_starter"
        )
    )

    implementation(
        project(
            ":lib_common"
        )
    )

    implementation(
        project(
            ":lib_network"
        )
    )

    implementation(
        project(
            ":lib_room"
        )
    )

    implementation(
        project(
            ":lib_widget"
        )
    )

    implementation(
        project(
            ":lib_starter"
        )
    )

    implementation(
        project(
            ":mod_learn"
        )
    )

    implementation(
        project(
            ":mod_login"
        )
    )

    implementation(
        project(
            ":mod_main"
        )
    )

    implementation(
        project(
            ":mod_recite"
        )
    )

    implementation(
        project(
            ":mod_search"
        )
    )

    implementation(
        project(
            ":mod_user"
        )
    )


}