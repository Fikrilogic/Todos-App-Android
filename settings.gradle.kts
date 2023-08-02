pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "Todo Apps"
include(":app")
include(":data:local")
include(":data:model")
include(":data:repository")
include(":library:framework")
include(":feature:todo")
include(":library:jetframework")
include(":domain")
include(":library:utils")
include(":commons:theme")
include(":commons:provider")
include(":commons:component")
include(":feature:home")
