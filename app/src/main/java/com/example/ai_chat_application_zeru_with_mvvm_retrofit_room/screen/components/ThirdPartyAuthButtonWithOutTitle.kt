package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground

@Composable
fun ThirdPartyAuthButtonWithOutTitle(
    onClick: () -> Unit,
    icon: Int,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(90.dp)
            .height(80.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 0.dp
        ),
        border = BorderStroke(1.dp, color = Color.LightGray),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBackground,
            //contentColor = SecondaryFontColor
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "null",
                tint = Color.Unspecified, modifier = Modifier.size(40.dp)
            )
        }
    }
}