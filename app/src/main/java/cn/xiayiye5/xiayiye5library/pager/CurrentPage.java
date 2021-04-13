package cn.xiayiye5.xiayiye5library.pager;

import java.io.Serializable;

/**
 * @author : xiayiye5
 * @date : 2021/4/13 11:09
 * 类描述 :
 */
public interface CurrentPage extends Serializable {
    /**
     * 设置当前页面
     *
     * @param position 位置
     */
    void setCurrentPage(int position);
}
