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
* [SmartViewPager2Adapter更新动态及以往版本](https://github.com/lihangleo2/ViewPager2Demo/wiki)
* 3.0后，更新了部分api,具体可以点击上面链接查看。从2.0升级到3.0的同学请注意。[不方便转移的，请查看2.0文档](https://github.com/lihangleo2/ViewPager2Demo/blob/main/README2.0.md)


## Demo
为录制流畅，截图分辨率比较模糊。可在下方扫描二维码下载apk  

![](https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/QRCode_336.png)
<br/>

## 效果展示
为录制流畅，截图分辨率模糊。可下载apk查看真机效果
* ### 基础功能展示 
|几句代码实现抖音列表|向上或向下加载数据|设置加载监听|
|:---:|:---:|:---:|
|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/base.gif" alt="Sample"  width="100%">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/loadfront.gif" alt="Sample"  width="100%">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/listener.gif" alt="Sample"  width="100%">
<br/>

* ### 画廊效果
|asGallery一句代码搞定|3d画廊|
|:---:|:---:|
|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/gallery.gif" alt="Sample">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/gallery3d.gif" alt="Sample">
<br/>

* ### 无限循环和自动滚动
|无限循环|自动滚动|
|:---:|:---:|
|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/infinite.gif" alt="Sample">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/loop.gif" alt="Sample">
<br/>

* ### 指示器功能及边缘滑动监听
|指示器的使用|指示器自动更新|边缘滑动监听|
|:---:|:---:|:---:|
|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/indicator.gif" alt="Sample"  width="100%">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/indicator_add.gif" alt="Sample"  width="100%">|<img src="https://github.com/lihangleo2/ViewPager2Demo/blob/main/showImages/slide.gif" alt="Sample"  width="100%">
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
           implementation 'com.github.lihangleo2:SmartViewPager2Adapter:3.0.0'
   }
  ```
<br/>

## 一、基本使用 
使用此库，你只需在xml加上简单的viewPager2即可，其他只需调用方法即可
### 1.1、两句代码实现抖音效果：步骤一：初始化adapter
```java
    
    private val mAdapter by lazy {
        //SourceBean是你使用的数据泛型
        SmartViewPager2Adapter.Builder<SourceBean>(this)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .build(mBinding.viewPager2)
            //可以在这里初始化数据
            .addData(list)
    }
```
<br>

### 1.2、步骤二：设置给viewpager2（做完这下就搞定了，你没看错）
```java
mBinding.viewPager2.adapter = mAdapter
```
<br>

### 1.3、步骤三：数据源对象bean要实现接口：SmartFragmentTypeExEntity
```java
public class SourceBean extends SmartFragmentTypeExEntity {
    int type;

    @Override
    public int getFragmentType() {
        return type;
    }
}
```
数据源bean实现此即可后，例如：通过方法.addFragment(1, ImageFragment::class.java)(也就是说type==1时生成ImageFragment，这些逻辑adapter帮你操作了)

<br>

### 1.4、步骤四：目标fragment要实现接口：SmartFragmentImpl<T>
```java
public class ImageFragment extends Fragment implements SmartFragmentImpl<SourceBean> {
    //....伪代码
    @Override
    public void initSmartFragmentData(SourceBean bean) {
        this.mSourceBean = bean
    }
}
```
要使用SmartViewPager2Adapter，你的目标fragment必须要实现SmartFragmentImpl<T>接口，adapter会将数据回传给fragment

<br/>


## 二、数据加载及监听
### 2.1、数据类api
* 向下无感加载数据
  ```java
  mAdapter.addData(list)
  ```

* 向上无感加载数据
  ```java
  mAdapter.addFrontData(list)
  ```

* 移除数据
  ```java
  mAdapter.removeData(index: Int)
  ```

* 根据position获取对象
  ```java
  //getLastItem() 获取last item
  //getItemOrNull() 不存在position会返回null
  var item = mAdapter.getItem(index: Int)
  ```

* 获取数据源list
  ```java
  var list = mAdapter.getData()
  ```

### 2.2、监听类api
使用加载监听可以搭配此设置。不设置则默认是3
  ```java
  //设置滑动到preLoadLimit触发预加载监听
  .setPreLoadLimit(3)
  ```

* 设置头部加载监听（不设置则不触发）
  ```java
            mAdapter.setOnRefreshListener(object : OnRefreshListener {
                override fun onRefresh(smartAdapter: SmartViewPager2Adapter<*>) {
                    //滑动到preLoadLimit后触发头部加载监听

                }
            })
  ```

* 设置底部加载监听（不设置则不触发）
  ```java
            mAdapter.setOnLoadMoreListener(object :OnLoadMoreListener{
                override fun onLoadMore(smartAdapter: SmartViewPager2Adapter<*>) {
                    //滑动到preLoadLimit后触发底部加载监听
  
                }
            })
  ```

* 同时设置头部和底部监听（不设置则不触发）
  ```java
            mAdapter.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onRefresh(smartAdapter: SmartViewPager2Adapter<*>) {
                    
                }

                override fun onLoadMore(smartAdapter: SmartViewPager2Adapter<*>) {
                    
                }

            })
  ```

* 设置ViewPager2边缘滑动监听（不设置则不触发）
  ```java
        mAdapter.setOnSideListener(object :onSideListener{
            override fun onLeftSide() {
                ToastUtils.showShort("触发左边缘事件")
            }

            override fun onRightSide() {
                ToastUtils.showShort("触发右边缘事件")
            }

        })
  ```
<br>

### 2.3 数据加载其他方法

* 头部已经不能翻页时，调用。将不再触发头部监听。
  ```java
  mAdapter.finishRefreshWithNoMoreData()
  ```

* 底部已经不能翻页时，调用。将不再触发底部监听。
  ```java
  mAdapter..finishLoadMoreWithNoMoreData()
  ```

* 结束头部刷新状态，继续触发监听
  ```java
  //1.注意调用mAdapter.addData(list)和mAdapter.addFrontData(list)也会触发此效果，无须主动调用
  //2.此方法针对调用加载接口时，接口异常等无数据情况下需要主动调用
  mAdapter.finishRefresh()
  ```
  
* 结束底部刷新状态，继续触发监听
  ```java
  //同上
  mAdapter.finishLoadMore()
  ```

<br>

## 三、画廊效果
画廊只需要加上如下代码，无需在xml里写clipChildren="false"这些代码，解放xml
```java
    private val mAdapter by lazy {
        SmartViewPager2Adapter.Builder<SourceBean>(this)
            //实现画廊功能，参数为左右间距
            .asGallery(ConvertUtils.dp2px(50f),ConvertUtils.dp2px(50f))
            //设置滑动效果
            .setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE)
            .addFragment(1,ImageFragment::class.java)
            .addFragment(2,TextFragment::class.java)
            .build(mBinding.viewPager2)
            .addData(list)
    }
