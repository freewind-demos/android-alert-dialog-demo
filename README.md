# Android AlertDialog 对话框演示

## 简介

本 Demo 演示 Android 中 AlertDialog 的各种使用方式，包括简单消息、确认框、列表、单选、多选、输入框和自定义布局对话框。

## 基本原理

### 什么是 AlertDialog？

AlertDialog 是 Android 中最常用的对话框类型，用于向用户显示重要信息或获取用户确认。它是一个阻塞式的对话框，会暂停当前 Activity 的执行直到用户做出响应。

### AlertDialog 的特点

- 模态对话框：弹出时阻止用户与其他界面交互
- 必须包含标题和内容
- 可以包含 0-3 个按钮（Positive、Negative、Neutral）
- 支持列表、单项选择、多项选择等模式

## 启动和使用

### 环境要求
- Android Studio Arctic Fox 或更高版本
- JDK 11+

### 安装和运行
1. 使用 Android Studio 打开项目
2. 连接设备或启动模拟器
3. 运行项目

## 教程

### 1. 简单消息对话框

最简单的对话框，只显示消息和一个确定按钮。

```kotlin
AlertDialog.Builder(this)
    .setTitle("提示")
    .setMessage("这是一个简单的消息对话框")
    .setPositiveButton("确定", null)
    .show()
```

### 2. 确认对话框

带有标题、消息和三个按钮（确定、取消、忽略）的对话框。

```kotlin
AlertDialog.Builder(this)
    .setTitle("确认")
    .setMessage("确定要退出吗？")
    .setPositiveButton("确定") { _, _ ->
        // 处理确定点击
    }
    .setNegativeButton("取消") { _, _ ->
        // 处理取消点击
    }
    .setNeutralButton("忽略") { _, _ ->
        // 处理忽略点击
    }
    .show()
```

### 3. 列表对话框

显示一个可滚动列表供用户选择。

```kotlin
val items = arrayOf("苹果", "香蕉", "橙子")
AlertDialog.Builder(this)
    .setTitle("选择水果")
    .setItems(items) { _, which ->
        updateResult("选择了: ${items[which]}")
    }
    .show()
```

### 4. 单选对话框

只能选择一个选项的对话框。

```kotlin
val items = arrayOf("选项一", "选项二", "选项三")
var selectedIndex = 0
AlertDialog.Builder(this)
    .setSingleChoiceItems(items, 0) { _, which ->
        selectedIndex = which
    }
    .setPositiveButton("确定") { _, _ ->
        updateResult("选择了: ${items[selectedIndex]}")
    }
    .show()
```

### 5. 多选对话框

可以同时选择多个选项。

```kotlin
val items = arrayOf("Java", "Kotlin", "Python")
val checkedItems = booleanArrayOf(false, true, false)
AlertDialog.Builder(this)
    .setMultiChoiceItems(items, checkedItems) { _, which, isChecked ->
        checkedItems[which] = isChecked
    }
    .show()
```

### 6. 输入框对话框

带有 EditText 的对话框，用于获取用户输入。

```kotlin
val input = EditText(this)
input.hint = "请输入内容"
AlertDialog.Builder(this)
    .setTitle("输入对话框")
    .setView(input)
    .setPositiveButton("确定") { _, _ ->
        val text = input.text.toString()
    }
    .show()
```

### 7. 自定义布局对话框

使用自定义布局的对话框，可以包含复杂的表单。

```kotlin
val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)
AlertDialog.Builder(this)
    .setTitle("用户信息")
    .setView(dialogView)
    .show()
```

## 注意事项

1. **不要在对话框中执行耗时操作**：对话框应该在主线程中快速响应
2. **正确处理按钮点击**：使用 Lambda 表达式处理回调
3. **自定义布局注意尺寸**：使用 wrap_content 而非固定高度
4. **及时 dismiss()**：对话框使用完后应该关闭，避免内存泄漏
