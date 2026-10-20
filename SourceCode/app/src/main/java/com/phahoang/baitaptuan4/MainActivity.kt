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
import com.phahoang.baitaptuan4.ui.screens.LazyListScreen
import com.phahoang.baitaptuan4.ui.screens.QuoteDetailScreen
import com.phahoang.baitaptuan4.ui.screens.MillionLazyListScreen
import com.phahoang.baitaptuan4.ui.screens.MillionColumnScreen
import com.phahoang.baitaptuan4.ui.screens.ChooseListTypeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaitapTuan4Theme {
                var screen by remember { mutableStateOf<Screen>(Screen.Welcome) }
                when (val current = screen) {
                    Screen.Welcome -> WelcomeScreen(
                        onStart = { screen = Screen.Components }
                    )
                    Screen.Components -> ComponentsScreen(
                        onBack = { screen = Screen.Welcome },
                        onComponentClick = { component ->
                            if (component.name == "LazyColumn") {
                                screen = Screen.LazyList
                            } else {
                                screen = Screen.Detail(component)
                            }
                        }
                    )
                    is Screen.Detail -> ComponentDetailScreen(
                        component = current.component,
                        onBack = { screen = Screen.Components }
                    )
                    Screen.LazyList -> LazyListScreen(
                        onBack = { screen = Screen.Welcome },
                        onQuoteClick = { quote -> screen = Screen.QuoteDetail(quote) },
                        onOpenBenchmark = { screen = Screen.ChooseListType }
                    )
                    Screen.ChooseListType -> ChooseListTypeScreen(
                        onBack = { screen = Screen.LazyList },
                        onColumn = { screen = Screen.MillionColumnList },
                        onLazyColumn = { screen = Screen.MillionLazyList }
                    )
                    Screen.MillionLazyList -> MillionLazyListScreen(
                        onBack = { screen = Screen.LazyList }
                    )
                    Screen.MillionColumnList -> MillionColumnScreen(
                        onBack = { screen = Screen.LazyList }
                    )
                    is Screen.QuoteDetail -> QuoteDetailScreen(
                        quote = current.quote,
                        onBackToList = { screen = Screen.LazyList },
                        onBackToRoot = { screen = Screen.Welcome }
                    )
                }
            }
        }
    }
}
