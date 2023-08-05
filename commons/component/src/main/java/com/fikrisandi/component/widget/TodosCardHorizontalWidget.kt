package com.fikrisandi.component.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.framework.extension.toDaysWithMontAndYear
import com.fikrisandi.framework.extension.toHoursAndMinute
import com.fikrisandi.theme.R
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun TodosCardHorizontalWidget(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    time: Long,
) {
    OutlinedCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.4f)
                ) {
                    Text(text = title, style = TodosTypography.titleLarge)
                    Text(
                        text = description,
                        style = TodosTypography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(.2f),
                    horizontalAlignment = Alignment.End
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_circle_check),
                        contentDescription = "Completed",
                        tint = TodosColors.secondary
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 1.dp)
                    .background(color = TodosColors.primary)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val timeFormatting = Instant.fromEpochMilliseconds(time).toLocalDateTime(TimeZone.currentSystemDefault())
                Text(
                    text =  timeFormatting.toDaysWithMontAndYear(),
                    style = TodosTypography.bodySmall,
                )
                Text(
                    text = timeFormatting.toHoursAndMinute(),
                    style = TodosTypography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun TodoCardHorizontalEmpty(
    modifier: Modifier = Modifier,
) {
    OutlinedCard(modifier = modifier) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Data is Empty")
        }
    }
}

@Composable
@Preview
fun TodoCardHorizontalEmptyPreview(
) {
    TodoCardHorizontalEmpty(modifier = Modifier.fillMaxWidth().height(200.dp) )
}

@Composable
@Preview
fun CardHorizontalWidgetPreview() {
    TodoTheme {
        TodosCardHorizontalWidget(
            modifier = Modifier.fillMaxWidth(),
            title = "Go To Station",
            time = 1691280000000L,
            description = stringResource(
                R.string.default_string
            )
        )
    }
}