package cn.xiayiye5.xiayiye5library

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
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
        tvName.setOnClickListener {
            Toast.makeText(this, "点击了", Toast.LENGTH_LONG).show()
            showTimeDialog();
        }
    }

    private fun showTimeDialog() {
//        TimePickerDialog(this, 3, OnTimeSetListener { view, hourOfDay, minute ->
//            val houre = hourOfDay
//            if (minute < 10) {
//                tvName.setText(houre.toString() + ":" + "0" + minute)
//            } else {
//                tvName.setText(houre.toString() + ":" + minute)
//            }
//        }, 0, 0, true).show()

        DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT).show()
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