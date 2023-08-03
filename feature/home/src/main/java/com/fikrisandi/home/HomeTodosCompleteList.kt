package com.fikrisandi.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.component.widget.TodosCardHorizontalWidget
import com.fikrisandi.component.widget.TodosCardVerticalWidget
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.theme.R
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosTypography

@Composable
fun HomeTodosCompleteList(modifier: Modifier = Modifier, listTodo: List<TodoDto>, onShowMore: () -> Unit){

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
                Text(text = "Completed", style = TodosTypography.headlineSmall)
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

        Column(modifier = Modifier.fillMaxWidth()) {
            listTodo.forEach { data ->
                TodosCardHorizontalWidget(modifier = Modifier.padding(vertical = 10.dp),title = data.title, description =data.description, days = "Today", time = "15.40")
            }
        }

    }
}

@Composable
@Preview
fun HomeTodosCompleteListPreview(){
    val listTodo = listOf(
        TodoDto(id = 1, title = "Build Feature", description = stringResource(R.string.default_string), dueDate = System.currentTimeMillis()),
        TodoDto(id = 2, title = "Checking Bugs", description = stringResource(R.string.default_string), dueDate = System.currentTimeMillis())
    )

    TodoTheme {
        HomeTodosCompleteList(
            modifier = Modifier.fillMaxWidth(),
            listTodo = listTodo,
        ){

        }
    }
}