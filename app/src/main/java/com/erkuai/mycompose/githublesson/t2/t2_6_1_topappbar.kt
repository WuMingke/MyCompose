package com.erkuai.mycompose.githublesson.t2

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Tutorial2_6_1_Screen(onBack: (() -> Unit)? = null) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffECEFF1)),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            item {
                ActionTopAppbar({
                    onBack?.invoke()
                }, elevation = 8.dp)
            }

            item {
                TextTabComponent()
            }
        })
}

@Composable
fun TextTabComponent() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val list = listOf("Home", "Map", "Settings")
    TabRow(
        selectedTabIndex = selectedIndex,
        indicator = {
            it.forEachIndexed { index, tabPosition ->
                println(
                    "index: $index, " +
                            "left: ${tabPosition.left}, " +
                            "right: ${tabPosition.right}, " +
                            "width: ${tabPosition.width}"
                )
            }
            Box(
                modifier = Modifier
//                    .myTabIndicatorOffset(it[selectedIndex])
                    .height(4.dp)
                    .wrapContentSize(Alignment.BottomStart)
            )
        }
    ) {
        list.forEachIndexed { index, title ->
            Tab(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                },
                text = {
                    Text(text = title)
                })
        }
    }
}

fun Modifier.myTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = currentTabPosition.width,
        animationSpec = tween(durationMillis = 5250, easing = FastOutSlowInEasing),
        label = "tab width"
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = "indicator offset"
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}


@Composable
fun ActionTopAppbar(onBack: (() -> Unit)? = null, elevation: Dp) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(

        title = {
            Text(text = "TopAppbar")
        },
        modifier = Modifier,
        navigationIcon = {
            IconButton(onClick = { onBack?.invoke() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
        },
        elevation = elevation,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,

        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.MoreVert, contentDescription = null)
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
                }
                DropdownMenuItem(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
                }
                DropdownMenuItem(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Default.Face, contentDescription = "")
                }
            }
        }
    )
}