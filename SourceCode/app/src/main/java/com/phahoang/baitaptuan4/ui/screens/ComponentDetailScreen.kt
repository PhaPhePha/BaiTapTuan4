package com.phahoang.baitaptuan4.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*
import coil3.compose.AsyncImage
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.phahoang.baitaptuan4.R


val AppFont = FontFamily(
    Font(
        resId = R.font.dancing_script,
        weight = FontWeight.Normal
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetailScreen(component: UiComponent, onBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(component.name, color = Color(0xFF1389D7), fontWeight = FontWeight.Bold) },
            navigationIcon = { TextButton(onClick = onBack) { Text("‹", fontSize = 32.sp, color = Color(0xFF1389D7)) } }
        )
    }) { padding ->
        Column(Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(component.description, color = Color(0xFF667085), modifier = Modifier.padding(bottom = 28.dp))
            when (component.name) {
                "Text" -> TextDemo()
                "Image" -> ImageDemo()
                "TextField" -> TextFieldDemo()
                "PasswordField" -> PasswordDemo()
                "Toggle" -> ToggleDemo()
                "Slider" -> SliderDemo()
                "DatePicker" -> DatePickerDemo()
                "Button" -> ButtonDemo()
                "Checkbox" -> CheckboxDemo()
                "RadioButton" -> RadioDemo()
                "Picker" -> PickerDemo()
                "List" -> ListDemo()
                "Card" -> CardDemo()
                "Divider" -> DividerDemo()
                "Column" -> ColumnDemo()
                "Row" -> RowDemo()
                else -> NavigationDemo(component.name)
            }
        }
    }
}

@Composable
private fun TextDemo() {
    Text(
        text = buildAnnotatedString {
            append("The ")
            withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                append("quick")
            }
            append(" ")
            withStyle(SpanStyle(color = Color(0xFFC67C13), fontSize = 30.sp)) {
                append("Brown")
            }
            append("\nfox j u m p s ")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                append("over")
            }
            append("\n")
            withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("the ")
            }
            withStyle(SpanStyle(fontFamily = AppFont, fontWeight = FontWeight.Normal,)) {
                append("lazy ")
            }
            withStyle(SpanStyle(fontWeight = FontWeight.Normal)) {
                append("dog.")
            }
        },
        fontSize = 28.sp,
        lineHeight = 42.sp,
        modifier = Modifier.padding(top = 32.dp)
    )
    HorizontalDivider(Modifier.padding(top = 26.dp))
}

@Composable
private fun ImageDemo() {
    val imageUrl =
        "https://t.cmx-cdn.com/giaothongvantaitphcm.edu.vn/wp-content/uploads/2024/06/ky-niem-36-nam-thanh-lap-truong-dai-hoc-giao-thong-van-tai-tphcm-560px.jpg"

    AsyncImage(
        model = imageUrl,
        contentDescription = "Ảnh từ URL",
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(18.dp)),
        contentScale = ContentScale.Crop
    )

    Text(
        text = imageUrl,
        color = Color(0xFF667085),
        modifier = Modifier.padding(top = 10.dp)
    )

    Spacer(modifier = Modifier.height(24.dp))

    Image(
        painter = painterResource(id = R.drawable.images),
        contentDescription = "Ảnh lưu trong app",
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(18.dp)),
        contentScale = ContentScale.Crop
    )

    Text(
        text = "Ảnh được lưu trong app",
        color = Color(0xFF667085),
        modifier = Modifier.padding(top = 10.dp)
    )
}

@Composable private fun TextFieldDemo() {
    var value by remember { mutableStateOf("") }
    OutlinedTextField(value, { value = it },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Thông tin nhập") },
        supportingText = { Text("Tự động cập nhật dữ liệu theo textfield") }
    )
    Text("Giá trị hiện tại: ${value.ifBlank { "(trống)" }}",
        color = Color(0xFF1389D7),
        modifier = Modifier.padding(top = 18.dp))
}

@Composable private fun PasswordDemo() {
    var value by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }
    OutlinedTextField(value, { value = it },
        modifier = Modifier.fillMaxWidth(),
        label = { Text("Mật khẩu") },
        visualTransformation =
            if (visible)
                androidx.compose.ui.text.input.VisualTransformation.None
            else
                androidx.compose.ui.text.input.PasswordVisualTransformation(),
        trailingIcon = {
            TextButton({ visible = !visible }) {
                Text(if (visible) "Ẩn" else "Hiện")
            }
        })
}

@Composable private fun ToggleDemo() {
    var checked by remember { mutableStateOf(true) }
    Row(Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(if (checked) "Đang bật" else "Đang tắt", fontSize = 18.sp)
        Switch(checked, { checked = it })
    }
}

