package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    background = DarkPrimaryBackground,
    secondary = DarkPrimaryFontColor,       //primary font as secondary
    onSecondary = DarkSecondaryFontColor,    //secondary font as onSecondary
    tertiary = DarkAIChatBackgroundColor,    //AIChatBackground as tertiary
    error = DarkPinkDark,                     //PinkDark as error
    onError = DarkGrayColor,                     //GrayColor as onError
    secondaryContainer = DarkTextFieldColorFirst, // TextField Focused color
    onSecondaryContainer = DarkTextFieldColorSecond, // TextField unFocused color
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    background = PrimaryBackground,
    secondary = PrimaryFontColor,       //primary font as secondary
    onSecondary = SecondaryFontColor,    //secondary font as onSecondary
    tertiary = AIChatBackgroundColor,    //AIChatBackground as tertiary
    error = PinkDark,                     //PinkDark as error
    onError = GrayColor,                    //GrayColor as onError
    secondaryContainer = TextFieldColorFirst, // TextField Focused color
    onSecondaryContainer = TextFieldColorSecond, // TextField unFocused color




    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun AI_Chat_Application_ZERU_With_MVVM_Retrofit_ROOMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            //window.statusBarColor = colorScheme.primary.toArgb()
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}