package com.erkuai.mycompose.githublesson.t2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.erkuai.mycompose.githublesson.widgets.TutorialHeader
import com.erkuai.mycompose.githublesson.widgets.TutorialText2

@Composable
fun T2_5_7_Content() {
//    LazyColumn(
//        contentPadding = PaddingValues(8.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//        content = {
//            item {
//                TutorialHeader(text = "ListItem")
//                TutorialText2(text = "one-line")
//                OneLineListItemExample()
//            }
//        })
    OneLineListItemExample()
}

@Composable
fun OneLineListItemExample() {
//    Column {
//        ListItem {
//            Text(text = "One line list item with no icon")
//        }
//        Divider()
//        ListItem(
//            text = { Text("One line list item with 24x24 icon") },
//            icon = {
//                Icon(
//                    Icons.Filled.Favorite,
//                    contentDescription = null
//                )
//            }
//        )
//    }


    val nameList = remember {
        mutableStateListOf("zhangsan")
    }
    val personList by remember(nameList) {
        derivedStateOf {
            nameList.map { "my name is $it" }

        }
    }
    Button(onClick = { nameList.add("lisi") }) {
        Text(text = "add new name")
    }
    Column {
        for (p in personList) {
            Text(text = p)
        }
    }

}