```

<br>

## 四、无线循环和自动滚动
```java
    private val mAdapter by lazy {
        SmartViewPager2Adapter.Builder<SourceBean>(this)
            //实现无线循环模式
            .setInfinite(true)
            //实现自动滚动
            .setAutoLoop(true)
            //自动滚动下绑定页面生命周期：即离开页面暂停滚动，回到页面恢复滚动
            .addLifecycleObserver()
            //滚动间隔时间
            .setLoopTime(3000L)
            //切换轮播滚动速度
            .setScrollTime(600L)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .build(mBinding.viewPager2)
            .addData(list)
    }
```

<br>

## 五、指示器的使用
指示器的使用也是非常的简单，如下：（demo里的IndicatorActivity有具体用法）。

### 5.1、api使用指示器
特别注意，使用withIndicator(SmartIndicator.CIRCLE) api，必须是viewPage2的父控件是ConstraintLayout布局，否则建议使用xml里的方式，不受父控件约束
```java
    private val mAdapter by lazy {
        SmartViewPager2Adapter.Builder<SourceBean>(this)
            //圆形指示器：SmartIndicator.CIRCLE   线性指示器：SmartIndicator.LINE 
            .withIndicator(SmartIndicator.CIRCLE)

            //以上会默认在居中靠下的位置，设置位置你可以使用以下api
            //.withIndicator(SmartIndicator.CIRCLE,SmartGravity.LEFT_BOTTOM, ConvertUtils.dp2px(20f),ConvertUtils.dp2px(20f)) 
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .build(mBinding.viewPager2)
            .addData(list)
    }
