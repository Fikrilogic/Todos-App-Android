package com.fikrisandi.todoapps

import com.fikrisandi.home.HomeNavGraph
import com.fikrisandi.todo.TodoNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object RootNavGraph : NavGraphSpec {
    override val destinationsByRoute: Map<String, DestinationSpec<*>> = emptyMap()
    override val route: String = "root"
    override val startRoute: Route = HomeNavGraph
    override val nestedNavGraphs: List<NavGraphSpec> = listOf(
        HomeNavGraph,
        TodoNavGraph
    )

}