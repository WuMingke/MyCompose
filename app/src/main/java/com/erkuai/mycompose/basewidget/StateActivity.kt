package com.erkuai.mycompose.basewidget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class StateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "标题")
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    finish()
                                }) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                            }
                        }
                    )
                }) {

                TestViewModel()
            }
        }
    }
}

@Composable
fun TestViewModel() {
    val viewModel: MyViewModel = viewModel()
    val count by viewModel.count.observeAsState(initial = 0)

//    var countTest by remember { mutableStateOf(0) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(), Modifier.padding(10.dp)
        )
        Button(onClick = {
            viewModel.onCountChanged(count + 2)
//            countTest++
        }) {
            Text(text = "Add Count")
        }
    }
}

class MyViewModel : ViewModel() {
    private val _count = MutableLiveData(0)

    val count: LiveData<Int>
        get() = _count

    fun onCountChanged(count: Int) {
        _count.postValue(count)
    }
}