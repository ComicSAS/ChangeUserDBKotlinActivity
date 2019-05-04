package com.example.changeuserdbkotlinactivity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.changeuserdbkotlinactivity.databinding.ActivityOtherBinding
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AppCompatActivity() {

    private lateinit var onOtherSubmitAction: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityOtherBinding = DataBindingUtil.setContentView(this, R.layout.activity_other)
        binding.user = user
        initListeners()
        bindListeners()
    }

    private fun initListeners() {
        onOtherSubmitAction = View.OnClickListener {
            val other = etOtherInput.text.toString()
            val intentSendOther = Intent()
            intentSendOther.putExtra("OTHER", other)
            setResult(OPEN_OTHER, intentSendOther)
            finish()
        }
    }

    private fun bindListeners() {
        btnOtherSubmit.setOnClickListener(onOtherSubmitAction)
    }
}
