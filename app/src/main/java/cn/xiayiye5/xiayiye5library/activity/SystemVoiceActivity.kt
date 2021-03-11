package cn.xiayiye5.xiayiye5library.activity

import android.content.Context
import android.media.AudioManager
import android.util.Log
import android.widget.SeekBar
import cn.xiayiye5.xiayiye5library.R
import cn.xiayiye5.xiayiye5library.utils.SafeUtils
import kotlinx.android.synthetic.main.activity_system_voice.*

/**
 * @author : xiayiye5
 * @date : 2021/3/8 11:06
 * 类描述 : 调节系统音量的服务
 */
class SystemVoiceActivity : BaseActivity() {
    //获取系统音量服务,使用懒加载的方式
    private val audioManager: AudioManager by lazy { getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    override fun loadData() {
        //获取当前音量
        val currentVoice = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val callVoice = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL)
        //获取最大音量
        val maxVoice = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        //获取通话最大音量
        val maxCallVoice = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL)
        //设置seekBar
        sbSeekBar.max = maxVoice
        //设置通话最大声音
        sbVoiceSeekBar.max = maxCallVoice
//        sbSeekBar.min = minVoice
        sbSeekBar.progress = currentVoice
        //设置通话进度
        sbVoiceSeekBar.progress = callVoice
        //设置音乐点击事件
        sbSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.e("打印音量", "音量值${progress}")
                if (fromUser) {
                    audioManager.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        progress,
                        AudioManager.FLAG_PLAY_SOUND
//                        AudioManager.FLAG_SHOW_UI
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
        sbVoiceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.e("打印通话音量", "音量值${progress}")
                if (fromUser) {
                    audioManager.setStreamVolume(
                        AudioManager.STREAM_VOICE_CALL,
                        progress,
                        AudioManager.FLAG_PLAY_SOUND
//                        AudioManager.FLAG_SHOW_UI
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
        SafeUtils.getInstance().test()
    }
}