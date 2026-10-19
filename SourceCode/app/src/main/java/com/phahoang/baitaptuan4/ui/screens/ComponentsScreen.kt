package com.phahoang.baitaptuan4.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val CardBlue = Color(0xFFD9EEFF)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsScreen(onBack: () -> Unit, onComponentClick: (UiComponent) -> Unit) {
    var tab by remember { mutableIntStateOf(0) }
    var menuOpen by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UI Components", color = Color(0xFF1389D7), fontWeight = FontWeight.Bold) },
                navigationIcon = { TextButton(onClick = onBack) { Text("‹", fontSize = 32.sp, color = Color(0xFF1389D7)) } },
                actions = {
                    Box { TextButton(onClick = { menuOpen = true }) { Text("⋮", fontSize = 26.sp) }
                        DropdownMenu(menuOpen, { menuOpen = false }) {
                            DropdownMenuItem(
                                text = { Text("Giới thiệu") },
                                onClick = {
                                    menuOpen = false
                                    showAboutDialog = true
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Đặt lại") },
                                onClick = {
                                    menuOpen = false
                                    tab = 0
                                }
                            )
                        }
                    }
                }
            )
        },

    ) { padding ->
        if (tab == 0) {
            Column(Modifier.padding(padding).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp, vertical = 10.dp)) {
                Text("Tất cả thành phần UI", fontSize = 15.sp, color = Color(0xFF667085))
                Spacer(Modifier.height(10.dp))
                componentGroups.forEach { (group, items) ->
                    Text(group, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp, bottom = 6.dp))
                    items.forEach { component ->
                        ComponentCard(component, onClick = { onComponentClick(component) })
                        Spacer(Modifier.height(8.dp))
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
        } else {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text(if (tab == 1) "Chưa có component yêu thích" else "UI Components Showcase", color = Color(0xFF667085))
            }
        }
    }
    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = {
                showAboutDialog = false
            },
            title = {
                Text("UI Components")
            },
            text = {
                Text(
                    "Ứng dụng minh hoạ các thành phần giao diện cơ bản " +
                            "bằng Jetpack Compose."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showAboutDialog = false
                    }
                ) {
                    Text("Đóng")
                }
            }
        )
    }
}

@Composable
private fun ComponentCard(component: UiComponent, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(13.dp), color = CardBlue
    ) { Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text(component.name, fontWeight = FontWeight.Bold, color = Color(0xFF14213D))
        Text(component.description, color = Color(0xFF425466), fontSize = 13.sp)
    } }
}
