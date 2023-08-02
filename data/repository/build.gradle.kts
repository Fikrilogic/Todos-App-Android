import extension.FRAMEWORK
import extension.LOCAL
import extension.MODEL

plugins {
    id("commons.android-core")
    id("commons.dagger-hilt")
}

android{
    namespace = "com.fikrisandi.repository"
}


dependencies {
    FRAMEWORK
    LOCAL
    MODEL
}