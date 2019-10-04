package com.muchine.playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muchine.githubuser.ui.base.BaseFragment
import com.muchine.playground.ui.core.pager.FragmentPage
import com.muchine.playground.ui.core.pager.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_scrollable_tab.*

class ScrollableTabFragment : BaseFragment() {

    private lateinit var adapter: FragmentPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scrollable_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPager()
        pager.layoutParams.height = requireContext().resources.displayMetrics.heightPixels
    }

    private fun initPager() {
        adapter = FragmentPagerAdapter(requireContext(), createPages(), childFragmentManager)
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