package com.fikrisandi.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.fikrisandi.component.widget.TodosCardVerticalWidget
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.theme.TodosTypography
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeTodosListHorizontal(modifier: Modifier = Modifier, todoFlowPaging: Flow<PagingData<TodoDto>>) {
    val listTodo = todoFlowPaging.collectAsLazyPagingItems()
    val pagedStateLastAdded = rememberPagerState{listTodo.itemCount}

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
                TextButton(onClick = {}) {
                    Text(text = "View More", style = TodosTypography.labelLarge)
                }
            }
        }

        HorizontalPager(state = pagedStateLastAdded) {index ->
            listTodo[index]?.let{
                TodosCardVerticalWidget(
                    modifier = Modifier.fillMaxWidth(1f),
                    title = it.title, date = it.dueDate.toString(), description = it.description
                )
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
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