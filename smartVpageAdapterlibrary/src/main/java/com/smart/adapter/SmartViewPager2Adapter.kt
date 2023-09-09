package com.smart.adapter

import androidx.annotation.IntDef
import androidx.annotation.RestrictTo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.smart.adapter.interf.OnLoadMoreListener
import com.smart.adapter.interf.OnRefreshListener
import com.smart.adapter.interf.OnRefreshLoadMoreListener
import com.smart.adapter.interf.SmartFragmentImpl
import com.smart.adapter.interf.SmartFragmentTypeExEntity
import com.smart.adapter.transformer.SmartTransformer
import com.smart.adapter.transformer.StereoPagerTransformer
import com.smart.adapter.transformer.StereoPagerVerticalTransformer
import com.smart.adapter.transformer.TransAlphScaleFormer
import com.smart.adapter.util.ViewPager2Util


/**
 * @Author leo2
 * @Date 2023/9/4
 */
class SmartViewPager2Adapter : FragmentStateAdapter {
    private val mDataList = mutableListOf<SmartFragmentTypeExEntity>()
    private val mPreloadDataList = mutableListOf<SmartFragmentTypeExEntity>()
    private lateinit var mViewPager2: ViewPager2
    private var mRefreshListener: OnRefreshListener? = null
    private var mLoadMoreListener: OnLoadMoreListener? = null
    private val fragments = mutableMapOf<Int, Class<*>>()
    private var defaultFragment: Class<*>? = null

    //预加载litmit,当滑动到只剩余limit个数后，触发加载刷新监听
    //如果，当前个数小于mPreLoadLimit*2+1时，优先触发loadMore监听
    private var mPreLoadLimit: Int = 3


    constructor(fragmentActivity: FragmentActivity, bindViewPager2: ViewPager2) : super(
        fragmentActivity
    ) {
        this.mViewPager2 = bindViewPager2
        initSmartViewPager()
    }

    constructor(fragment: Fragment, bindViewPager2: ViewPager2) : super(fragment) {
        this.mViewPager2 = bindViewPager2
        initSmartViewPager()
    }

    constructor(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle,
        bindViewPager2: ViewPager2,
    ) : super(fragmentManager, lifecycle) {
        //源码都会走这里
        this.mViewPager2 = bindViewPager2
        initSmartViewPager()
    }


    fun setOnRefreshListener(listener: OnRefreshListener): SmartViewPager2Adapter {
        this.mRefreshListener = listener
        return this
    }

    fun setLoadMoreListener(listener: OnLoadMoreListener): SmartViewPager2Adapter {
        this.mLoadMoreListener = listener
        return this
    }

    fun setOnRefreshLoadMoreListener(listener: OnRefreshLoadMoreListener): SmartViewPager2Adapter {
        this.mRefreshListener = listener
        this.mLoadMoreListener = listener
        checkIndexAndCallBack(mViewPager2.currentItem)
        return this
    }

    private fun checkIndexAndCallBack(position: Int) {
        if (mLoadMoreListener != null) {
            registLoadMoreOrNot(position)
        }
        if (mRefreshListener != null) {
            registRefreshOrNot(position)
        }
    }


    override fun createFragment(position: Int): Fragment {
        var bean = mDataList[position]
        if (fragments.isEmpty()) {
            throw IllegalArgumentException("the fragments can not be empty");
        }
        var targetFragment = fragments[bean.getFragmentType()]
        var realFragment = targetFragment?.newInstance() as Fragment
        var smartFrgamentImpl = realFragment as SmartFragmentImpl
        smartFrgamentImpl.initSmartFragmentData(bean)
        return realFragment
    }

    override fun getItemCount() = mDataList.size

    override fun getItemId(position: Int): Long {
        return mDataList[position].hashCode().toLong()
    }

    fun addData(list: MutableList<SmartFragmentTypeExEntity>): SmartViewPager2Adapter {
        if (list.isNullOrEmpty()) {
            return this
        }
        var lastIndex = mDataList.size
        mDataList.addAll(list)
        notifyItemRangeChanged(lastIndex, list.size)
        updateRefreshLoadMoreState()
        return this
    }

    fun addData(bean: SmartFragmentTypeExEntity): SmartViewPager2Adapter {
        if (bean == null) {
            return this
        }
        var lastIndex = mDataList.size
        mDataList.add(bean)
        notifyItemRangeChanged(lastIndex, 1)
        updateRefreshLoadMoreState()
        return this
    }

    fun addFrontData(list: MutableList<SmartFragmentTypeExEntity>): SmartViewPager2Adapter {
        if (list.isNullOrEmpty()) {
            return this
        }
        mPreloadDataList.addAll(0, list)
        updateWithIdel(mViewPager2.scrollState)
        return this
    }

    fun addFrontData(bean: SmartFragmentTypeExEntity): SmartViewPager2Adapter {
        if (bean == null) {
            return this
        }
        mPreloadDataList.add(0, bean)
        updateWithIdel(mViewPager2.scrollState)
        return this
    }


    fun getDataList(): MutableList<SmartFragmentTypeExEntity> {
        return mDataList
    }

    private fun initSmartViewPager() {

        if (mViewPager2 == null) {
            throw IllegalArgumentException("the bindView viewPager2 can not be null")
        }

        mViewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                updateWithIdel(state)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                checkIndexAndCallBack(position)
            }
        })

        //在页面onCreate的时候，设置给mViewPager2，简化用户使用
