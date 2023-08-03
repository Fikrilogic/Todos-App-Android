package com.fikrisandi.todo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoContentSheetScreen(modifier: Modifier = Modifier) {

    val dateState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "New Todo",
                    style = TodosTypography.headlineMedium,
                    color = TodosColors.primary
                )
            })
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Title", style = TodosTypography.labelLarge, color = TodosColors.primary)
                    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Add Title", color = TodosColors.primary) })
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Description",
                        style = TodosTypography.labelLarge,
                        color = TodosColors.primary
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Add Description", color = TodosColors.primary) },
                        maxLines = 4
                    )
                }

                DatePicker(state = dateState, modifier = Modifier.fillMaxWidth(), headline = {
                    Text(
                        "Select Date",
                        style = TodosTypography.displayMedium,
                        color = TodosColors.primary
                    )
                }, title = {
                    Text(
                        "Due Date",
                        style = TodosTypography.labelLarge,
                        color = TodosColors.primary
                    )
                })
            }
        }
    }
}

@Composable
@Preview
fun TodoContentSheetScreenPreview() {
    TodoTheme {
        TodoContentSheetScreen(modifier = Modifier.fillMaxWidth())
    }
}