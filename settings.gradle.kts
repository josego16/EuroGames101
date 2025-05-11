rootProject.name = "EuroGames101"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
        maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlinx/") }
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
    }
}

include(":composeApp")