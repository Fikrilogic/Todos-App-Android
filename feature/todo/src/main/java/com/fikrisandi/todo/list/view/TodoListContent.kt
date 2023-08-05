package com.fikrisandi.todo.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.fikrisandi.component.widget.TodoEmptyData
import com.fikrisandi.component.widget.TodosCardVerticalWidget
import com.fikrisandi.framework.base.BaseUiState
import com.fikrisandi.framework.extension.cast
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography
import com.fikrisandi.todo.list.TodoListState
import kotlinx.coroutines.flow.flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListContent(
    modifier: Modifier = Modifier,
    state: BaseUiState<*>,
    navController: NavigationProvider = EmptyNavigationProvider(),
    openBottomSheet: () -> Unit = {},
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Todo List",
                        style = TodosTypography.displayLarge,
                        color = TodosColors.background
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = TodosColors.background
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = TodosColors.primary,
                    titleContentColor = TodosColors.background
                )
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                FilledTonalIconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = openBottomSheet,
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
            when (state) {
                is BaseUiState.Empty -> {
                    TodoEmptyData(modifier = Modifier.fillMaxSize())
                }

                is BaseUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            modifier = Modifier.size(200.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(70.dp),
                                strokeWidth = 4.dp
                            )
                        }
                    }
                }

                is BaseUiState.Error -> {
                    TodoEmptyData(modifier = Modifier.fillMaxSize())
                }

                is BaseUiState.Data -> {
                    val listTodo =
                        state.cast<BaseUiState.Data<TodoListState>>().value.pagedData.collectAsLazyPagingItems()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        items(listTodo.itemCount) { index ->
                            listTodo[index]?.let { todo ->
                                TodosCardVerticalWidget(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .clickable {
                                            navController.openTodoDetail(todo)
                                        },
                                    title = todo.title,
                                    date = todo.dueDate,
                                    description = todo.description
                                )
                            }
                        }
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
            state = BaseUiState.Data<TodoListState>(TodoListState(
                pagedData = flow {
                    PagingData.from(listTodo)
                }
            ))
        )
    }
}