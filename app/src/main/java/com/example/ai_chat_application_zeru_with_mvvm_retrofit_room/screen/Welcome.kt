package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MainButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryBackground
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.PrimaryFontColor
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Welcome(navController: NavHostController) {
    val welcomeImages = listOf(
        R.drawable.ai_intro_1,
        R.drawable.ai_intro_2,
        R.drawable.ai_intro_3
    )
    val welcomeDescription = listOf(
        "Welcome to Zeru, a great friend to chat with you",
        "If you are confused about what to do, just open Zeru",
        "Zeru will be ready to chat and make you happy"
    )
    val pagerState = rememberPagerState(pageCount = {
        welcomeImages.size
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(state = pagerState, ) { page ->
            Column(
                Modifier
                    //.wrapContentSize()
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = welcomeImages[page]),
                    contentDescription = "intro images",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),

                )
                //Spacer(modifier = Modifier.height(20.dp))
                Text(welcomeDescription[page],
                    fontFamily = ubuntu,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 40.sp,
                    color = PrimaryFontColor,
                    textAlign = TextAlign.Center
                )
                MainButton(onClick= {}, eventText = "Next")

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    Welcome(navController = rememberNavController())
}