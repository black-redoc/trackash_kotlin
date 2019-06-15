package com.black.trackash.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.black.trackash.R
import com.black.trackash.data.model.Transaction
import kotlinx.android.synthetic.main.activity_add_transaction.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class AddTransactionActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: TransactionViewModelFactory by instance()

    private lateinit var globalTransactions: MutableLiveData<List<Transaction>>
    private lateinit var viewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TransactionViewModel::class.java)

        initializeUI()
    }

    private fun initializeUI() {
        toHome.setOnClickListener { this.finish() }
        performDPD()

        onSubmit()
    }

    private fun updateGlobalTransactions() {}

    // this function enable the date picker dialog
    private fun performDPD() {
        val cal = Calendar.getInstance()
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)

        txtDate.setOnClickListener {
            val dtp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { it, mYear, mMonth, mDay ->
                val txt = "${if (mDay < 10) "0$mDay" else mDay}/${if (mMonth < 10) "0$mMonth" else mMonth}/$mYear"
                txtDate.setText(txt)
            }, year, month, day)

            dtp.show()
        }
    }

    private fun validate(): Boolean {
        return !(txtConcept.text.isEmpty() || txtDate.text.length < 9 || txtValue.text.length < 2)
    }

    private fun onSubmit() = runBlocking(Dispatchers.IO) {
        btnSubmit.setOnClickListener {
            if (!validate())
                Toast.makeText(applicationContext, "There is incomplete fields", Toast.LENGTH_SHORT).show()
            else {
                val now = LocalDate.parse(txtDate.text, DateTimeFormatter.ofPattern("d/MM/yyyy"))
                // Persist and Update View

                viewModel.insert(Transaction(
                    concept = txtConcept.text.toString(),
                    value = "${txtValue.text}".toDouble(),
                    type = spType.selectedItem as String,
                    date = now)
                )

                // Clean View
                txtConcept.setText("")
                txtValue.setText("")
                txtDate.setText("")

                Toast.makeText(applicationContext, "Transaction Successful", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun finish() {
        super.finish()
        // Exit the activity with animation
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}
