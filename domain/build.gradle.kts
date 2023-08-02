import extension.FRAMEWORK
import extension.LOCAL
import extension.MODEL
import extension.REPOSITORY

plugins {
    id("commons.android-core")
    id("commons.dagger-hilt")
}

android{
    namespace = "com.fikrisandi.domain"
}



dependencies {
    FRAMEWORK
    MODEL
    LOCAL
    REPOSITORY

    implementation(Paging3Libs.runtime)
}