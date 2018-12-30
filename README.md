<p align="center">
<img src="art/header.png">
</p>

# CityPicker

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html) [![API](https://img.shields.io/badge/API-14%2B-yellow.svg?style=flat)](https://android-arsenal.com/api?level=14)

现在使用较多的类似美团、外卖等APP的城市选择界面，**一行代码搞定**，就是这么简单粗暴！！！

#### 主要功能：

-   字母悬浮栏
-   指定热门城市
-   自定义动画效果
-   自定义主题
-   名称或拼音搜索
-   返回城市名、code等数据
-   提供定位接口，解耦定位SDK

# Preview

![image](https://github.com/zaaach/CityPicker/raw/master/art/screen.gif) ![image](https://github.com/zaaach/CityPicker/raw/master/art/screen1.gif)
![image](https://github.com/zaaach/CityPicker/raw/master/art/screen2.gif)

# Download

1.先在项目根目录的 build.gradle 的 repositories 添加:
```
allprojects {
     repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

2.然后在dependencies添加:

```
dependencies {
  ...
  implementation 'com.github.xuexiangjys:citypicker:1.0.1'
  implementation 'com.android.support:appcompat-v7:27.1.1'
  implementation 'com.android.support:recyclerview-v7:27.1.1'
  implementation 'com.android.support:design:27.1.1'
}
```

# Usage

`CityPicker` 基于`DialogFragment` 实现，已提供定位接口，需要APP自身实现定位。

### 基本使用：

#### Step1:

在`manifest.xml`中给使用`CityPicker` 的`activity`添加主题`android:theme="@style/CityPickerTheme"`

```xml
<activity android:name=".MainActivity" android:theme="@style/CityPickerTheme">
  ......
</activity>
```

#### Step2:

注意：热门城市使用`HotCity` ，定位城市使用`LocatedCity` 

```java
List<HotCity> hotCities = new ArrayList<>();
hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
hotCities.add(new HotCity("上海", "上海", "101020100"));
hotCities.add(new HotCity("广州", "广东", "101280101"));
hotCities.add(new HotCity("深圳", "广东", "101280601"));
hotCities.add(new HotCity("杭州", "浙江", "101210101"));
......

CityPicker.from(MainActivity.this)
    .enableAnimation(enable)
    .setAnimationStyle(anim)
    .setLocatedCity(null)
    .setHotCities(hotCities)
    .setOnPickListener(new OnPickListener() {
        @Override
        public void onPick(int position, City data) {
            currentTV.setText(String.format("当前城市：%s，%s", data.getName(), data.getCode()));
            Toast.makeText(
                    getApplicationContext(),
                    String.format("点击的数据：%s，%s", data.getName(), data.getCode()),
                    Toast.LENGTH_SHORT)
                    .show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLocate(final OnLocationListener locationListener) {
            //开始定位，这里模拟一下定位
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    locationListener.onLocationChanged(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
                }
            }, 3000);
        }
    })
    .show();
```

### 关于自定义主题：

在`style.xml` 中自定义主题并且继承`CityPickerTheme` ，别忘了在`manifest.xml` 设置给`activity`。

```xml
<style name="CustomTheme" parent="CityPickerTheme">
        <item name="cpCancelTextColor">@color/color_green</item>
        <item name="cpSearchCursorDrawable">@color/color_green</item>
        <item name="cpIndexBarNormalTextColor">@color/color_green</item>
        <item name="cpIndexBarSelectedTextColor">@color/color_green</item>
        <item name="cpSectionHeight">@dimen/custom_section_height</item>
        <item name="cpOverlayBackground">@color/color_green</item>
  		......
</style>
```

`CityPicker` 中自定义的所有属性如下，有些属性值必须是引用类型`reference`，使用时注意。

```xml
<resources>
    <!--取消按钮的样式-->
    <attr name="cpCancelTextSize" format="dimension|reference" />
    <attr name="cpCancelTextColor" format="color|reference" />

    <!--搜索样式-->
    <attr name="cpClearTextIcon" format="reference" />
    <attr name="cpSearchTextSize" format="dimension|reference" />
    <attr name="cpSearchTextColor" format="color|reference" />
    <attr name="cpSearchHintText" format="string|reference" />
    <attr name="cpSearchHintTextColor" format="color|reference" />
    <attr name="cpSearchCursorDrawable" format="reference" />

    <!--城市列表样式-->
    <attr name="cpListItemTextSize" format="dimension|reference" />
    <attr name="cpListItemTextColor" format="color|reference" />
    <attr name="cpListItemHeight" format="dimension|reference"/>

    <!--空页面样式-->
    <attr name="cpEmptyIcon" format="reference"/>
    <attr name="cpEmptyIconWidth" format="dimension|reference"/>
    <attr name="cpEmptyIconHeight" format="dimension|reference"/>
    <attr name="cpEmptyText" format="string|reference"/>
    <attr name="cpEmptyTextSize" format="dimension|reference"/>
    <attr name="cpEmptyTextColor" format="color|reference"/>

    <!--热门城市样式-->
    <attr name="cpGridItemBackground" format="color|reference"/>
    <attr name="cpGridItemSpace" format="reference"/>

	<!--悬浮栏-->
    <attr name="cpSectionHeight" format="reference"/>
    <attr name="cpSectionTextSize" format="reference" />
    <attr name="cpSectionTextColor" format="reference" />
    <attr name="cpSectionBackground" format="reference" />

    <!--右侧字母索引-->
    <attr name="cpIndexBarTextSize" format="reference" />
    <attr name="cpIndexBarNormalTextColor" format="reference" />
    <attr name="cpIndexBarSelectedTextColor" format="reference" />

    <!--悬浮字母序提示-->
    <attr name="cpOverlayWidth" format="dimension|reference"/>
    <attr name="cpOverlayHeight" format="dimension|reference"/>
    <attr name="cpOverlayTextSize" format="dimension|reference"/>
    <attr name="cpOverlayTextColor" format="color|reference"/>
    <attr name="cpOverlayBackground" format="color|reference"/>
</resources>
```

# 感谢

[ImmersionBar](https://github.com/gyf-dev/ImmersionBar)
