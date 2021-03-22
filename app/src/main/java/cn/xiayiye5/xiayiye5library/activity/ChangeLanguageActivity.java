package cn.xiayiye5.xiayiye5library.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import cn.xiayiye5.xiayiye5library.R;

/**
 * @author : xiayiye5
 * @date : 2021/3/22 15:52
 * 类描述 :
 */
public class ChangeLanguageActivity extends BaseActivity {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_lan;
    }

    @Override
    protected void initId() {

    }

    @Override
    protected void loadData() {

    }

    public void changeLan(View view) {
        String locale = "null";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Locale primaryLocale = getResources().getConfiguration().getLocales().get(0);
            locale = primaryLocale.getDisplayName();
        }
        Configuration config = getResources().getConfiguration();
        String language = config.locale.getLanguage();
        if ("en".equals(language)) {
            config.setLocale(Locale.CHINA);
        } else if ("zh".equals(language)) {
            config.setLocale(Locale.ENGLISH);
        }
        Toast.makeText(this, language + "点击了切换" + locale, Toast.LENGTH_LONG).show();
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
//        createConfigurationContext(config);
        startActivity(new Intent(this, ChangeLanguageActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }
}
