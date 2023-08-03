package com.fikrisandi.todo.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.fikrisandi.component.widget.TodosCardVerticalWidget
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography
import com.fikrisandi.todo.list.TodoListState
import kotlinx.coroutines.flow.flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListContent(modifier: Modifier = Modifier, state: TodoListState) {

    val listData = state.pagedData.collectAsLazyPagingItems()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text("Todo List", style = TodosTypography.displayLarge)
            },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = TodosColors.primary
                        )
                    }
                })
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                FilledTonalIconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add New Todo",
                            tint = TodosColors.primary,
                        )
                        Text(
                            text = "Create New Todo",
                            style = TodosTypography.labelLarge,
                            color = TodosColors.primary
                        )
                    }
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(TodosColors.background)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(listData.itemCount) { index ->
                    listData[index].let { todo ->
                        TodosCardVerticalWidget(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            title = todo?.title.orEmpty(),
                            date = todo?.dueDate.toString(),
                            description = todo?.description.orEmpty()
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun TodoListContentPreview() {
    val listTodo = listOf(
        TodoDto(
            id = 1,
            title = "Todo Sample",
            description = "test description data",
            dueDate = System.currentTimeMillis()
        ),
        TodoDto(
            id = 2,
            title = "Todo Sample",
            description = "test description data",
            dueDate = System.currentTimeMillis()
        ),
        TodoDto(
            id = 2,
            title = "Todo Sample",
            description = "test description data",
            dueDate = System.currentTimeMillis()
        ),
        TodoDto(
            id = 2,
            title = "Todo Sample",
            description = "test description data",
            dueDate = System.currentTimeMillis()
        ),
        TodoDto(
            id = 2,
            title = "Todo Sample",
            description = "test description data",
            dueDate = System.currentTimeMillis()
        ),
        TodoDto(
            id = 2,
            title = "Todo Sample",
            description = "test description data",
            dueDate = System.currentTimeMillis()
        ),
    )
    TodoTheme {
        TodoListContent(
            state = TodoListState(
                pagedData = flow {
                    PagingData.from(listTodo)
                }
            )
        )
    }
}