package com.black.trackash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.black.trackash.R
import com.black.trackash.data.model.Transaction
import com.black.trackash.utils.CustomAdapter
import kotlinx.android.synthetic.main.activity_historic.*
import kotlinx.android.synthetic.main.activity_main.recyclerView
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class HistoricActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)

        initializeUI()
    }

    private fun initializeUI() {
        startRecyclerView()

        toHome.setOnClickListener { finish() }
    }

    private fun startRecyclerView() {
        recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val transactions:List<Transaction> = mutableListOf(
            Transaction("WorkDay",1000000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Food",25000.0, "Expense", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Freelancing",250000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Clothes",80000.0, "Expense", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("WorkDay",1000000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Food",25000.0, "Expense", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Freelancing",250000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Clothes",80000.0, "Expense", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("WorkDay",1000000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Food",25000.0, "Expense", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Freelancing",250000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Clothes",80000.0, "Expense", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("WorkDay",1000000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Food",25000.0, "Expense", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Freelancing",250000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Clothes",80000.0, "Expense", LocalDate.now(ZoneId.of("UTC-05:00")))
        )

        val adapter = CustomAdapter(transactions,this)

        recyclerView.adapter = adapter
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
    }
}