@Composable private fun SliderDemo() {
    var value by remember { mutableFloatStateOf(.5f) }
    Text("Giá trị: ${(value * 100).toInt()}%", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Slider(value, { value = it }, modifier = Modifier.fillMaxWidth())
}

@Composable private fun DatePickerDemo() {
    var show by remember { mutableStateOf(false) }; var selected by remember { mutableStateOf("Chưa chọn ngày") }
    OutlinedButton({ show = true }) { Text("📅  Chọn ngày") }
    Text(selected, color = Color(0xFF667085), modifier = Modifier.padding(top = 14.dp))
    if (show) {
        val state = rememberDatePickerState()
        DatePickerDialog(onDismissRequest = { show = false },
            confirmButton = {
                TextButton({ show = false; state.selectedDateMillis?.let {
                    selected = SimpleDateFormat("dd/MM/yyyy",
                        Locale.getDefault()).format(Date(it)) } }) { Text("OK") }
            },
            dismissButton = {
                TextButton({ show = false }) {
                    Text("Huỷ") }
            }
        )
        { DatePicker(state) }
    }
}

@Composable private fun ButtonDemo() {
    var clicked by remember {
        mutableStateOf(false) }
    Button({ clicked = true }) { Text("Nhấn thử") }
    if (clicked)
        Text("Đã thực hiện hành động ✓",
            color = Color(0xFF168B67),
            modifier = Modifier.padding(top = 14.dp)) }

@Composable private fun CheckboxDemo() {
    var checked by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked, { checked = it }
        );
        Text("Tôi đồng ý với điều khoản") } }

@Composable private fun RadioDemo() {
    var selected by remember { mutableStateOf("Kotlin") }
    listOf("Kotlin", "Java", "Compose Multiplatform").forEach {
        option -> Row(
        verticalAlignment = Alignment.CenterVertically
        )
    { RadioButton(selected == option,
        { selected = option });
        Text(option)
        }
    };
    Text("Đã chọn: $selected", color = Color(0xFF1389D7)) }

@Composable private fun PickerDemo() {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Chọn một mục") }
    Box {
        OutlinedButton({ expanded = true }) {
            Text(selected); Text("  ▾")
        };
        DropdownMenu(expanded, { expanded = false }) {
            listOf("Mục thứ nhất", "Mục thứ hai", "Mục thứ ba").forEach {
                item -> DropdownMenuItem(
                text = { Text(item) },
                onClick = { selected = item; expanded = false })
            }
        }
    }
}

@Composable private fun ListDemo() {
    Column(Modifier.fillMaxWidth()) {
        listOf("Phần tử thứ nhất", "Phần tử thứ hai", "Phần tử thứ ba").forEachIndexed {
            i, item -> ListItem(headlineContent = { Text(item) },
            supportingContent = { Text("Mô tả cho phần tử ${i + 1}") })
            if (i < 2) HorizontalDivider() } } }

@Composable private fun CardDemo() {
    Card(Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD9EEFF))) {
        Column(Modifier.padding(20.dp)) {
            Text("Card component",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold);
            Text("Container hiển thị nội dung có style",
                modifier = Modifier.padding(top = 6.dp))
        }
    }
}

@Composable private fun DividerDemo() {
    Text("Khu vực A", fontSize = 18.sp)
    HorizontalDivider(Modifier.padding(vertical = 22.dp))
    Text("Khu vực B", fontSize = 18.sp) }

@Composable
private fun ColumnDemo() {
    Text("Column layout", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Text("Các phần tử được xếp theo chiều dọc",
        color = Color(0xFF667085),
        modifier = Modifier.padding(top = 6.dp, bottom = 16.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        LayoutBlock("Item 1")
        LayoutBlock("Item 2")
        LayoutBlock("Item 3")
    }
}

@Composable
private fun LazyColumnDemo() {
    Text("LazyColumn layout", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Text("Các phần tử được xuất hiện và sắp xếp theo khung column",
        color = Color(0xFF667085),
        modifier = Modifier.padding(top = 6.dp, bottom = 16.dp))
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item{
            LayoutBlock("Item 1")
        }
        item{
            LayoutBlock("Item 2")
        }
        item{
            LayoutBlock("Item 3")
        }
    }
}


@Composable
private fun RowDemo() {
    Text("Row layout", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Text("Các phần tử được xếp theo chiều ngang",
        color = Color(0xFF667085),
        modifier = Modifier.padding(top = 6.dp, bottom = 16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        LayoutBlock("1", Modifier.weight(1f))
        LayoutBlock("2", Modifier.weight(1f))
        LayoutBlock("3", Modifier.weight(1f))
    }
}

@Composable
private fun LayoutBlock(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(72.dp)
            .background(Color(0xFFB8D9F4), RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(label, fontWeight = FontWeight.Bold, color = Color(0xFF15436B))
    }
}

@Composable private fun NavigationDemo(name: String) {
    Text("$name đang hoạt động",
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1389D7))
    Text("Màn hình này minh hoạ cơ chế điều hướng và chuyển đổi nội dung.",
        modifier = Modifier.padding(top = 12.dp),
        color = Color(0xFF667085))
}
