package com.black.trackash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.black.trackash.R
import com.black.trackash.utils.doubleExchange
import kotlinx.android.synthetic.main.activity_historic.*
import kotlinx.android.synthetic.main.activity_historic.toHome
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.runBlocking
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ProfileActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: ExtractViewModelFactory by instance()

    private lateinit var viewModel: ExtractViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExtractViewModel::class.java)

        initializeUI()
    }

    private fun initializeUI() {
        toHome.setOnClickListener { finish() }
        bindUI()
    }

    private fun bindUI() = runBlocking {
        val extract = viewModel.last.await()
        extract.observe(this@ProfileActivity, Observer {
            if (it == null) return@Observer
            txtExpenses.setText(it.expenses.doubleExchange())
            txtIncomes.setText(it.incomes.doubleExchange())
            totalCash.setText(it.total.doubleExchange())
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down)
    }
}
