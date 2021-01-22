package cn.xiayiye5.xiayiye5library.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.Toast;


/**
 * 控制内容的长度的监听器
 *
 * @author wangfubin
 */
public class EditTextUtils {
    public static void setupLengthFilter(EditText inputText,
                                         final Context context, final int maxLength, final boolean showToast) {
        // Create a new filter
        InputFilter.LengthFilter filter = new InputFilter.LengthFilter(
                maxLength) {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                if (source != null
                        && source.length() > 0
                        && (((dest == null ? 0 : dest.length()) + dstart - dend) == maxLength)) {
                    if (showToast) {
                        Toast.makeText(context, "没有更多了",
                                Toast.LENGTH_SHORT).show();
                    }
                    return "";
                }
                return super.filter(source, start, end, dest, dstart, dend);
            }
        };

        // Find exist lenght filter.
        InputFilter[] filters = inputText.getFilters();
        int length = 0;
        for (int i = 0; i < filters.length; i++) {
            if (!(filters[i] instanceof InputFilter.LengthFilter)) {
                length++;
            }
        }

        // Only one length filter.
        InputFilter[] contentFilters = new InputFilter[length + 1];
        for (int i = 0; i < filters.length; i++) {
            if (!(filters[i] instanceof InputFilter.LengthFilter)) {
                contentFilters[i] = filters[i];
            }
        }
        contentFilters[length] = filter;
        inputText.setFilters(contentFilters);
    }
}
