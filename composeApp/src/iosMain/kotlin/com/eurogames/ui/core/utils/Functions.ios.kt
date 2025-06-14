package com.eurogames.ui.core.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

actual fun Modifier.coloredShadow(
    color: Color,
    alpha: Float,
    borderRadius: Dp,
    shadowRadius: Dp,
    offsetY: Dp,
    offsetX: Dp
): Modifier {
    return this.then(
        Modifier.coloredShadow(
            color = color.copy(alpha = alpha),
            borderRadius = borderRadius,
            shadowRadius = shadowRadius,
            offsetY = offsetY,
            offsetX = offsetX
        )
    )
}