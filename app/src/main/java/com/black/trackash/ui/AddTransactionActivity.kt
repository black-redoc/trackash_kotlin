package com.black.trackash.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.black.trackash.R
import kotlinx.android.synthetic.main.activity_add_transaction.*
import java.util.*

class AddTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        initializeUI()
    }

    private fun initializeUI() {
        toHome.setOnClickListener { this.finish() }
        performDPD()
    }

    // this function enable the date picker dialog
    private fun performDPD() {
        val cal = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)

        txtDate.setOnClickListener {
            val dtp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { it, mYear, mMonth, mDay ->
                txtDate.setText("%d/%d/%d".format(mDay,mMonth,mYear))
            },year,month,day)

            dtp.show()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}
