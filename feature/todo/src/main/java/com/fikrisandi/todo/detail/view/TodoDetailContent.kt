package com.fikrisandi.todo.detail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.framework.extension.toDaysWithMontAndYear
import com.fikrisandi.framework.extension.toHoursAndMinute
import com.fikrisandi.model.dto.todo.TodoDto
import com.fikrisandi.provider.EmptyNavigationProvider
import com.fikrisandi.provider.NavigationProvider
import com.fikrisandi.theme.R
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailContent(
    modifier: Modifier = Modifier,
    navController: NavigationProvider,
    state: TodoDto,
    onDeleteClick: () -> Unit = {},
    onUpdateClick: () -> Unit = {},
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = TodosColors.background
                        )
                    }
                },

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = TodosColors.primary)
            )
        }
    )
    {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.elevatedCardColors(containerColor = TodosColors.onPrimaryContainer)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = state.title,
                            style = TodosTypography.displayLarge,
                            color = TodosColors.background,
                        )
                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = state.description,
                            style = TodosTypography.bodyLarge,
                            color = TodosColors.background
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val timeFormatting =
                                Instant.fromEpochMilliseconds(state.dueDate).toLocalDateTime(
                                    TimeZone.currentSystemDefault()
                                )
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(
                                    text = "Due Date",
                                    style = TodosTypography.labelMedium,
                                    color = TodosColors.background
                                )
                                Text(
                                    text = "${timeFormatting.toDaysWithMontAndYear()} ${timeFormatting.toHoursAndMinute()}",
                                    style = TodosTypography.bodyLarge,
                                    color = TodosColors.background
                                )
                            }
                            Column() {
                                Text(
                                    text = "status",
                                    style = TodosTypography.labelMedium,
                                    color = TodosColors.background
                                )
                                Text(
                                    text = "Completed",
                                    style = TodosTypography.bodyLarge,
                                    color = TodosColors.background
                                )
                            }
                        }
                    }
                }

                TodoDetailActionContent(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onDeleteClick = onDeleteClick,
                    onUpdateClick = onUpdateClick,
                )

            }
        }
    }
}

@Composable
@Preview
fun TodoDetailContentPreview() {
    TodoTheme {
        TodoDetailContent(
            state = TodoDto(
                id = 0, title = "create new task", description = stringResource(
                    id = R.string.default_string
                ), dueDate = System.currentTimeMillis()
            ),
            navController = EmptyNavigationProvider()
        )
    }
}