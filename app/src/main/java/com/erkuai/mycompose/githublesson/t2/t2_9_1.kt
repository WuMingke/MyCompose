package com.erkuai.mycompose.githublesson.t2

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

object Routes {
    const val HOME_ROUTE = "Home"
    const val SETTINGS_ROUTE = "Settings"
}

@Composable
fun Tutorial2_9Screen1() {

    val context = LocalContext.current
    var currentRoute by remember { mutableStateOf(Routes.HOME_ROUTE) }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()


    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {

        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "title")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("33333")
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                }

            )
        },
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .background(Color.Red)
                ) {
                    Text(text = "Box")
                }
//                Snackbar(
//                    modifier = Modifier.padding(4.dp),
//                    action = {
//                        Text(
//                            modifier = Modifier.clickable {
//                                Toast.makeText(context, "toast", Toast.LENGTH_SHORT).show()
//                            },
//                            text = "2222"
//                        )
//                    }
//                ) {
//                    Text(text = "111")
//                }
            }
        }
    ) {
        it.calculateBottomPadding()
        NavHost(navController = navController, startDestination = Routes.HOME_ROUTE) {
            composable(Routes.HOME_ROUTE) {

            }
            composable(Routes.SETTINGS_ROUTE) {

            }
        }
    }
}