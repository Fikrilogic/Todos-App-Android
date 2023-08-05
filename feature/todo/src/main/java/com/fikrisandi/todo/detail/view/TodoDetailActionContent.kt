package com.fikrisandi.todo.detail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosShape
import com.fikrisandi.theme.TodosTypography

@Composable
fun TodoDetailActionContent(modifier: Modifier=Modifier, onDeleteClick: () -> Unit = {}, onUpdateClick: () -> Unit = {}){
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilledTonalButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(.4f),
            shape = TodosShape.small,
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = TodosColors.error,
                contentColor = TodosColors.background
            )
        ) {
            Text("Delete", style = TodosTypography.labelMedium)
        }
        FilledTonalButton(
            onClick = onUpdateClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(.4f),
            shape = TodosShape.small
        ) {
            Text("Update", style = TodosTypography.labelMedium)
        }
    }
}