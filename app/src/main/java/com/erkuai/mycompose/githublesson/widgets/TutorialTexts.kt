package com.erkuai.mycompose.githublesson.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val boldRegex = Regex("(?<!\\*)\\*\\*(?!\\*).*?(?<!\\*)\\*\\*(?!\\*)")

@Composable
fun TutorialHeader(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 6.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        text = text,
    )
}

@Composable
fun StyleableTutorialText(modifier: Modifier = Modifier, text: String, bullets: Boolean = true) {
    var result: MatchResult? = boldRegex.find(text)
    val boldIndex = mutableListOf<Pair<Int, Int>>()
    val keywords = mutableListOf<String>()
    var finalText = text
    while (result != null) {
        keywords.add(result.value)
        result = result.next()
    }

    keywords.forEach { keyword ->
        val newKeyWord = keyword.removeSurrounding("**")
        finalText = finalText.replace(keyword, newKeyWord)
        val indexOf = finalText.indexOf(newKeyWord)
        boldIndex.add(Pair(indexOf, indexOf + newKeyWord.length))
    }

    val annotatedString = buildAnnotatedString {

        append(finalText)

        if (bullets) {
            addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), start = 0, end = 3)
        }

        boldIndex.forEach {
            addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff64b5f6),
                    fontSize = 15.sp
                ),
                start = it.first,
                end = it.second
            )
        }
    }

    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 12.dp),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        text = annotatedString,
    )
}

@Composable
fun TutorialText2(modifier: Modifier = Modifier, text: String) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
            text = text, style = MaterialTheme.typography.body2,
            color = Color.Gray, modifier = modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    StyleableTutorialText(text = "Sample text for demonstrating this text")
}