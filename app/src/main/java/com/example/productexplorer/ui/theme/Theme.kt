package com.example.productexplorer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = TealLight,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFA4F2DF),
    onPrimaryContainer = Color(0xFF002019),
    secondary = Color(0xFF556353),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD9E8D2),
    onSecondaryContainer = Color(0xFF1C2B18),
    background = BackgroundLight,
    onBackground = Color(0xFF17201C),
    surface = Color.White,
    onSurface = Color(0xFF17201C),
    surfaceVariant = Color(0xFFE2ECE6),
    onSurfaceVariant = Color(0xFF3F4943),
    outline = Color(0xFF6F7972)
)

private val DarkColorScheme = darkColorScheme(
    primary = TealDark,
    onPrimary = Color(0xFF00382E),
    primaryContainer = Color(0xFF005044),
    onPrimaryContainer = Color(0xFFA4F2DF),
    secondary = Color(0xFFBACCBC),
    onSecondary = Color(0xFF253426),
    secondaryContainer = Color(0xFF3B4B3B),
    onSecondaryContainer = Color(0xFFD9E8D2),
    background = BackgroundDark,
    onBackground = Color(0xFFDEE5DF),
    surface = Color(0xFF1A211D),
    onSurface = Color(0xFFDEE5DF),
    surfaceVariant = Color(0xFF303B34),
    onSurfaceVariant = Color(0xFFBECAC1),
    outline = Color(0xFF89958C)
)

private val ProductShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun ProductExplorerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Les couleurs restent les memes sur tous les telephones.
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ProductTypography,
        shapes = ProductShapes,
        content = content
    )
}
