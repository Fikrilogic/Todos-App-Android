object Config {

    const val id = "com.fikrisandi.todoapps"
    const val minSdk = 21
    const val maxSdk = 34
    const val compileSdk = 34
    const val versionCode = 1
    const val versionName = "1.0"

    const val androidJunitRunner =  "androidx.test.runner.AndroidJUnitRunner"

    val FreeCompilerArgs = listOf(
        "-Xjvm-default=all",
        "opt-in=kotlin.RequiresOptIn",
        "opt-in=kotlin.Experimental",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview",
        "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi",
        "-Xopt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
        "-Xopt-in=androidx.compose.animation.ExperimentalAnimationApi"
    )

    val FreeCoroutineCompilerArgs = listOf(
        "-Xjvm-default=all",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlin.Experimental",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview"
    )

    object Release{

    }

    object Debug{
        const val DbName = "TODO_DB"
    }
}