package commons

import com.android.build.api.dsl.BuildType
import extension.addCommonDependency
import extension.addComposeDependency
import org.gradle.kotlin.dsl.dependencies
import org.gradle.api.artifacts.dsl.DependencyHandler


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
}

android {

    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        testInstrumentationRunner = Config.androidJunitRunner

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures.buildConfig = true

    buildTypes {
        debug {
            buildConfigStringField("DB_NAME", Config.Debug.DbName)
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

}

android.libraryVariants.all {
    val variantName = name
    kotlin.sourceSets{
        getByName("main") {
            kotlin.srcDir(File("build/generated/ksp/$variantName/kotlin"))
        }
    }
}

fun BuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

dependencies {

    addCommonDependency()
}