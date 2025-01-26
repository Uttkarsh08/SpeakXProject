package com.example.speakxproject.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.speakxproject.R

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1fd196),
    onPrimary = Color.White,
    secondary = Color(0xFF5A5A5A),
    onSecondary = Color.White,
    background = Color(0xFFe0e0e0),
    onBackground = Color.White,
    surface = Color(0xFF2B3137),
    onSurface = Color.White,
    error = Color.Red,
    onError = Color.White
)


val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1fd196),
    onPrimary = Color.Black,
    secondary = Color(0xFF5A5A5A),
    onSecondary = Color.White,
    background = Color(0xFFe0e0e0),
    onBackground = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color.Red
)


@Composable
fun SpeakXProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}