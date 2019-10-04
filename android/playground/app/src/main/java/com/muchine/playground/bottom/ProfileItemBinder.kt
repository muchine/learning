package com.muchine.playground.bottom

import android.content.Context
import com.muchine.githubuser.ui.core.adapter.BindableItemViewHolder
import com.muchine.githubuser.ui.core.adapter.composite.AbstractCompositeItemBinder

class ProfileItemBinder(
    private val context: Context
) : AbstractCompositeItemBinder<ProfileItem>() {

    private val names = arrayOf("Tom", "Steve", "Damian", "Stephen", "Clay", "Kevin", "Danny", "Lebron")

    init {
        var index = 0
        for (i in 0..20) {
            names.forEach { add(ProfileItem(it), index++) }
        }
    }

    override fun newViewHolder(): BindableItemViewHolder<ProfileItem> {
        return BindableItemViewHolder(ProfileItemView(context))
    }

}