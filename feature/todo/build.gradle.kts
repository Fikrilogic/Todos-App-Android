import extension.*

plugins {
    id("commons.android-feature")
    id("commons.android-compose")
    id("commons.dagger-hilt")
    id("com.google.devtools.ksp")
}

android{
    namespace = "com.fikrisandi.todo"
}

ksp{
    arg("compose-destinations.mode", "navgraphs")
    arg("compose-destinations.moduleName", "todo")
}

dependencies {
    JETFRAMEWORK
    FRAMEWORK
    MODEL
    DOMAIN
    PROVIDER
    THEME
    COMPONENT

    addPaging3Dependency()
    addDestinationDependency()
    implementation(DaggerHiltLibs.compose)
}