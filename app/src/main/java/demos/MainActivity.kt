package demos.android.alert.dialog.demo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/**
 * AlertDialog 对话框演示
 * 展示各种 AlertDialog 的使用方式
 */
class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)

        // 1. 简单消息对话框
        findViewById<Button>(R.id.btnSimple).setOnClickListener {
            showSimpleDialog()
        }

        // 2. 确认对话框
        findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            showConfirmDialog()
        }

        // 3. 选择列表对话框
        findViewById<Button>(R.id.btnList).setOnClickListener {
            showListDialog()
        }

        // 4. 单选对话框
        findViewById<Button>(R.id.btnSingleChoice).setOnClickListener {
            showSingleChoiceDialog()
        }

        // 5. 多选对话框
        findViewById<Button>(R.id.btnMultiChoice).setOnClickListener {
            showMultiChoiceDialog()
        }

        // 6. 输入框对话框
        findViewById<Button>(R.id.btnInput).setOnClickListener {
            showInputDialog()
        }

        // 7. 自定义布局对话框
        findViewById<Button>(R.id.btnCustom).setOnClickListener {
            showCustomDialog()
        }
    }

    // 简单消息对话框
    private fun showSimpleDialog() {
        AlertDialog.Builder(this)
            .setTitle("提示")
            .setMessage("这是一个简单的消息对话框")
            .setPositiveButton("确定", null)
            .show()
    }

    // 确认对话框
    private fun showConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle("确认")
            .setMessage("确定要退出吗？")
            .setPositiveButton("确定") { _, _ ->
                updateResult("点击了确定")
            }
            .setNegativeButton("取消") { _, _ ->
                updateResult("点击了取消")
            }
            .setNeutralButton("忽略") { _, _ ->
                updateResult("点击了忽略")
            }
            .show()
    }

    // 列表对话框
    private fun showListDialog() {
        val items = arrayOf("苹果", "香蕉", "橙子", "葡萄", "西瓜")
        AlertDialog.Builder(this)
            .setTitle("选择水果")
            .setItems(items) { _, which ->
                updateResult("选择了: ${items[which]}")
            }
            .show()
    }

    // 单选对话框
    private fun showSingleChoiceDialog() {
        val items = arrayOf("选项一", "选项二", "选项三")
        var selectedIndex = 0
        AlertDialog.Builder(this)
            .setTitle("单选对话框")
            .setSingleChoiceItems(items, 0) { _, which ->
                selectedIndex = which
            }
            .setPositiveButton("确定") { _, _ ->
                updateResult("选择了: ${items[selectedIndex]}")
            }
            .setNegativeButton("取消", null)
            .show()
    }

    // 多选对话框
    private fun showMultiChoiceDialog() {
        val items = arrayOf("Java", "Kotlin", "Python", "Swift")
        val checkedItems = booleanArrayOf(false, true, false, false)
        AlertDialog.Builder(this)
            .setTitle("多选对话框")
            .setMultiChoiceItems(items, checkedItems) { _, which, isChecked ->
                checkedItems[which] = isChecked
            }
            .setPositiveButton("确定") { _, _ ->
                val selected = items.filterIndexed { index, _ -> checkedItems[index] }
                updateResult("选择了: ${selected.joinToString(", ")}")
            }
            .setNegativeButton("取消", null)
            .show()
    }

    // 输入框对话框
    private fun showInputDialog() {
        val input = android.widget.EditText(this)
        input.hint = "请输入内容"

        AlertDialog.Builder(this)
            .setTitle("输入对话框")
            .setView(input)
            .setPositiveButton("确定") { _, _ ->
                val text = input.text.toString()
                if (text.isNotEmpty()) {
                    updateResult("输入的内容: $text")
                } else {
                    updateResult("没有输入内容")
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

    // 自定义布局对话框
    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)
        val nameInput = dialogView.findViewById<android.widget.EditText>(R.id.nameInput)
        val emailInput = dialogView.findViewById<android.widget.EditText>(R.id.emailInput)

        AlertDialog.Builder(this)
            .setTitle("用户信息")
            .setView(dialogView)
            .setPositiveButton("提交") { _, _ ->
                val name = nameInput.text.toString()
                val email = emailInput.text.toString()
                updateResult("姓名: $name, 邮箱: $email")
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun updateResult(text: String) {
        resultText.text = text
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
