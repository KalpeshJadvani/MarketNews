package com.example.marketnews.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.platform.testTag
import com.example.marketnews.utils.Constants.tagTitleAuthor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    title: String, onNavigateUp: () -> Unit, content: @Composable () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = title, modifier = Modifier.testTag(tagTitleAuthor)
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
        )
    }, content = { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            content()
        }
    }

    )
}