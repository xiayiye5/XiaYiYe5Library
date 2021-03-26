package cn.xiayiye5.xiayiye5library.activity;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.widget.TextView;

import cn.xiayiye5.xiayiye5library.R;
import cn.xiayiye5.xiayiye5library.other.SimpleJobIntentService;

/**
 * @author : xiayiye5
 * @date : 2021/3/23 10:24
 * 类描述 :
 * * static long getMobileRxBytes()//获取通过Mobile连接收到的字节总数，但不包含WiFi static long
 * * getMobileRxPackets()//获取Mobile连接收到的数据包总数 static long
 * * getMobileTxBytes()//Mobile发送的总字节数 static long
 * * getMobileTxPackets()//Mobile发送的总数据包数 static long
 * * getTotalRxBytes()//获取总的接受字节数，包含Mobile和WiFi等 static long
 * * getTotalRxPackets()//总的接受数据包数，包含Mobile和WiFi等 static long
 * * getTotalTxBytes()//总的发送字节数，包含Mobile和WiFi等 static long
 * * getTotalTxPackets()//发送的总数据包数，包含Mobile和WiFi等 static long
 * * getUidRxBytes(int uid)//获取某个网络UID的接受字节数 static long getUidTxBytes(int
 * * uid) //获取某个网络UID的发送字节数
 */
public class NetworkShowActivity extends BaseActivity {

    private TextView tvNetwork;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_network_show;
    }

    @Override
    protected void initId() {
        tvNetwork = findViewById(R.id.tvNetwork);
    }

    @Override
    protected void loadData() {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(tvNetwork, "alpha", 0.0f, 1.0f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvNetwork, "scaleX", 0.0f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tvNetwork, "scaleY", 0.0f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(alphaAnim, scaleX, scaleY);
        set.setDuration(3000);
        set.start();
        //请求成功，开始显示网速
        long mobileRxBytes = TrafficStats.getMobileRxBytes();
        long mobileRxPackets = TrafficStats.getMobileRxPackets();
        long mobileTxBytes = TrafficStats.getMobileTxBytes();
        long mobileTxPackets = TrafficStats.getMobileTxPackets();
        long totalRxBytes = TrafficStats.getTotalRxBytes();
        long totalRxPackets = TrafficStats.getTotalRxPackets();
        long totalTxBytes = TrafficStats.getTotalTxBytes();
        long totalTxPackets = TrafficStats.getTotalTxPackets();
        long uidRxBytes = TrafficStats.getUidRxBytes(1);
        NetworkStatsManager networkStatsManager = (NetworkStatsManager) getSystemService(Context.NETWORK_STATS_SERVICE);
        tvNetwork.setText("总下载流量为：" + (totalRxBytes / 8) + "kb");
        Intent intent = new Intent();
        intent.putExtra("intent", "haha");
        //开启后台任务
        SimpleJobIntentService.enqueueWork(this, intent);
    }

}
