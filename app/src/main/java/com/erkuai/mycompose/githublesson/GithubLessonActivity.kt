package com.erkuai.mycompose.githublesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.erkuai.mycompose.githublesson.t1.T1_1_Content
import com.erkuai.mycompose.githublesson.t1.T1_2_Content
import com.erkuai.mycompose.githublesson.t2.T2_1_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_1_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_2_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_3_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_4_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_5_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_7_Content

class GithubLessonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                T2_5_7_Content()
            }
        }
    }
}