package com.black.trackash.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.black.trackash.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.black.trackash.data.model.Transaction
import com.black.trackash.data.model.TransactionItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware{

    override val kodein by kodein()
    private val viewModelFactory: TransactionViewModelFactory by instance()

    private lateinit var viewModel: TransactionViewModel
    private var menuPressed = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TransactionViewModel::class.java)

        initializeUI()
    }

    private fun initializeUI() {
        bindUI()
        enableMenu()

        fabHistoric.setOnClickListener { openHistoric() }
        txtHistoric.setOnClickListener { openHistoric() }

        fabProfile.setOnClickListener { openProfile() }
        txtProfile.setOnClickListener { openProfile() }

        fabSettings.setOnClickListener { openSettings() }
        txtSettings.setOnClickListener { openSettings() }

        fabaddTransaction.setOnClickListener { openAddTransaction() }
    }

    private fun bindUI() = runBlocking {
        val tr = viewModel.lastTransactions.await()
        tr.observe(this@MainActivity, Observer {
            if (it == null) return@Observer
            println(it.size)
            startRecyclerView(it.toTransactionItems())
        })
    }

        private fun startRecyclerView(items: List<TransactionItem>) {
            val groupAdapter = GroupAdapter<ViewHolder>(). apply {
                addAll(items)
            }

            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
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

        private fun enableMenu() {
            menuBurger.setOnClickListener {
                val histDimen = resources.getDimension(R.dimen.fab_history)
                val settDimen = resources.getDimension(R.dimen.fab_settings)
                val profDimen = resources.getDimension(R.dimen.fab_profile)

                fabHistoric.animate().translationY( if(menuPressed) histDimen else 0f)
                txtHistoric.animate().translationY( if(menuPressed) histDimen else 0f)
                txtHistoric.visibility = if(menuPressed) View.VISIBLE else View.GONE

                fabProfile.animate().translationY( if(menuPressed) profDimen else 0f)
                txtProfile.animate().translationY( if(menuPressed) profDimen+1 else  0f)
                txtProfile.visibility = if (menuPressed) View.VISIBLE else View.GONE

                fabSettings.animate().translationY( if(menuPressed) settDimen else 0f)
                txtSettings.animate().translationY( if(menuPressed) settDimen+1 else 0f)
                txtSettings.visibility = if (menuPressed) View.VISIBLE else View.GONE

                if(menuPressed){
                    menuBurger.setImageResource(R.drawable.ic_close)

                    menuBurger.animate()
                        .rotation(180F)
                        .withLayer()
                        .setDuration(300L)
                        .start()
                }
                else menuBurger.setImageResource(R.drawable.ic_menu)

                menuPressed = !menuPressed
            }
        }

        private fun openHistoric() {
            intent = Intent(this, HistoricActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
            menuBurger.callOnClick()
        }

        private fun openProfile() {
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down)
            menuBurger.callOnClick()
        }

        private fun openSettings() {
            intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            menuBurger.callOnClick()
        }

        private fun openAddTransaction() {
            intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
            if (!menuPressed) menuBurger.callOnClick()
        }
    }

