package com.muchine.playground.ui.tab

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.FragmentManager
import com.muchine.githubuser.ui.base.BaseView
import com.muchine.playground.BottomTestFragment
import com.muchine.playground.R
import com.muchine.playground.ui.core.pager.FragmentPage
import com.muchine.playground.ui.core.pager.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_scrollable_tab.*
import kotlinx.android.synthetic.main.fragment_scrollable_tab.view.*

class ScrollableTabView : BaseView {

    private lateinit var adapter: FragmentPagerAdapter

    constructor(context: Context): this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?): super(context, attributeSet)

    init {
        View.inflate(context, R.layout.fragment_scrollable_tab, this)
    }

    fun initialize(fragmentManager: FragmentManager) {
        initPager(fragmentManager)
        pager.layoutParams.height = context.resources.displayMetrics.heightPixels
    }

    private fun initPager(fragmentManager: FragmentManager) {
        adapter = FragmentPagerAdapter(context, createPages(), fragmentManager)
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
    }

    private fun createPages(): List<FragmentPage> {
        return arrayListOf(
            FragmentPage(R.string.tab1, BottomTestFragment()),
            FragmentPage(R.string.tab2, BottomTestFragment())
        )
    }


}