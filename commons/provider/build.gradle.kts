import extension.MODEL

plugins {
    id("commons.android-core")
    id("commons.android-compose")
}

android{
    namespace = "com.fikrisandi.provider"
}


dependencies {
    MODEL
}