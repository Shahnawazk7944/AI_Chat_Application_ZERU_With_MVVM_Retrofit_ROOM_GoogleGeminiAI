package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AppTheme
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.poppins

@Composable
fun ThirdPartyAuthButton(
    onClick: () -> Unit,
    eventText: String,
    icon: Int,
    iconColor:Color = Color.Unspecified,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(10.dp),
        //.clip(RoundedCornerShape(50.dp))
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 0.dp
        ),
        border = BorderStroke(1.dp, color = Color.LightGray),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.background,
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
                tint = iconColor, modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                eventText,
                fontFamily = poppins,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.secondary
            )
        }
    }
}