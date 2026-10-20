package com.phahoang.baitaptuan4.ui.screens

import androidx.compose.runtime.Immutable

@Immutable
data class UiComponent(val name: String, val description: String, val group: String)
sealed interface Screen {
    data object Welcome : Screen
    data object Components : Screen
    data class Detail(val component: UiComponent) : Screen

    data object LazyList : Screen

    data object ChooseListType : Screen

    data object MillionLazyList : Screen

    data object MillionColumnList : Screen

    data class QuoteDetail(val quote: Quote) : Screen
}

val componentGroups = listOf(
    "Display" to listOf(
        UiComponent("Text", "Hiển thị nội dung văn bản", "Display"),
        UiComponent("Image", "Hiển thị hình ảnh", "Display")
    ),
    "Input" to listOf(
        UiComponent("TextField", "Nhập dữ liệu dạng văn bản", "Input"),
        UiComponent("PasswordField", "Nhập mật khẩu bảo mật", "Input"),
        UiComponent("Toggle", "Bật hoặc tắt một tuỳ chọn", "Input"),
        UiComponent("Slider", "Chọn giá trị trong một khoảng", "Input"),
        UiComponent("DatePicker", "Chọn ngày và thời gian", "Input")
    ),
    "Selection" to listOf(
        UiComponent("Button", "Thực hiện hành động khi chạm", "Selection"),
        UiComponent("Checkbox", "Chọn một hoặc nhiều lựa chọn", "Selection"),
        UiComponent("RadioButton", "Chọn một lựa chọn duy nhất", "Selection"),
        UiComponent("Picker", "Chọn một giá trị từ danh sách", "Selection")
    ),
    "Navigation" to listOf(
        UiComponent("NavigationView", "Container điều hướng có tiêu đề và nút quay lại", "Navigation"),
        UiComponent("TabBar", "Chuyển đổi giữa các khu vực chính", "Navigation"),
        UiComponent("Menu", "Hiển thị menu với nhiều hành động", "Navigation"),
        UiComponent("LazyColumn", "Danh sách cuộn tối ưu trong Compose", "Navigation")
    ),
    "Content" to listOf(
        UiComponent("List", "Hiển thị danh sách các phần tử", "Content"),
        UiComponent("Card", "Container có nội dung và kiểu dáng", "Content"),
        UiComponent("Divider", "Phân tách nội dung thành các phần", "Content")
    ),
    "Layout" to listOf(
        UiComponent("Column", "Sắp xếp phần tử theo chiều dọc", "Layout"),
        UiComponent("Row", "Sắp xếp phần tử theo chiều ngang", "Layout")
    )
).toMap()

data class Quote(
    val id: Int,
    val text: String
)
val quotes = listOf(
    Quote(1, "The only way to do great work is to love what you do."),
    Quote(2, "Stay hungry, stay foolish."),
    Quote(3, "Simplicity is the soul of efficiency."),
    Quote(4, "Great things are done by a series of small things."),
    Quote(5, "Code is like humor. When you have to explain it, it’s bad.")
)
