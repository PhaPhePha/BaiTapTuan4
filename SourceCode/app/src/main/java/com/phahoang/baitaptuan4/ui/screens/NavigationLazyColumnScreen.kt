package com.phahoang.baitaptuan4.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

private const val ONE_MILLION = 1_000_000

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyListScreen(
    onBack: () -> Unit,
    onQuoteClick: (Quote) -> Unit,
    onOpenBenchmark: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LazyColumn", color = Color(0xFF168CE5), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("‹", fontSize = 32.sp, color = Color(0xFF168CE5))
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(quotes, key = { it.id }) { quote ->
                QuoteCard(quote = quote, onClick = { onQuoteClick(quote) })
            }
            item {
                MillionTestCard(
                    onClick = onOpenBenchmark
                )
            }
        }
    }
}

@Composable
private fun QuoteCard(quote: Quote, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD9EEFF))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "%02d | %s".format(quote.id, quote.text),
                modifier = Modifier.weight(1f),
                fontSize = 14.sp
            )
            Text("›", fontSize = 30.sp, color = Color(0xFF162B3D))
        }
    }
}

@Composable
private fun MillionTestCard(
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF4FF))
    ) {
        Column(Modifier.padding(14.dp)) {
            Text("Test 1,000,000 items", fontWeight = FontWeight.Bold)
            Text(
                "Chạm để chọn Column hoặc LazyColumn",
                fontSize = 12.sp,
                color = Color(0xFF425466),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseListTypeScreen(
    onBack: () -> Unit,
    onColumn: () -> Unit,
    onLazyColumn: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose List Type", color = Color(0xFF168CE5), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("‹", fontSize = 32.sp, color = Color(0xFF168CE5))
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onColumn,
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text("Column (1,000,000 items)")
            }
            Button(
                onClick = onLazyColumn,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA000)
                )
            ) {
                Text("LazyColumn (1,000,000 items)")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MillionLazyListScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LazyColumn - 1,000,000", color = Color(0xFF168CE5), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("‹", fontSize = 32.sp, color = Color(0xFF168CE5)) }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = ONE_MILLION,
                key = { index -> index }
            ) { index ->
                LargeItemCard(index = index)
            }
        }
    }
}

@Composable
private fun LargeItemCard(index: Int) {
    val randomNumber = Random(index).nextInt(100_000, 999_999)
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE5F3FF))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text("${index + 1}", fontWeight = FontWeight.Bold)
                Text("Random item: $randomNumber", fontSize = 12.sp, color = Color.Gray)
            }
            Text("›", fontSize = 26.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MillionColumnScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Column - 1,000,000", color = Color(0xFF168CE5), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("‹", fontSize = 32.sp, color = Color(0xFF168CE5)) }
                }
            )
        }
    ) { padding ->
        // Cố ý compose toàn bộ 1.000.000 item ngay khi mở màn hình.
        // Trên thiết bị yếu, thao tác này có thể làm app đứng hoặc crash.
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp)
        ) {
            repeat(ONE_MILLION) { index ->
                Text("${index + 1}. Random item ${Random(index).nextInt()}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteDetailScreen(
    quote: Quote,
    onBackToList: () -> Unit,
    onBackToRoot: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail", color = Color(0xFF168CE5), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    TextButton(onClick = onBackToList) {
                        Text("‹", fontSize = 32.sp, color = Color(0xFF168CE5))
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "\"${quote.text}\"",
                modifier = Modifier.padding(top = 70.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF182B49)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onBackToRoot, modifier = Modifier.fillMaxWidth()) {
                Text("BACK TO ROOT")
            }
        }
    }
}
