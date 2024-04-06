pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {setUrl("https://jitpack.io")}
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io")}
    }
}

rootProject.name = "VietnameseDictionaryPlus"
include(":app")
include(":mod_login")
include(":mod_main")
include(":mod_search")
include(":mod_learn")
include(":mod_user")
include(":lib_network")
include(":lib_common")
include(":lib_framework")
include(":mod_recite")
include(":lib_room")
include(":lib_widget")
include(":lib_starter")
include(":mod_demo")
include(":lib_glide")
