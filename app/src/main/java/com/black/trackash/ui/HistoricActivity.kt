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
        updateUI()
        toHome.setOnClickListener { finish() }
    }


    private fun bindUI() = runBlocking {
        val tr = viewModel.monthlyTransactions.await()
        tr.observe(this@HistoricActivity, Observer {
            if (it == null) return@Observer
            startRecyclerView(it.toTransactionItems())
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

    private fun atMonth(month: Int): LocalDate {
        return LocalDate.of(LocalDate.now(ZoneId.of("UTC-05:00")).year,month,1)
    }

    private fun updateUI() {
        m1.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(1)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m2.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(2)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m3.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(3)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m4.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(4)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m5.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(5)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m6.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(6)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m7.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(7)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m8.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(8)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m9.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(9)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m10.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(10)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m11.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(11)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
        m12.setOnClickListener { runBlocking{
            val transactions = viewModel.transactionByMonth(atMonth(12)).value.await()
            transactions.observe(this@HistoricActivity, Observer {
                startRecyclerView(it.toTransactionItems())
            })
        }}
    }
}