//        mViewPager2.adapter = this
//        mLifecycle?.addObserver(object : LifecycleEventObserver {
//            override fun onStateChanged(source: LifecycleOwner,
//                                        event: Lifecycle.Event) {
//                if (event == Lifecycle.Event.ON_CREATE) {
//                    LogUtils.dTag("生命周期没有触发吗","ON_CREATE")
//                    mViewPager2.adapter = this@SmartViewPager2Adapter
//                    source.lifecycle.removeObserver(this)
//                }else if (event == Lifecycle.Event.ON_RESUME){
//                    LogUtils.dTag("生命周期没有触发吗","ON_RESUME====")
//                }
//            }
//        })


//        mViewPager2.getChildAt(0).setOnTouchListener(object :OnTouchListener{
//            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
//                return false
//            }
//        })


    }


    /**
     * 空闲时才去更新数据
     * */
    private fun updateWithIdel(state: Int) {
        if (state == ViewPager2.SCROLL_STATE_IDLE && mPreloadDataList.isNotEmpty()) {
            var tempSize = mDataList.size
            mDataList.addAll(0, mPreloadDataList)
            if (tempSize == 0) {
                //初始化数据为空时，front只能走notifyDataSetChanged
                notifyDataSetChanged()
            } else {
                mViewPager2.setCurrentItem(mPreloadDataList.size + mViewPager2.currentItem, false)
            }
            mPreloadDataList.clear()
            updateRefreshLoadMoreState()
        }
    }

    /**
     * 兜底策略，如果没有对应type的fragment则会用defaultFragment
     * 如果没有设置defaultFragment的话，则会取fragments里第一个添加元素，如果fragments为空则会报错
     * */
    fun addDefaultFragment(fragment: Class<*>): SmartViewPager2Adapter {
        defaultFragment = fragment
        return this
    }

    fun addFragment(type: Int, fragment: Class<*>): SmartViewPager2Adapter {
        fragments[type] = fragment
        if (defaultFragment == null) {
            defaultFragment = fragments[type]
        }
        return this
    }

    fun cancleOverScrollMode(): SmartViewPager2Adapter {
        ViewPager2Util.cancleViewPagerShadow(mViewPager2)
        return this
    }


    //是否实现画廊
    fun asGallery(leftMargin:Int,rightMargin:Int):SmartViewPager2Adapter{
        var recycleView = ViewPager2Util.getRecycleFromViewPager2(mViewPager2)
        if (mViewPager2.orientation==ViewPager2.ORIENTATION_HORIZONTAL){
            recycleView?.setPadding(leftMargin, 0, rightMargin, 0);
        }else{
            recycleView?.setPadding(0, leftMargin, 0, leftMargin);
        }
        recycleView?.clipToPadding = false;
        return this
    }


    fun setOffscreenPageLimit(limit: Int): SmartViewPager2Adapter {
        mViewPager2.offscreenPageLimit = limit
        return this
    }

    fun setPreLoadLimit(preLoadLimit: Int): SmartViewPager2Adapter {
        this.mPreLoadLimit = preLoadLimit
        return this
    }

    fun setPagerTransformer( smartTransformer: SmartTransformer) :SmartViewPager2Adapter{
        when(smartTransformer){
            SmartTransformer.TRANSFORMER_3D->{mViewPager2.setPageTransformer(if (mViewPager2.orientation==ViewPager2.ORIENTATION_HORIZONTAL)
            {StereoPagerTransformer(mViewPager2.resources.displayMetrics.widthPixels.toFloat())}else{StereoPagerVerticalTransformer(mViewPager2.resources.displayMetrics.heightPixels.toFloat())})}
            SmartTransformer.TRANSFORMER_ALPHA_SCALE-> mViewPager2.setPageTransformer(TransAlphScaleFormer())
        }


        return this
    }


    private var hasRefresh = true
    private var isRefreshing = false
    private fun registRefreshOrNot(currentPosition: Int) {
        //刷新和加载，同一时间段只允许一个进行(直至数据返回，或主动调用finishWithNoData)
        if (!hasRefresh || isRefreshing || isLoadMoring) {
            return
        }

        if (currentPosition <= mPreLoadLimit) {
            isRefreshing = true
            mRefreshListener?.onRefresh(this)
        }
    }

    private fun updateRefreshLoadMoreState() {
        isRefreshing = false
        isLoadMoring = false
    }


    private var hasLoadMore = true
    private var isLoadMoring = false
    private fun registLoadMoreOrNot(currentPosition: Int) {
        if (!hasLoadMore || isLoadMoring || isRefreshing) {
            return
        }

        val realPosition: Int = getDataList().size - 1 - currentPosition
        if (realPosition <= mPreLoadLimit) {
            isLoadMoring = true
            mLoadMoreListener?.onLoadMore(this)
        }
    }


    /**
     * 调用此方法将不再触发refresh监听
     * */
    fun finishRefreshWithNoMoreData() {
        hasRefresh = false
        updateRefreshLoadMoreState()
    }


    /**
     * 调用此方法将不再触发LoadMore监听
     * */
    fun finishLoadMoreWithNoMoreData() {
        hasLoadMore = false
        updateRefreshLoadMoreState()
    }


}