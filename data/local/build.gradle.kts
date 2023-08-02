import extension.FRAMEWORK
import extension.MODEL
import extension.UTILS
import extension.ksp

plugins {
    id("commons.android-core")
    id("commons.dagger-hilt")
    id("com.google.devtools.ksp")
}

android{
    namespace = "com.fikrisandi.local"
}


dependencies {
    FRAMEWORK
    MODEL
    UTILS
    implementation(StorageLibs.roomKtx)
    implementation(StorageLibs.roomRuntime)
    ksp(StorageLibs.roomCompiler)
}