package com.fikrisandi.component.widget

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
fun TodosCardVerticalWidget(
    modifier: Modifier = Modifier,
    title: String,
    date: String,
    description: String
) {
    OutlinedCard(
        modifier = modifier
            .padding(8.dp)
            .wrapContentHeight(),
        border = BorderStroke(2.dp, color = TodosColors.primary)
    ) {
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
                ) {
                    Text(text = title, style = TodosTypography.titleLarge)
                    Text(text = date, style = TodosTypography.titleSmall)
                }

                Column(modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)) {
                    Box(
                        modifier = Modifier
                            .sizeIn(maxWidth = 70.dp, maxHeight = 70.dp)
                            .clip(CircleShape)
                            .background(color = TodosColors.primary)
                            .padding(8.dp), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_todos_people),
                            contentDescription = "todos_icon"
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 1.dp)
                    .background(color = TodosColors.primary)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Description",
                    style = TodosTypography.labelMedium,
                )
                Text(
                    text = description,
                    style = TodosTypography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@Composable
@Preview
fun CardVerticalWidgetPreview() {
    TodoTheme {
        TodosCardVerticalWidget(
            modifier = Modifier
                .width(200.dp)
                .wrapContentHeight(),
            title = "Todos Today",
            date = "12/07/2022",
            description = stringResource(id = R.string.default_string)
        )
    }
}