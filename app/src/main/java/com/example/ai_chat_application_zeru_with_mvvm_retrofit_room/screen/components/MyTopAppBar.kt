package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    onClick: () -> Unit,
    title: @Composable () -> Unit
) {
    TopAppBar(
        title = title,
        navigationIcon = {
            IconButton(onClick = { onClick }) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "back Icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(18.dp),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
