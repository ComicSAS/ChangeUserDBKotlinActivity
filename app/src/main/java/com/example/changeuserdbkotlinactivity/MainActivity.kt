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

    private lateinit var onChangeNameAction: View.OnClickListener
    private lateinit var onChangeSurnameAction: View.OnClickListener
    private lateinit var onChangeAgeAction: View.OnClickListener
    private lateinit var onChangeOtherAction: View.OnClickListener

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            OPEN_NAME -> if (data != null) {
                val name = data.getStringExtra("NAME")
                tvMainName.text = name
            }

            OPEN_SURNAME -> if (data != null) {
                val surname = data.getStringExtra("SURNAME")
                tvMainSurname.text = surname
            }

            OPEN_AGE -> if (data != null) {
                val age = data.getStringExtra("AGE")
                tvMainAge.text = age
            }

            OPEN_OTHER -> if (data != null) {
                val other = data.getStringExtra("OTHER")
                tvMainOther.text = other
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = user
        initListeners()
        bindListeners()
    }

    fun saveUser(v: View) {
        if (isAllValid(tvMainName, tvMainSurname, tvMainOther)) {
            user.name = tvMainName.text.toString()
            user.surname = tvMainSurname.text.toString()
            user.age = tvMainAge.text.toString().toInt()
            user.other = tvMainOther.text.toString()
            makeToast(
                """onSaveAction:
                |${user.toStringUser()}
            """.trimMargin()
            )
        } else
            makeToast("onSaveAction: Error: Wrong input data")
    }

    private fun openName() {
        val intentChangeName = Intent(this, NameActivity::class.java)
        startActivityForResult(intentChangeName, OPEN_NAME)
    }

    private fun openSurname() {
        val intentChangeSurname = Intent(this, SurnameActivity::class.java)
        startActivityForResult(intentChangeSurname, OPEN_SURNAME)
    }

    private fun openAge() {
        val intentChangeAge = Intent(this, AgeActivity::class.java)
        startActivityForResult(intentChangeAge, OPEN_AGE)
    }

    private fun openOther() {
        val intentChangeOther = Intent(this, OtherActivity::class.java)
        startActivityForResult(intentChangeOther, OPEN_OTHER)
    }

    private fun initListeners() {
        onChangeNameAction = View.OnClickListener {
            openName()
        }
        onChangeSurnameAction = View.OnClickListener {
            openSurname()
        }
        onChangeAgeAction = View.OnClickListener {
            openAge()
        }
        onChangeOtherAction = View.OnClickListener {
            openOther()
        }
    }

    private fun bindListeners() {
        tvMainChangeName.setOnClickListener(onChangeNameAction)
        tvMainChangeSurname.setOnClickListener(onChangeSurnameAction)
        tvMainChangeAge.setOnClickListener(onChangeAgeAction)
        tvMainChangeOther.setOnClickListener(onChangeOtherAction)
    }

    private fun makeToast(msg: String) {
        Toast.makeText(this, "click $msg", Toast.LENGTH_LONG).show()
    }

    private fun isValid(str: String): Boolean {
        return str.length > 2
    }

    private fun isAllValid(name: TextView, surname: TextView, other: TextView): Boolean {
        return isValid(name.text.toString()) && isValid(surname.text.toString())
                && isValid(other.text.toString())
    }
}
