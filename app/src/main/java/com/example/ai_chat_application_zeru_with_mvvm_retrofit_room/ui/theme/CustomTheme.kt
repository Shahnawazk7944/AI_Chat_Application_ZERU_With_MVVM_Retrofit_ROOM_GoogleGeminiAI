package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


fun myLightColorScheme(
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

fun myDarkColorScheme(
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
    var onSecondary by mutableStateOf(onSecondary)
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
        onSecondary: Color = this.onSecondary,
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

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        background = other.background
        secondary = other.secondary
        onSecondary = other.onSecondary
        tertiary = other.tertiary
        error = other.error
        onError = other.onError
        secondaryContainer = other.secondaryContainer
        onSecondaryContainer = other.onSecondaryContainer
    }

}
private val LocalColors = compositionLocalOf<AppColors> { error("No colors provided") }
object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}

@Composable
fun AppTheme(
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

