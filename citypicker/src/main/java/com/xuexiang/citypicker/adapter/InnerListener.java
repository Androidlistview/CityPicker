package com.xuexiang.citypicker.adapter;


import com.xuexiang.citypicker.model.City;

/**
 * 列表的监听
 *
 * @author xuexiang
 * @since 2018/12/30 下午6:47
 */
public interface InnerListener {
    /**
     * 选择
     *
     * @param position
     * @param city
     */
    void pick(int position, City city);

    /**
     * 定位
     */
    void locate();
}
