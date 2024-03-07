package com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.screen.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai_chat_application_zeru_with_mvvm_retrofit_room.ui.theme.ubuntu

@Composable
fun RoundedCornerCheckbox(
    label: String,
    labelSize: TextUnit = 18.sp,
    labelColor: Color = Color.Black,
    isChecked: Boolean,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    size: Float = 24f,
    checkedColor: Color = Color.Blue,
    uncheckedColor: Color = Color.Black,
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(
        if (isChecked) checkedColor else uncheckedColor,
        label = ""
    )
    val density = LocalDensity.current
    val duration = 200

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .heightIn(48.dp) // height of 48dp to comply with minimum touch target size
            .toggleable(
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onValueChange
            )
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(8.dp))
                .border(width = 3.dp, color = checkedColor, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = label,
            fontFamily = ubuntu, //My own FontFamily
            fontSize = labelSize,
            fontWeight = FontWeight.Medium,
            color = labelColor
        )
    }
}