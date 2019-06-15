package com.black.trackash.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.black.trackash.R
import com.black.trackash.data.model.Transaction
import de.hdodenhof.circleimageview.CircleImageView

class CustomAdapter(
    private val movements: List<Transaction>,
    private val context: Context
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction: Transaction = movements[position]

        val icon = if (transaction.type === "Income")
            R.drawable.ic_arrow_up else R.drawable.ic_arrow_down

        holder.txtConcept.text = transaction.concept
        holder.txtValue.text = transaction.value.exchange()
        holder.txtValue.setTextColor(
            if(transaction.type === "Expense") getColor( context, R.color.expensesColor )
            else getColor(context, R.color.incomesColor))
        holder.iconArrow.setImageResource(icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_movements,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movements.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txtConcept = view.findViewById<View>(R.id.txtConcept) as TextView
        val txtValue = view.findViewById<View>(R.id.txtValue) as TextView
        val iconArrow = view.findViewById<View>(R.id.iconArrow_) as CircleImageView
    }
}