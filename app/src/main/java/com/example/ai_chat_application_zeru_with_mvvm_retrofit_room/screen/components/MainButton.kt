package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@Composable
fun MainButton(
    onClick: () -> Unit,
    isEnable: Boolean = true,
    eventText: String,
    isLoading: Boolean = false,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        enabled = isEnable,
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(10.dp),
        //.clip(RoundedCornerShape(50.dp))
        shape = RoundedCornerShape(50.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 3.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {
        if (!isLoading) {
            Text(
                eventText,
                fontFamily = ubuntu,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        } else {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    eventText,
                    fontFamily = ubuntu,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(10.dp))
                CircularProgressIndicator(Modifier.size(40.dp), color = MaterialTheme.colorScheme.onSecondary)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButPRe() {
    MainButton(onClick = { /*TODO*/ }, eventText = "Next", modifier = Modifier)
}