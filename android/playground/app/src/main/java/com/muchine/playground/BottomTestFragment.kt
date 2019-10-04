package com.muchine.playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.muchine.githubuser.ui.base.BaseFragment
import com.muchine.playground.bottom.ProfileAdapter
import kotlinx.android.synthetic.main.fragment_recycler_view_bottom_test.*

class BottomTestFragment : BaseFragment() {

    private lateinit var adapter: ProfileAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_bottom_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ProfileAdapter(requireContext())

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.setOnScrollChangeListener { _, _, _, _, _ ->
            textMessage.text = "${bottomMessage(0)}, ${bottomMessage(1)}, ${bottomMessage(2)}"
        }
    }

    private fun bottomMessage(position: Int): String {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstItem = layoutManager.findViewByPosition(position) ?: return "invisible"
        return "${firstItem.bottom}"
    }
}