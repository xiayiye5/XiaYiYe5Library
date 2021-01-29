package cn.xiayiye5.xiayiye5library.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import cn.xiayiye5.xiayiye5library.R;

public class ScrollerTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_text);
        ((TextView) findViewById(R.id.stv)).setMovementMethod(new ScrollingMovementMethod());
    }
}