```
<br>

### 5.2 布局xml里使用指示器：
你还可以将指示器放在xml里，这样可以更强自定义指示器样式，及把指示器放置你布局里想放置的任何位置，不受父控件影响
xml里如下：
```xml
    <com.smart.adapter.indicator.CircleIndicator
        android:id="@+id/circle_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginRight="10dp"
        android:layout_marginBottom="10dp"
        app:layout_constraintBottom_toBottomOf="@+id/viewPager2"
        app:layout_constraintRight_toRightOf="parent"
        app:lh_indicator_mode="fill"
        app:lh_indicator_radius="10dp"
        app:lh_indicator_scrollWithViewPager2="true"
        app:lh_indicator_selectColor="#FF0101"
        app:lh_indicator_space="10dp"
        app:lh_indicator_strokeWidth="1.5dp"
        app:lh_indicator_unselectColor="#FFEB3B" />
```

代码如下：
```java
    private val mAdapter by lazy {
        SmartViewPager2Adapter.Builder<SourceBean>(this)
            //关键代码，xml里的指示器
            .withIndicator(mBinding.circleIndicator)
            .addFragment(1, ImageFragment::class.java)
            .addFragment(2, TextFragment::class.java)
            .build(mBinding.viewPager2)
            .addData(list)
    }
```
<br>

## 六、刷新问题和视频的用法问题
本库开源以后，有很多说刷新问题和视频的问题，这个这里详细讲解一下

### 6.1 刷新问题：
我们首先是通过.addData()去设置数据源的，然后到了我们的fragment里。以下代码，在fragment里你可以拿到mSourceBean，注意这里的内存地址和数据源list里的内存地址指向一个地方的。记住这一点就好办了。
```java
public class ImageFragment extends Fragment implements SmartFragmentImpl2<SourceBean> {
    private SourceBean mSourceBean;
    //....伪代码
    @Override
    public void initSmartFragmentData(SourceBean bean) {
        this.mSourceBean = bean
    }

