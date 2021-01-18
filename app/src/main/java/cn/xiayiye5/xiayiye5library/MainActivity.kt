package cn.xiayiye5.xiayiye5library

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.xiayiye5.xiayiye5library.utils.MainThread
import cn.xiayiye5.xiayiye5library.utils.ThreadUtils
import cn.xiayiye5.xiayiye5library.utils.XiaYiYe5Toast

class MainActivity : AppCompatActivity() {
    lateinit var tvName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvName = findViewById(R.id.tvName)
        ThreadUtils.getInstance().createThread(runnables)
    }

    var runnables = Runnable {
        Log.e("打印线程1", Thread.currentThread().name)
        MainThread.getInstance().open {
            val name = Thread.currentThread().name
            Log.e("打印线程2", name)
            tvName.text = name
            XiaYiYe5Toast.getInstance().showText(name)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //移除线程
        ThreadUtils.getInstance().removeThread(runnables)
    }
}