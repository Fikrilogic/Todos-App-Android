package com.fikrisandi.component.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.theme.R
import com.fikrisandi.theme.TodoTheme
import com.fikrisandi.theme.TodosColors
import com.fikrisandi.theme.TodosTypography

@Composable
fun TodoEmptyData(modifier: Modifier = Modifier){
    Box(modifier = modifier.background(TodosColors.background), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.empty_data), contentDescription = "Data Is Empty", modifier = Modifier.size(200.dp))
            Text("Data Not Found", style = TodosTypography.displayMedium, textAlign = TextAlign.Center)
        }
    }    
}

@Composable
@Preview
fun TodoEmptyDataPreview(modifier: Modifier = Modifier){
    TodoTheme {
        TodoEmptyData(modifier = Modifier.fillMaxSize())
    }
}