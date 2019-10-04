package com.muchine.playground.bottom

import android.content.Context
import android.view.View
import com.muchine.githubuser.ui.core.adapter.BindableItemView
import com.muchine.playground.R
import kotlinx.android.synthetic.main.view_profile_item.view.*

class ProfileItemView(context: Context) : BindableItemView<ProfileItem>(context) {

    init {
        View.inflate(context, R.layout.view_profile_item, this)
    }

    override fun bind(item: ProfileItem) {
        textName.text = item.name
    }

}