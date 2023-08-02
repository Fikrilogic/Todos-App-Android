// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0")
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}