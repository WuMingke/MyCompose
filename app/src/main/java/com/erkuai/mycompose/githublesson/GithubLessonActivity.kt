package com.erkuai.mycompose.githublesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.erkuai.mycompose.githublesson.t1.T1_1_Content
import com.erkuai.mycompose.githublesson.t1.T1_2_Content
import com.erkuai.mycompose.githublesson.t2.T1_10_1_CONTENT
import com.erkuai.mycompose.githublesson.t2.T2_12_CONTENT
import com.erkuai.mycompose.githublesson.t2.T2_1_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_1_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_2_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_3_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_4_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_5_Content
import com.erkuai.mycompose.githublesson.t2.T2_5_7_Content
import com.erkuai.mycompose.githublesson.t2.Tutorial2_6_1_Screen
import com.erkuai.mycompose.githublesson.t2.Tutorial2_9Screen1
import com.erkuai.mycompose.githublesson.t3.T3_1_CONTENT
import com.erkuai.mycompose.githublesson.t4.T4_11_1_CONTENT

class GithubLessonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {
                T4_11_1_CONTENT()
            }
        }
    }
}