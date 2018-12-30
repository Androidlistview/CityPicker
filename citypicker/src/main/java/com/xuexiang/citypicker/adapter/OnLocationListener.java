package com.xuexiang.citypicker.adapter;

import com.xuexiang.citypicker.model.LocatedCity;

/**
 * 定位监听
 *
 * @author xuexiang
 * @since 2018/12/30 下午8:53
 */
public interface OnLocationListener {

    /**
     * 定位发送变化
     *
     * @param location
     * @param state
     */
    void onLocationChanged(LocatedCity location, int state);
}

