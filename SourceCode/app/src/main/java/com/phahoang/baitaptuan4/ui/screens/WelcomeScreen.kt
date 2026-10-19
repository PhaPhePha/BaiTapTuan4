package com.phahoang.baitaptuan4.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.phahoang.baitaptuan4.R

@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Surface(Modifier.fillMaxSize(), color = Color(0xFFF8FAFF)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 28.dp, vertical = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("BÀI TẬP TUẦN 04", color = Color(0xFF1686D8), fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.jetpack),
                    contentDescription = "Jetpack Compose logo",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(38.dp)),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(Modifier.height(28.dp))
            Text("UI Components", fontSize = 27.sp, fontWeight = FontWeight.Bold, color = Color(0xFF17213B))
            Text("Khám phá các thành phần giao diện cơ bản", color = Color(0xFF667085), modifier = Modifier.padding(top = 8.dp))
            Spacer(Modifier.weight(1f))
            Button(
                onClick = onStart,
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF168CE5))
            ) { Text("Let's Go",fontSize = 25.sp, fontWeight = FontWeight.Bold) }
        }
    }
}
