package com.black.trackash.data.model

import android.widget.TextView
import com.black.trackash.R
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.list_movements.*

class TransactionItem(
    val transaction: Transaction
): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            concept_.text = transaction.concept
            (value_ as TextView).text  = "%d".format(transaction.value.toInt())
            updateArrow()
        }
    }

    override fun getLayout(): Int = R.layout.list_movements

    private fun ViewHolder.updateArrow() {
        Glide.with(this.containerView)
            .load( if (transaction.type == "Income")
            R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
            .into(iconArrow_)
    }
}