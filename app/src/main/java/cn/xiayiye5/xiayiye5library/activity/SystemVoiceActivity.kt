package cn.xiayiye5.xiayiye5library.activity

import android.content.Context
import android.media.AudioManager
import android.util.Log
import android.widget.SeekBar
import cn.xiayiye5.xiayiye5library.R
import kotlinx.android.synthetic.main.activity_system_voice.*

/**
 * @author : xiayiye5
 * @date : 2021/3/8 11:06
 * 类描述 : 调节系统音量的服务
 */
class SystemVoiceActivity : BaseActivity() {
    lateinit var audioManager: AudioManager
    override fun loadData() {
        //获取系统音量服务
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        //获取当前音量
        val currentVoice = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        //获取最大音量
        val maxVoice = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        //设置seekBar
        sbSeekBar.max = maxVoice
//        sbSeekBar.min = minVoice
        sbSeekBar.progress = currentVoice
        sbSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.e("打印音量", "音量值${progress}")
                if (fromUser) {
                    audioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        progress,
                        AudioManager.FLAG_PLAY_SOUND
                    )
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // nothing to do
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // nothing to do
            }
        })
    }

    override fun getLayoutView(): Int = R.layout.activity_system_voice

    override fun initId() {
        // nothing to do
    }
}