package com.example.changeuserdbkotlinactivity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.changeuserdbkotlinactivity.databinding.ActivitySurnameBinding
import kotlinx.android.synthetic.main.activity_surname.*

class SurnameActivity : AppCompatActivity() {

    private lateinit var onSurnameSubmitAction: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySurnameBinding = DataBindingUtil.setContentView(this, R.layout.activity_surname)
        binding.user = user
        initListeners()
        bindListeners()
    }

    private fun initListeners() {
        onSurnameSubmitAction = View.OnClickListener {
            val surname = etSurnameInput.text.toString()
            val intentSendSurname = Intent()
            intentSendSurname.putExtra("SURNAME", surname)
            setResult(OPEN_SURNAME, intentSendSurname)
            finish()
        }
    }

    private fun bindListeners() {
        btnSurnameSubmit.setOnClickListener(onSurnameSubmitAction)
    }
}
