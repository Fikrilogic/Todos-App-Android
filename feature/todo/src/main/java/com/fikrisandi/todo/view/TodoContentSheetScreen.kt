package com.fikrisandi.todo.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.framework.extension.orZero
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoContentSheetScreen(
    modifier: Modifier = Modifier,
    state: TodoDto? = null,
    onCancel: () -> Unit,
    onSubmit: (TodoDto) -> Unit
) {

    var formState by remember { mutableStateOf(state ?: TodoDto()) }
    val dateState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input, initialSelectedDateMillis = state?.dueDate)

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
                .padding(it)
                .verticalScroll(state = rememberScrollState()),
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
                        value = formState.title,
                        onValueChange = {
                            formState = formState.copy(title = it)
                        },
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
                        value = formState.description,
                        onValueChange = { formState = formState.copy(description = it) },
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
                }, dateValidator = {
                    it >= System.currentTimeMillis()
                })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = onCancel,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.4f), shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, color = TodosColors.primary)
                    ) {
                        Text("Cancel", style = TodosTypography.labelMedium)
                    }
                    Spacer(modifier = Modifier.weight(.01f))
                    FilledTonalButton(
                        onClick = {
                            onSubmit.invoke(formState.copy(dueDate = dateState.selectedDateMillis.orZero()))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.4f), shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            if (state == null) "Create" else "Update",
                            style = TodosTypography.labelMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun TodoContentSheetScreenPreview() {
    TodoTheme {
        TodoContentSheetScreen(modifier = Modifier.fillMaxWidth(), state = null, {}, {})
    }
}