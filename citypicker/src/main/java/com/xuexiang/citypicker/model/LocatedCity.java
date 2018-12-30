package com.xuexiang.citypicker.model;

/**
 * 定位城市
 *
 * @author xuexiang
 * @since 2018/12/30 下午6:27
 */
public class LocatedCity extends City {

    public LocatedCity(String name, String province, String code) {
        super(name, province, "定位城市", code);
    }
}
