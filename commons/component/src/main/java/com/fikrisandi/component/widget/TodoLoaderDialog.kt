package com.fikrisandi.component.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fikrisandi.theme.TodoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoLoaderDialog(modifier: Modifier = Modifier, onHideDialog: () -> Unit = {}){
    AlertDialog(onDismissRequest = { onHideDialog }, modifier = modifier.padding(horizontal = 16.dp)) {
        Surface {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp, 50.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text("Loading...")
            }
        }
    }
}

@Preview
@Composable
fun TodoLoaderDialogPreview(){
    TodoTheme {
        TodoLoaderDialog(modifier = Modifier)
    }
}