    //....伪代码
    private void initView(){
        //一般封装的baseFragement都通过一个方法去初始化页面
        //当然你也可以叫initData()这里随便。拿到mSourceBean去初始化页面
    }
}
```

<br>

需求来了：假设你有一个点赞按钮，点击了，从未点赞状态变成了红色点赞状态如下：

```java
btn.setOnClickListener{
    //第一步：（因为你在fragment里），你要去改变按钮ui的样式
    //第二步：【重点】你要去修改数据源里的点赞字段假设：mSourceBean.isClick = 1
    //执行了第二步以后，你的数据源的isClick字段改变了。因为内存地址一样，数据源list里的数据也改变了。
    //所以，当你随便滑动你的viewPager2，即使超过offscreenPageLimit，
    //页面被销毁了，然后重建，因为数据源在这，样式也会恢复之前的状态。
    //其实就和RecycleView里的Adapter里的用法是一样的
}
```
以上是对此库页面刷新的用法。其实很简单
<br>

### 6.2、视频在fragment里的用法：
视频用法也是极其简单，我这里放我baseFragment里的封装，我相信大部分开发者都会有一个base，如下：
```java
public abstract BaseFragment{
    //....伪代码
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化子布局
        initView()
    }

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }

        if (!isHidden) {
            onVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        onInVisible()
    }
}
```
这里有3个方法
* initView() 在这里你可以初始化你的视频数据，甚至preLoad(因为缓存问题会加载很多个fragment,只要你不播放视频即可)
* onVisible() 页面可见时，播放视频
* onInVisible() 页面不可见时，暂停播放。假设你滑回这个页面你想从第一帧开始播放，你可以将视频seekTo到第0秒的时间。可以根据你的需求来。

希望我表达清楚了。看懂了也是非常好用！感谢，让我们共同维护这个库，让你在使用ViewPager2的时候，是如此简单



<br>

## 方法详解
```java
    private val mAdapter by lazy {
        SmartViewPager2Adapter.Builder<SourceBean>(this) //SourceBean 泛型bean
            //构造参数（3.0后部分api不可动态改变，规范及正确使用）
            .overScrollNever() //取消viewPager2滑动边缘阴影
            .canScroll(false) //viewPager2不可手势滑动
            .setOffscreenPageLimit(5) //滑动到preLoadLimit触发预加载监听
            .setPreLoadLimit(3) //设置viewPager2缓存
            .addFragment(1, ImageFragment::class.java) //添加要生成的fragment
            .addFragment(2, TextFragment::class.java)
            .addDefaultFragment(TextFragment::class.java) //找不到对应type的数据时生成的默认fragment
            .asGallery(50,50) //实现画廊
            .setPagerTransformer(SmartTransformer.TRANSFORMER_ALPHA_SCALE) //设置滑动效果
            .withIndicator(SmartIndicator.CIRCLE) // 添加指示器
            .setVertical(true) //viewPager2是否竖直方向
            .setInfinite(true) //实现无线循环模式
            .setAutoLoop(true) //实现自动滚动
            .addLifecycleObserver() //自动滚动下绑定页面生命周期：即离开页面暂停滚动，回到页面恢复滚动
            .setLoopTime(3000L) //滚动间隔时间
            .setScrollTime(600L) //切换轮播滚动速度
            .build(mBinding.viewPager2) //绑定viewPager2

            //方法
            .addData(list) //添加数据
            .addFrontData(list) //添加头部数据
            .removeData(index) //移除数据
            .addLifecycleObserver() //自动滚动下绑定页面生命周期：即离开页面暂停滚动，回到页面恢复滚动
            .removeLifecycleObserver() //移除页面监听
            .finishRefresh() //结束头部刷新状态，继续触发监听(addData，addFrontData默认调用此方法)，此方法针对数据加载失败时需要主动调用
            .finishLoadMore() //同上
            .finishRefreshWithNoMoreData() //头部已经不能翻页时，调用。将不再触发头部监听。
            .finishLoadMoreWithNoMoreData() //底部已经不能翻页时，调用。将不再触发底部监听。

            //监听
            .setOnRefreshLoadMoreListener(object :OnRefreshLoadMoreListener{ //加载监听
                override fun onRefresh(smartAdapter: SmartViewPager2Adapter<*>) {
                    //滑动到preLoadLimit后触发头部加载监听
                }

                override fun onLoadMore(smartAdapter: SmartViewPager2Adapter<*>) {
                    //滑动到preLoadLimit后触发底部加载监听
                }

            })
            .setOnSideListener(object : onSideListener { //左右边界滑动监听
                override fun onLeftSide() {
                    //触发左边缘事件

                }
                override fun onRightSide() {
                    //触发右边缘事件
                }

            })
    }

```

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
* QQ群： 209010674 <a target="_blank" href="http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=C5RJFvVexskcqccqO1ORpLID9dBxlbIM&authKey=aax93zJjnA2San0TA0VEIc%2BLU9RDtstZ7BD7pz3FPdJRjlOu8%2Ffb%2BDNSX0Cz6hbr&noverify=0&group_code=209010674"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="android交流群" title="android交流群"></a>（点击图标，可以直接加入）

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


