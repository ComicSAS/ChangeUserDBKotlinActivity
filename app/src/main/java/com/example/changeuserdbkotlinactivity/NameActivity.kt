package com.example.changeuserdbkotlinactivity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.changeuserdbkotlinactivity.databinding.ActivityNameBinding
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    private lateinit var onNameSubmitAction: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityNameBinding = DataBindingUtil.setContentView(this, R.layout.activity_name)
        binding.setUser(user)
        initListeners()
        bindListeners()
    }

    private fun initListeners() {
        onNameSubmitAction = View.OnClickListener {
            val name = etNameInput.getText().toString()
            val intentSendName = Intent()
            intentSendName.putExtra("NAME", name)
            setResult(OPEN_NAME, intentSendName)
            finish()
        }
    }

    private fun bindListeners() {
        btnNameSubmit.setOnClickListener(onNameSubmitAction)
    }
}
