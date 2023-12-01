package com.erkuai.mycompose.githublesson.t2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun T1_10_1_CONTENT() {
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(
                initialValue = BottomSheetValue.Collapsed,
                density = LocalDensity.current
            )
        )
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetGesturesEnabled = true,
        sheetContent = {
            SheetContent()
        },
        drawerGesturesEnabled = true,
        drawerScrimColor = Color.Red,
//        sheetPeekHeight = 70.dp,
        floatingActionButton = {
            FloatingActionButtonComponent(state = bottomSheetScaffoldState.bottomSheetState)
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        MainContent(bottomSheetScaffoldState.bottomSheetState)
    }

}

@Composable
fun SheetContent() {
    Column(
        modifier = Modifier.heightIn(300.dp, 500.dp)
    ) {

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FloatingActionButtonComponent(state: BottomSheetState) {
    val coroutineScope = rememberCoroutineScope()
    FloatingActionButton(onClick = {
        coroutineScope.launch {
            if (state.isCollapsed) {
                state.expand()
            } else {
                state.collapse()
            }
        }
    }) {
        Icon(Icons.Filled.AccountCircle, contentDescription = "")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(bottomSheetState: BottomSheetState) {
    val currentValue: BottomSheetValue = bottomSheetState.currentValue
    val offset = bottomSheetState.requireOffset()

    val progress = bottomSheetState.progress

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff6D4C41))
            .padding(top = 30.dp)
    ) {
        Text(
            color = Color.White,
            text = "isExpanded: ${bottomSheetState.isExpanded}\n" +
                    "isCollapsed: ${bottomSheetState.isCollapsed}"
        )

        Text(
            color = Color.White,
            text = "currentValue: ${currentValue}\n" +
                    "offset: $offset"
        )

        Text(
            color = Color.White,
            text = "progress: $progress"
        )
    }
}