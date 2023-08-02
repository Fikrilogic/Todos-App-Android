import extension.FRAMEWORK
import extension.UTILS
import extension.ksp

plugins {
    id("commons.android-core")
    id("commons.dagger-hilt")
    id("com.google.devtools.ksp")
}

android{
    namespace = "com.fikrisandi.model"
}


dependencies {
    FRAMEWORK
    UTILS

    implementation(StorageLibs.roomKtx)
    ksp(StorageLibs.roomCompiler)
}