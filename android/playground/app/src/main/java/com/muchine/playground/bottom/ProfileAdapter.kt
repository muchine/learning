package com.muchine.playground.bottom

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.muchine.githubuser.ui.core.adapter.composite.CompositeItemAdapter

class ProfileAdapter(context: Context) : CompositeItemAdapter() {

    val binder = ProfileItemBinder(context)

    init {
        addBinder(binder)
    }

}
