package com.fikrisandi.todo.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.todo.list.view.TodoListContent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.flow.flow


@Composable
fun TodoListScreen(modifier: Modifier = Modifier,navController: DestinationsNavigator){
    val listTodo by remember{
        mutableStateOf(
            listOf(
                TodoDto(id = 1, title = "Todo Sample", description = "test description data", dueDate = "12/02/2023"),
                TodoDto(id = 2, title = "Todo Sample", description = "test description data", dueDate = "12/02/2023"),
                TodoDto(id = 2, title = "Todo Sample", description = "test description data", dueDate = "12/02/2023"),
                TodoDto(id = 2, title = "Todo Sample", description = "test description data", dueDate = "12/02/2023"),
                TodoDto(id = 2, title = "Todo Sample", description = "test description data", dueDate = "12/02/2023"),
                TodoDto(id = 2, title = "Todo Sample", description = "test description data", dueDate = "12/02/2023"),
            )
        )
    }
    TodoListContent(
        state = TodoListState(
            pagedData = flow {
                PagingData.from(listTodo)
            }
        )
    )
}