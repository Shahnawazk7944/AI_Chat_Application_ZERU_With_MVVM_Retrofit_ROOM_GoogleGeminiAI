package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.MainActivity
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.R
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.navigation.Screen
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components.MainButton
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.AppTheme
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Welcome(navController: NavHostController, context: MainActivity) {
    val scope = rememberCoroutineScope()
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
            .background(AppTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(state = pagerState) { page ->
            Column(
                Modifier
                    .wrapContentSize()
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
                Text(
                    welcomeDescription[page],
                    fontFamily = ubuntu,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 40.sp,
                    color = AppTheme.colors.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 40.dp)
                )


            }

        }

        PageIndicator(
            pageCount = welcomeImages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            MainButton(
                onClick = {
                    if (pagerState.currentPage != 2) {
                        scope.launch {
                            val nextPage = pagerState.currentPage + 1
                            pagerState.scrollToPage(nextPage)
                        }
                    } else {
                        setOnboardingCompleted( context= context)
                        navController.popBackStack()
                        navController.navigate(Screen.GoogleSignIn.route)
                    }
                },
                eventText = "Next",
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
            )
        }
    }

}

@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorDot(isSelected = it == currentPage)
        }
    }


}

@Composable
fun IndicatorDot(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 45.dp else 15.dp, label = "")
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(15.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) AppTheme.colors.primary else AppTheme.colors.onError)
    )
}
fun setOnboardingCompleted(context : MainActivity) {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("onboarding_completed", false).apply()
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    Welcome(navController = rememberNavController(), context = MainActivity())
}