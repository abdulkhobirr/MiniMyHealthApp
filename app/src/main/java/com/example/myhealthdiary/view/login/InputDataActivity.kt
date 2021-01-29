package com.example.myhealthdiary.view.login

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.myhealthdiary.data.user.entity.User
import com.example.myhealthdiary.databinding.ActivityInputDataBinding
import com.example.myhealthdiary.view.home.HomeActivity
import com.example.myhealthdiary.viewmodel.LoginViewModel
import com.example.myhealthdiary.utils.pref.PrefManager
import com.example.myhealthdiary.utils.pref.UserPreferenceKey
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.Period

class InputDataActivity : AppCompatActivity(), GenderOnClickListener, DatePickerListener {
    private lateinit var binding: ActivityInputDataBinding
    private var initDay = 1
    private var initMonth = 0
    private var initYear = 2000
    private var username = ""
    private var password = ""

    private val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private val preferenceManager: PrefManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        username = intent.getStringExtra("Username").toString()
        password = intent.getStringExtra("Password").toString()

        initActions()
    }

    private fun initActions(){
        binding.edtTTL.setOnClickListener {
            this.supportFragmentManager.let {
                DatePickerBottomFragment.newInstance(this, initDay, initMonth, initYear).show(it, "")
            }
            toast("Clicked")
        }

        binding.edtJenisKelamin.setOnClickListener {
            this.supportFragmentManager.let {
                GenderPickerBottomFragment.newInstance(this).show(it, "")
            }
        }

        binding.btnSimpan.setOnClickListener {
            when{
                binding.edtNama.text?.isEmpty() == true -> {}
                binding.edtTTL.text?.isEmpty() == true -> {}
                binding.edtJenisKelamin.text?.isEmpty() == true -> {}
                else -> {
                    loginViewModel.addUser(User(
                            Username = username,
                            Password = password,
                            Nama = binding.edtNama.text.toString(),
                            Ttl = binding.edtTTL.text.toString(),
                            Gender = binding.edtJenisKelamin.text.toString()
                    ))
                    preferenceManager.apply {
                        saveString(UserPreferenceKey.USERNAME, binding.edtNama.text.toString())
                        saveBoolean(UserPreferenceKey.IS_LOGGED_IN, true)
                    }
                    HomeActivity.start(this)
                }
            }
        }
    }

    override fun onGenderClick(gender: String) {
        binding.edtJenisKelamin.setText(gender)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setDate(day: String, month: String, year: String) {
        initDay = day.toInt()
        initMonth = month.toInt()
        initYear = year.toInt()
        binding.edtTTL.setText(String.format("$initDay - ${initMonth+1} - $initYear"))

        val age = getAge(initYear, initMonth+1, initDay)
        binding.edtTahun.setText(age.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
        ).years
    }
}