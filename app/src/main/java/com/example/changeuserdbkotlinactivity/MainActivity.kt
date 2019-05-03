package com.example.changeuserdbkotlinactivity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.changeuserdbkotlinactivity.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

const val OPEN_NAME = 1
const val OPEN_SURNAME = 2
const val OPEN_AGE = 3
const val OPEN_OTHER = 4

val user = User("Andrew", "Sukhovolskij", 21, "Developer")

class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            OPEN_NAME -> {
                if (data != null) {
                    val name = data!!.getStringExtra("NAME")
                    tvMainName.setText(name)
                }
            }
            OPEN_SURNAME -> {
                if (data != null) {
                    val surname = data!!.getStringExtra("SURNAME")
                    tvMainSurname.setText(surname)
                }
            }
            OPEN_AGE -> {
                if (data != null) {
                    val age = data!!.getStringExtra("AGE")
                    tvMainAge.setText(age)
                }
            }
            OPEN_OTHER -> {
                if (data != null) {
                    val other = data!!.getStringExtra("OTHER")
                    tvMainOther.setText(other)
                }
            }
        }
    }

    private lateinit var onChangeNameAction: View.OnClickListener
    private lateinit var onChangeSurnameAction: View.OnClickListener
    private lateinit var onChangeAgeAction: View.OnClickListener
    private lateinit var onChangeOtherAction: View.OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setUser(user)
        initListeners()
        bindListeners()
    }

    fun saveUser(v: View) {
        if (isAllValid(tvMainName, tvMainSurname, tvMainOther)) {
            user.name = tvMainName.getText().toString()
            user.surname = tvMainSurname.getText().toString()
            user.age = tvMainAge.getText().toString().toInt()
            user.other = tvMainOther.getText().toString()
            makeToast(
                """onSaveAction:
                |${user.toStringUser()}
            """.trimMargin()
            )
        } else
            makeToast("onSaveAction: Error: Wrong input data")
    }

    private fun initListeners() {
        onChangeNameAction = View.OnClickListener {
            val intentChangeName = Intent(this, NameActivity::class.java)
            startActivityForResult(intentChangeName, OPEN_NAME)
        }
        onChangeSurnameAction = View.OnClickListener {
            val intentChangeSurname = Intent(this, SurnameActivity::class.java)
            startActivityForResult(intentChangeSurname, OPEN_SURNAME)
        }
        onChangeAgeAction = View.OnClickListener {
            val intentChangeAge = Intent(this, AgeActivity::class.java)
            startActivityForResult(intentChangeAge, OPEN_AGE)
        }
        onChangeOtherAction = View.OnClickListener {
            val intentChangeOther = Intent(this, OtherActivity::class.java)
            startActivityForResult(intentChangeOther, OPEN_OTHER)
        }
    }

    private fun bindListeners() {
        tvMainChangeName.setOnClickListener(onChangeNameAction)
        tvMainChangeSurname.setOnClickListener(onChangeSurnameAction)
        tvMainChangeAge.setOnClickListener(onChangeAgeAction)
        tvMainChangeOther.setOnClickListener(onChangeOtherAction)
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, "click " + msg, Toast.LENGTH_LONG).show()
    }

    private fun isValid(str: String): Boolean {
        return str.length > 2
    }

    private fun isAllValid(name: TextView, surname: TextView, other: TextView): Boolean {
        return isValid(name.getText().toString()) && isValid(surname.getText().toString())
                && isValid(other.getText().toString())
    }
}
