# 万能ViewPager2适配器--SmartViewPager2Adapter
[![](https://jitpack.io/v/lihangleo2/SmartViewPager2Adapter.svg)](https://jitpack.io/#lihangleo2/SmartViewPager2Adapter)
[![](https://img.shields.io/badge/license-MIT-green)](https://github.com/lihangleo2/ViewPager2Demo/blob/main/LICENSE)
## 特点功能
完全脱离xml，所有效果只需要通过api调用
```
具体功能：
    1. 两句代码实现抖音列表效果
    2. 无感且丝滑，动态从头部或者底部加载数据
    3. 设置上下加载监听，再达到预加载limit的时候触发监听
    4. 实现数据源接口，和Fragment接口，你会体验到什么是丝滑
    5. 画廊的实现，不再需要在xml设置clipChildren属性，调用即可实现。
    6. 极限脱离xml控制，以简化使用者使用
    7. 支持无线循环模式
    8. 支持自动滚动模式；设置滚动时长，设置循环间隔时长
    9. 循环滚动可绑定页面lifeCycle生命周期

  
```


## SmartViewPager2Adapter动态
* [SmartViewPager2Adapter2.0.2更新动态及以往版本](https://github.com/lihangleo2/ViewPager2Demo/wiki)


## Demo
为录制流畅，截图分辨率比较模糊。可在下方扫描二维码下载apk  

![](https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/QRCode_336.png)
<br/>

## 效果展示
为录制流畅，截图分辨率模糊。可下载apk查看真机效果
* ### 基础功能展示 [挑战至此文档](https://github.com/lihangleo2/ViewPager2Demo#%E4%B8%80%E4%B8%A4%E5%8F%A5%E4%BB%A3%E7%A0%81%E5%AE%9E%E7%8E%B0%E6%8A%96%E9%9F%B3%E6%95%88%E6%9E%9C)
|几句代码实现抖音列表|向上或向下加载数据|设置加载监听|
|:---:|:---:|:---:|
|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/base.gif" alt="Sample"  width="100%">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/loadfront.gif" alt="Sample"  width="100%">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/listener.gif" alt="Sample"  width="100%">
<br/>

* ### 画廊功能展示 [挑战至此文档](https://github.com/lihangleo2/ViewPager2Demo#%E4%BA%8C%E7%94%BB%E5%BB%8A%E6%95%88%E6%9E%9C)
|asGallery一句代码搞定|3d画廊|
|:---:|:---:|
|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/gallery.gif" alt="Sample">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/gallery3d.gif" alt="Sample">
<br/>

* ### version2.0
|无线循环模式|自动滚动模式|
|:---:|:---:|
|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/infinite.gif" alt="Sample">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/loop.gif" alt="Sample">
<br/>



## 添加依赖

- 项目build.gradle添加如下
  ```java
  allprojects {
       repositories {
           maven { url 'https://jitpack.io' }
       }
   }
  ```
- app build.gradle添加如下
   ```java
  dependencies {
           implementation 'com.github.lihangleo2:SmartViewPager2Adapter:2.0.2'
   }
  ```
<br/>

## 基本使用
### 一、两句代码实现抖音效果
使用此库，你只需在xml加上简单的viewPager2即可，其他只需调用方法即可
#### 1.1、步骤一：初始化adapter
```java
    
    private val mAdapter by lazy {
        SmartViewPager2Adapter(this, mBinding.viewPager2)
            .cancleOverScrollMode()
            .setOffscreenPageLimit(5)
            .setPreLoadLimit(3)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            //可以在这里初始化数据
            .addData(list)
    }

```
<br>

#### 1.2、步骤二：设置给viewpager2（做完这下就搞定了，你没看错）
```
mBinding.viewPager2.adapter = mAdapter
```
<br>

#### 1.3、步骤三：list数据源（注意点）；bean对象要实现接口：SmartFragmentTypeExEntity
解析数据bean要实现SmartFragmentTypeExEntity接口，返回你在adapter里要生成的fragment的type。(也就是说type==1时生成图片fragment，这些逻辑adapter帮你操作了)
```java
public class SourceBean implements SmartFragmentTypeExEntity {
    int type;

    @Override
    public int getFragmentType() {
        return type;
    }
}
```
<br>

#### 1.4、步骤四：fragment（注意点）
* 可以看到方法.addFragment(type, Fragment.class)，这里结合步骤三就很清楚了。
* 比如上面的基础使用里调用了.addFragment(1, ImageFragment::class.java)。最后加载了数据.addData(list)，这样adapter会自动在数据里找到对应的tyep，然后生成你要的fragment。

注意你的fragment必须实现SmartFragmentImpl接口，这个接口是让adapter把数据回传给你，以便你做页面操作
```java
public class ImageFragment extends Fragment implements SmartFragmentImpl {
    //....伪代码
    @Override
    public void initSmartFragmentData(SmartFragmentTypeExEntity bean) {
        this.mSourceBean = bean as SourceBean
    }
}
```

<br/>


### 二、画廊效果
画廊只需要加上如下代码，无需在xml里写clipChildren="false"这些代码，解放xml
```
//如果是横向就是左右，竖直的话就是上下。adapter会自己判断
.asGallery(int leftMargin,int rightMargin)

//想要加上滑动效果只需要加上（后续会加上更多效果）
setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE)
```
<br>

### 三、方法详解
这里我会把重要的方法拿出来讲，其他的会出个表格

#### 3.1 数据加载

* 向下无感加载数据
  ```
  .addData(List<SmartFragmentTypeExEntity> list)
  ```

* 向上无感加载数据
  ```
  .addFrontData(List<SmartFragmentTypeExEntity> list)
  ```

* 加载对应type的fragment.class
  ```
  .addFragment(type, Fragment.class)
  ```
<br>

#### 3.2 设置监听

* 设置头部加载监听（不设置则不触发）
  ```
  .setOnRefreshListener(OnRefreshListener listener)
  ```

* 设置底部加载监听（不设置则不触发）
  ```
  .setLoadMoreListener(OnLoadMoreListener listener)
  ```

* 同时设置头部和底部监听（不设置则不触发）
  ```
  .setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener listener)
  ```
<br>

#### 3.2 结束监听

* 头部已经不能翻页时，调用。将不再触发头部监听。
  ```
  .finishRefreshWithNoMoreData()
  ```

* 底部已经不能翻页时，调用。将不再触发底部监听。
  ```
  .finishLoadMoreWithNoMoreData()
  ```

#### 3.3 ViewPager2滑动效果

* SmartTransformer.TRANSFORMER_3D 3d滑动效果
* SmartTransformer.TRANSFORMER_ALPHA_SCALE 缩放透明度效果
  ```
  .setPagerTransformer(SmartTransformer enum)
  ```
  

其他方法
|name|format|description|
|:---:|:---:|:---:|
|.getDataList()|List\<SmartFragmentTypeExEntity\>|返回数据源|
|.addDefaultFragment()|SmartFragmentImpl|找不到对应type的默认fragment|
|cancleOverScrollMode()|void|取消viewpager2边缘阴影|
|setVertical()|boolean|是否设置竖直viewpager2，默认横向|
|setOffscreenPageLimit()|integer|设置预加载数量|
|setPreLoadLimit()|integer|设置滑动到limit触发预加载监听|

<br/>

## 赞赏

如果你喜欢 SmartViewPager2Adapter 的功能，感觉 SmartViewPager2Adapter 帮助到了你，可以点右上角 "Star" 支持一下 谢谢！ ^_^
你也还可以扫描下面的二维码~ 请作者喝一杯咖啡。或者遇到工作中比较难实现的需求请作者帮忙。

![](https://github.com/lihangleo2/ShadowLayout/blob/master/showImages/pay_ali.png) ![](https://github.com/lihangleo2/ShadowLayout/blob/master/showImages/pay_wx.png)


如果在捐赠留言中备注名称，将会被记录到列表中~ 如果你也是github开源作者，捐赠时可以留下github项目地址或者个人主页地址，链接将会被添加到列表中
### [捐赠列表](https://github.com/lihangleo2/ShadowLayout/blob/master/showImages/friend.md)

<br/>



## 其他作品
[万能阴影布局](https://github.com/lihangleo2/ShadowLayout)

<br/>



## 关于作者。
Android工作多年了，如果你在学习的路上也感觉孤独，请和我一起。让我们在学习道路上少些孤独
<!-- * [关于我的经历](https://mp.weixin.qq.com/s?__biz=MzAwMDA3MzU2Mg==&mid=2247483667&idx=1&sn=1a575ea2c636980e5f4c579d3a73d8ab&chksm=9aefcb26ad98423041c61ad7cbad77f0534495d11fc0a302b9fdd3a3e6b84605cad61d192959&mpshare=1&scene=23&srcid=&sharer_sharetime=1572505105563&sharer_shareid=97effcbe7f9d69e6067a40da3e48344a#rd) -->
* QQ群： 209010674 <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5e29576e7d2ebf08fa37d8953a0fea3b5eafdff2c488c1f5c152223c228f1d11"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="android交流群" title="android交流群"></a>（点击图标，可以直接加入）

<br/>



## Licenses

```
MIT License

Copyright (c) 2023 leo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```


