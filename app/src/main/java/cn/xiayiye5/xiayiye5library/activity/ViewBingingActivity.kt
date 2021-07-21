package cn.xiayiye5.xiayiye5library.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.xiayiye5.xiayiye5library.databinding.ActivityViewbindingBinding

/**
 * @author : xiayiye5
 * @date : 2021/7/21 17:51
 * 类描述 : 官方文档 https://developer.android.com/topic/libraries/view-binding#java
 */
class ViewBingingActivity : AppCompatActivity() {
    private lateinit var bindView: ActivityViewbindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = ActivityViewbindingBinding.inflate(layoutInflater)
        setContentView(bindView.root)
        initData()
    }

    private fun initData() {
        bindView.progressBar.progress = 80
        bindView.progressBar.max = 100
        bindView.switch1.textOff = "关"
        bindView.switch1.textOn = "开"
        bindView.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            bindView.switch1.text = if (isChecked) "打开了" else "关闭了"
            Toast.makeText(
                this@ViewBingingActivity,
                if (isChecked) "打开了" else "关闭了",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}