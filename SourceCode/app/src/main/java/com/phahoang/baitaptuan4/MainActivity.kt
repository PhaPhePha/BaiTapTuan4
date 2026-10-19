package com.phahoang.baitaptuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.phahoang.baitaptuan4.ui.theme.BaitapTuan4Theme
import com.phahoang.baitaptuan4.ui.screens.ComponentsScreen
import com.phahoang.baitaptuan4.ui.screens.ComponentDetailScreen
import com.phahoang.baitaptuan4.ui.screens.WelcomeScreen
import com.phahoang.baitaptuan4.ui.screens.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaitapTuan4Theme {
                var screen by remember { mutableStateOf<Screen>(Screen.Welcome) }
                when (val current = screen) {
                    Screen.Welcome -> WelcomeScreen { screen = Screen.Components }
                    Screen.Components -> ComponentsScreen(
                        onBack = { screen = Screen.Welcome },
                        onComponentClick = { screen = Screen.Detail(it) }
                    )
                    is Screen.Detail -> ComponentDetailScreen(
                        component = current.component,
                        onBack = { screen = Screen.Components }
                    )
                }
            }
        }
    }
}
