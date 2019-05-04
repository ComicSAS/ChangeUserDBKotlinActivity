package com.example.changeuserdbkotlinactivity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.changeuserdbkotlinactivity.databinding.ActivityAgeBinding
import kotlinx.android.synthetic.main.activity_age.*

class AgeActivity : AppCompatActivity() {

    private lateinit var onAgeSubmitAction: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAgeBinding = DataBindingUtil.setContentView(this, R.layout.activity_age)
        binding.user = user
        initListeners()
        bindListeners()
    }

    private fun initListeners() {
        onAgeSubmitAction = View.OnClickListener {
            val age = etAgeInput.text.toString()
            val intentSendAge = Intent()
            intentSendAge.putExtra("AGE", age)
            setResult(OPEN_AGE, intentSendAge)
            finish()
        }
    }

    private fun bindListeners() {
        btnAgeSubmit.setOnClickListener(onAgeSubmitAction)
    }
}
