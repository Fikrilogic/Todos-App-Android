import extension.THEME
import extension.FRAMEWORK

plugins {
    id("commons.android-core")
    id("commons.android-compose")
}

android {
    namespace = "com.fikrisandi.component"

}

dependencies {
    THEME
    FRAMEWORK
}