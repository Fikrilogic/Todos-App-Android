package com.fikrisandi.component.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.theme.R
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography

@Composable
fun TodosCardHorizontalWidget(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    days: String,
    time: String
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
                    Icon(painter = painterResource(R.drawable.ic_circle_check), contentDescription = "Completed", tint = TodosColors.secondary)
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
                Text(
                    text = days,
                    style = TodosTypography.bodySmall,
                )
                Text(
                    text = time,
                    style = TodosTypography.bodySmall,
                )
            }
        }
    }
}

@Composable
@Preview
fun CardHorizontalWidgetPreview() {
    TodoTheme {
        TodosCardHorizontalWidget(
            modifier = Modifier.fillMaxWidth(),
            title = "Go To Station",
            time = "11:24",
            days = "Today",
            description = stringResource(
                R.string.default_string
            )
        )
    }
}