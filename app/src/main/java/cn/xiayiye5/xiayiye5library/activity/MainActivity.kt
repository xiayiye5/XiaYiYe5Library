package cn.xiayiye5.xiayiye5library.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.xiayiye5.xiayiye5library.R
import cn.xiayiye5.xiayiye5library.RecyclerViewPage
import cn.xiayiye5.xiayiye5library.dialog.XiaYiYe5Dialog
import cn.xiayiye5.xiayiye5library.pager.GuideActivity
import cn.xiayiye5.xiayiye5library.thread.MainThread
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

    fun showTimeDialog(view: View) {
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

    fun showSimple(view: View) {
        XiaYiYe5Dialog.getInstance().getSimpleDialog(this)

    }

    fun showMore(view: View) {
        XiaYiYe5Dialog.getInstance().getMultiDialog(this)
    }

    fun showDiffer(view: View) {
        XiaYiYe5Dialog.getInstance()
            .createViewDialog(this, layoutInflater.inflate(R.layout.activity_dialog, null, false))
    }

    fun showList(view: View) {
        XiaYiYe5Dialog.getInstance().listDialog(this)
    }

    fun goRecyclerPage(view: View) {
        startActivity(Intent(this, RecyclerViewPage::class.java))
    }

    fun scrollerText(view: View) {
        startActivity(Intent(this, ScrollerTextActivity::class.java))
    }

    fun systemVoice(view: View) {
        startActivity(Intent(this, SystemVoiceActivity::class.java))
    }

    fun scrollerLayout(view: View) {
        startActivity(Intent(this, ScrollerLayoutActivity::class.java))
    }

    fun bottomScrollerOut(view: View) {
        startActivity(Intent(this, BottomScrollerOutActivity::class.java))
    }

    fun changeLanPage(view: View) {
        startActivity(Intent(this, ChangeLanguageActivity::class.java))
    }

    fun networkPage(view: View) {
        startActivity(Intent(this, NetworkShowActivity::class.java))
    }

    fun scalePage(view: View) {
        startActivity(Intent(this, ScaleActivity::class.java))
    }

    fun recodePage(view: View) {
        startActivity(Intent(this, RecodeActivity::class.java))
    }

    fun fragmentPage(view: View) {
        startActivity(Intent(this, FragmentPageActivity::class.java))
    }

    fun guidePage(view: View) {
        startActivity(Intent(this, GuideActivity::class.java))
    }

    fun weekDayPage(view: View) {
        startActivity(Intent(this, WeekDayActivity::class.java))
    }

    fun motionLayoutPage(view: View) {
        startActivity(Intent(this, MotionLayoutActivity::class.java))
    }

    fun memoryLeakPage(view: View) {
        startActivity(Intent(this, MemoryLeakActivity::class.java))
    }
}