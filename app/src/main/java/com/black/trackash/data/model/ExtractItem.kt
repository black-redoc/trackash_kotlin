package com.black.trackash.data.model

import com.black.trackash.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class ExtractItem (
    val extract: Extract
): Item(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {

        }
    }

    override fun getLayout(): Int = R.layout.activity_main
}