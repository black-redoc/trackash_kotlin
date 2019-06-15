package com.black.trackash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.black.trackash.R
import com.black.trackash.data.model.Transaction
import com.black.trackash.data.model.TransactionItem
import com.black.trackash.utils.CustomAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_historic.*
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.coroutines.runBlocking
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class HistoricActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: TransactionViewModelFactory by instance()

    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historic)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TransactionViewModel::class.java)

        initializeUI()
    }

    private fun initializeUI() {
        bindUI()

        toHome.setOnClickListener { finish() }
    }


    private fun bindUI() = runBlocking {
        val tr = viewModel.transactions.await()
        tr.observe(this@HistoricActivity, Observer {
            if (it == null) return@Observer
            startRecyclerView(it.reversed().toTransactionItems())
        })
    }

    private fun startRecyclerView(items: List<TransactionItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>(). apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HistoricActivity, RecyclerView.VERTICAL, false)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener{ item, view ->
            (item as? Transaction)?.let {
                Toast.makeText(applicationContext, "Transaction ${it.concept}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun List<Transaction>.toTransactionItems(): List<TransactionItem> {
        return this.map {
            TransactionItem(it)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
    }
}
