package com.fikrisandi.todoapps.provider

import androidx.navigation.NavController
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.provider.TodoFlagType
import com.fikrisandi.todo.destinations.TodoDetailScreenDestination
import com.fikrisandi.todo.destinations.TodoListScreenDestination
import com.ramcosta.composedestinations.navigation.navigate

class AppNavigationProvider(private val navController: NavController) : NavigationProvider {
    override fun navigateUp() {
        navController.popBackStack()
    }

    override fun openTodoList(flag: TodoFlagType) {
        navController.navigate(TodoListScreenDestination.invoke(flag = flag))
    }

    override fun openTodoDetail(state: TodoDto) {
        navController.navigate(TodoDetailScreenDestination.invoke(state = state))
    }
}