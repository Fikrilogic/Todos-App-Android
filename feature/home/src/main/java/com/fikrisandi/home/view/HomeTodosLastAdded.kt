package com.fikrisandi.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.fikrisandi.component.widget.TodoCardHorizontalEmpty
import com.fikrisandi.component.widget.TodosCardVerticalWidget
import com.fikrisandi.framework.base.BaseUiState
import com.fikrisandi.framework.extension.cast
import com.fikrisandi.home.HomeState
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.theme.TodosTypography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTodosLastAdded(
    modifier: Modifier = Modifier,
    state: BaseUiState<*>,
    onShowMore: () -> Unit = {},
    onShowDetailTodo: (TodoDto) -> Unit = {}
) {

    Column(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "On Proggress", style = TodosTypography.headlineSmall)
                Text(text = "(10)", style = TodosTypography.headlineSmall)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.5f),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onShowMore) {
                    Text(text = "View More", style = TodosTypography.labelLarge)
                }
            }
        }

        when (state) {
            is BaseUiState.Loading -> {}
            is BaseUiState.Empty -> {
                HorizontalPager(state = rememberPagerState { 1 }) {
                    TodoCardHorizontalEmpty(modifier = Modifier.fillMaxWidth().height(150.dp))
                }

            }
            is BaseUiState.Data -> {
                val data = state.cast<BaseUiState.Data<HomeState>>().value
                val listTodo = data.listTodoAdded.collectAsLazyPagingItems()
                val pagedStateLastAdded = rememberPagerState { listTodo.itemCount }
                if(listTodo.itemCount == 0){
                    HorizontalPager(state = rememberPagerState { 1 }) {
                        TodoCardHorizontalEmpty(modifier = Modifier.fillMaxWidth().height(150.dp))
                    }
                } else {
                    HorizontalPager(state = pagedStateLastAdded) { index ->
                        listTodo[index]?.let {
                            TodosCardVerticalWidget(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .heightIn(min = 150.dp)
                                    .clickable { onShowDetailTodo(it) },
                                title = it.title, date = it.dueDate, description = it.description
                            )
                        }
                    }
                }
            }

            is BaseUiState.Error -> {
                HorizontalPager(state = rememberPagerState { 1 }) {
                    TodoCardHorizontalEmpty(modifier = Modifier.fillMaxWidth().height(150.dp))
                }
            }
        }



    }
}

@Preview
@Composable
fun HomeTodosListHorizontalPreview() {
//    HomeTodosListHorizontal(
//        modifier = Modifier.fillMaxSize(),
//        pagerState = rememberPagerState {
//            10
//        },
//        flowOf(PagingData.empty<TodoDto>()).collectAsLazyPagingItems()
//    )
}