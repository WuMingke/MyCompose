package com.erkuai.mycompose.githublesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.erkuai.mycompose.githublesson.t1.T1_1_Content
import com.erkuai.mycompose.githublesson.t1.T1_2_Content

class GithubLessonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                T1_2_Content()
            }
        }
    }
}