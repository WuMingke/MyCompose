package com.erkuai.mycompose.githublesson.t2

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun T2_12_CONTENT() {

    var showAlertDialog by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.onGloballyPositioned {
            val width = it.size.width
            val height = it.size.height
            Log.i("layoutlayout", "LazyColumn ==> width:$width,height:$height")
        }
    ) {
        item {
            Button(
                onClick = {
                    showAlertDialog = !showAlertDialog
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .onGloballyPositioned {
                        val width = it.size.width
                        val height = it.size.height
                        Log.i("layoutlayout", "Button ==> width:$width,height:$height")
                    }
            ) {
                Text(text = "AlertDialog")
                if (showAlertDialog) {
                    AlertDialogExample {
                        showAlertDialog = !showAlertDialog
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogExample(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "confirm")
            }
        },
        title = {
            Text(text = "title")
        },
        text = {
            Column {
                Image(imageVector = Icons.Default.Face, contentDescription = null)
                AsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F55c38ab5-7cff-46ef-ab2a-5149b36d30c7%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1701836210&t=f87ded5ec42382e01b341f509557682f",
                    contentDescription = null
                )
            }
        },
        modifier = Modifier.onGloballyPositioned {
            val width = it.size.width
            val height = it.size.height
            Log.i("layoutlayout", "AlertDialog ==> width:$width,height:$height")
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAlertDialogExample() {
    AlertDialogExample {}
}