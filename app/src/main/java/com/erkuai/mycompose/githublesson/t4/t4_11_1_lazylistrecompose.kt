package com.erkuai.mycompose.githublesson.t4

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.erkuai.mycompose.githublesson.t3.getRandomColor
import java.util.UUID

@Composable
fun T4_11_1_CONTENT() {
    Column(
        modifier = Modifier
            .background(Color(0xffECEFF1))
            .fillMaxSize()
            .padding(10.dp)
    ) {
        MainScreen(viewModel = MyViewModel())
    }
}

@Composable
fun MainScreen(viewModel: MyViewModel) {
    var counter by remember { mutableStateOf(0) }
    val onClick = remember {
        { index: Int ->
            viewModel.toggleSelection(index)
        }
    }
    Column(modifier = Modifier.padding(8.dp)) {
        val people = viewModel.people
        Text(text = "Counter $counter")
        Button(onClick = { counter++ }) {
            Text(text = "add counter")
        }
        Button(onClick = {
            val index = people.size
            viewModel.add(Person(id = index, name = "new Person $index"))
        }) {
            Text(text = "add")
        }
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                people.lastOrNull()?.apply {
                    viewModel.delete()
                }
            }
        ) {
            Text("Delete")
        }
        Spacer(modifier = Modifier.height(10.dp))
        ListScreen(people = people, onItemClick = onClick)
    }
}

@Composable
fun ListScreen(people: SnapshotStateList<Person>, onItemClick: (Int) -> Unit) {
    Column {
        Text(text = "Header", modifier = Modifier.border(2.dp, getRandomColor()), fontSize = 30.sp)
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .border(3.dp, getRandomColor(), RoundedCornerShape(8.dp)),
            content = {
                items(
                    items = people,
                    key = { it.hashCode() }
                ) {
                    ListItem(item = it, onItemClick = onItemClick)
                }
            })
    }
}

@Composable
fun ListItem(item: Person, onItemClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .border(
                2.dp, getRandomColor(), RoundedCornerShape(8.dp)
            )
            .background(Color.White)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick.invoke(item.id) }
            .padding(8.dp)) {
            Text("Index: ${item.id}, ${item.name}", fontSize = 20.sp)
            if (item.isSelected) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .background(Color.Red, CircleShape),
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = Color.Green,
                )
            }
        }
    }
}

data class Person(val id: Int, val name: String, val isSelected: Boolean = false)

class MyViewModel : ViewModel() {
    private val initialList = List(30) { index ->
        Person(id = index, name = "Person:$index")
    }
    val people = mutableStateListOf<Person>().apply { addAll(initialList) }

    fun toggleSelection(index: Int) {
        val item = people[index]
        val isSelected = item.isSelected
        people[index] = item.copy(isSelected = !isSelected)
    }

    fun delete() {
        people.removeLastOrNull()
    }

    fun add(person: Person) {
        people.add(person)
    }

    var personList by mutableStateOf(initialList)
    fun updateItemSelection(id: Int) {
        val newList = personList.map {
            if (it.id == id) {
                it.copy(isSelected = !it.isSelected)
            } else {
                it
            }
        }
        personList = newList
    }
}