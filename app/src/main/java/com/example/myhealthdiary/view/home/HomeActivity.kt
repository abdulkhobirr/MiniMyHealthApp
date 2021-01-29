package com.example.myhealthdiary.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myhealthdiary.databinding.ActivityHomeBinding
import com.example.myhealthdiary.view.diagnosis.DiagnosisChatActivity
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initActions()
    }

    private fun initActions(){
        binding.btnDiagnosisPintar.setOnClickListener {
            startActivity<DiagnosisChatActivity>()
        }

        binding.btnDiseasepedia.setOnClickListener {  }
        binding.btnBukuCatatan.setOnClickListener {  }
        binding.btnRumahSakit.setOnClickListener {  }
        binding.btnMyRecords.setOnClickListener {  }
        binding.btnSambungGadget.setOnClickListener {  }
    }
}