package com.black.trackash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.black.trackash.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initializeUI()
    }

    private fun initializeUI() {
        toHome.setOnClickListener { finish() }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left)
    }
}
