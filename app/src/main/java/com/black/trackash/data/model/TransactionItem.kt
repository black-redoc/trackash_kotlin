package com.black.trackash.data.model

import android.widget.TextView
import com.black.trackash.R
import com.black.trackash.utils.exchange
import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.list_movements.*
import org.threeten.bp.format.DateTimeFormatter

class TransactionItem(
    val transaction: Transaction
): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            concept_.text = transaction.concept
            txtDate.text = transaction.date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
            (value_ as TextView).text  = transaction.value.exchange()
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