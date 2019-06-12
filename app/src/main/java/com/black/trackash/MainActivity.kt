package com.black.trackash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.black.trackash.data.model.Transaction
import com.black.trackash.ui.AddTransactionActivity
import com.black.trackash.ui.HistoricActivity
import com.black.trackash.ui.ProfileActivity
import com.black.trackash.ui.SettingsActivity
import com.black.trackash.utils.CustomAdapter

import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class MainActivity : AppCompatActivity() {
    var menuPressed = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUI()
    }

    private fun initializeUI() {
        startRecyclerView()
       enableMenu()

        fabHistoric.setOnClickListener { openHistoric() }
        txtHistoric.setOnClickListener { openHistoric() }

        fabProfile.setOnClickListener { openProfile() }
        txtProfile.setOnClickListener { openProfile() }

        fabSettings.setOnClickListener { openSettings() }
        txtSettings.setOnClickListener { openSettings() }

        fabaddTransaction.setOnClickListener { openAddTransaction() }
    }

    private fun startRecyclerView() {
        recyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val movements:List<Transaction> = mutableListOf(
            Transaction("WorkDay",1000000.0, "Income", LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Food",25000.0, "Expense",LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Freelancing",250000.0, "Income",LocalDate.now(ZoneId.of("UTC-05:00"))),
            Transaction("Clothes",80000.0, "Expense",LocalDate.now(ZoneId.of("UTC-05:00")))
        )

        val adapter = CustomAdapter(movements,this)

        recyclerView.adapter = adapter
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
