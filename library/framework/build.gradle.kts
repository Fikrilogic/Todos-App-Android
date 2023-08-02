import extension.addHiltDependency
import extension.addStorageDependency

plugins {
    id("commons.android-core")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android{
    namespace = "com.fikrisandi.framework"
}

dependencies {

    addHiltDependency()
    addStorageDependency()
    implementation(Paging3Libs.runtime)
}