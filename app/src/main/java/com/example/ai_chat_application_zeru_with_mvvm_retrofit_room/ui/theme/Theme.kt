package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme

//import android.graphics.Color
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = MyDarkColorScheme(
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

private val LightColorScheme = MyLightColorScheme(
    primary = PrimaryColor,
    background = PrimaryBackground,
    secondary = PrimaryFontColor,       //primary font as secondary
    onSecondary = SecondaryFontColor,    //secondary font as onSecondary
    tertiary = AIChatBackgroundColor,    //AIChatBackground as tertiary
    error = PinkDark,                     //PinkDark as error
    onError = GrayColor,                    //GrayColor as onError
    secondaryContainer = TextFieldColorFirst, // TextField Focused color
    onSecondaryContainer = TextFieldColorSecond, // TextField unFocused color

)
fun MyLightColorScheme(
    primary: Color = PrimaryColor,
    background: Color = PrimaryBackground,
    secondary: Color = PrimaryFontColor,
    onSecondary: Color = SecondaryFontColor,
    tertiary: Color = AIChatBackgroundColor,
    error: Color = PinkDark,
    onError: Color = GrayColor,
    secondaryContainer: Color = TextFieldColorFirst,
    onSecondaryContainer: Color = TextFieldColorSecond,

): AppColors = AppColors(
    primary,
    background,
    secondary,
    onSecondary,
    tertiary,
    error,
    onError,
    secondaryContainer,
    onSecondaryContainer,
)
fun MyDarkColorScheme(
    primary: Color = DarkPrimaryColor,
    background: Color = DarkPrimaryBackground,
    secondary: Color = DarkPrimaryFontColor,
    onSecondary: Color = DarkSecondaryFontColor,
    tertiary: Color = DarkAIChatBackgroundColor,
    error: Color = DarkPinkDark,
    onError: Color = DarkGrayColor,
    secondaryContainer: Color = DarkTextFieldColorFirst,
    onSecondaryContainer: Color = DarkTextFieldColorSecond,
): AppColors = AppColors(
    primary,
    background,
    secondary,
    onSecondary,
    tertiary,
    error,
    onError,
    secondaryContainer,
    onSecondaryContainer,
)
class AppColors(
    primary: Color,
    background: Color,
    secondary: Color,
    onSecondary: Color,
    tertiary: Color,
    error: Color,
    onError: Color,
    secondaryContainer: Color,
    onSecondaryContainer: Color,
) {
    var primary by mutableStateOf(primary)
        private set
    var background by mutableStateOf(background)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var onSecondary by mutableStateOf(secondary)
        private set
    var tertiary by mutableStateOf(tertiary)
        private set
    var error by mutableStateOf(error)
        private set
    var onError by mutableStateOf(onError)
        private set
    var secondaryContainer by mutableStateOf(secondaryContainer)
        private set
    var onSecondaryContainer by mutableStateOf(onSecondaryContainer)
        private set

    fun copy(
        primary: Color = this.primary,
        background: Color = this.background,
        secondary: Color = this.secondary,
        onSecondary: Color = this.secondary,
        tertiary: Color = this.tertiary,
        error: Color = this.error,
        onError: Color = this.onError,
        secondaryContainer: Color = this.secondaryContainer,
        onSecondaryContainer: Color = this.onSecondaryContainer,

        ): AppColors = AppColors(
        primary,
        background,
        secondary,
        onSecondary,
        tertiary,
        error,
        onError,
        secondaryContainer,
        onSecondaryContainer,
    )

    object AppTheme {
        val colors: AppColors
            @Composable
            @ReadOnlyComposable
            get() = LocalColor.current

    }
}

@Composable
fun AI_Chat_Application_ZERU_With_MVVM_Retrofit_ROOMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            //if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

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

@Composable
fun MyAppTheme(
    colors: AppColors = AppTheme.colors,
    content: @Composable () -> Unit
) {
    // creating a new object for colors to not mutate the initial colors set when updating the values
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalColors provides rememberedColors,
    ) {
        content()
    }
}

