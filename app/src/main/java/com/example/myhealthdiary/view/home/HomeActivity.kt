package com.example.myhealthdiary.view.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.myhealthdiary.databinding.ActivityHomeBinding
import com.example.myhealthdiary.utils.pref.PrefManager
import com.example.myhealthdiary.utils.pref.UserPreferenceKey
import com.example.myhealthdiary.view.diagnosis.DiagnosisChatActivity
import com.example.myhealthdiary.view.login.LoginActivity
import com.example.myhealthdiary.viewmodel.LoginViewModel
import com.google.android.material.appbar.AppBarLayout
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class HomeActivity : AppCompatActivity(), OnUserMenuClickListener {
    private lateinit var binding: ActivityHomeBinding

    private val preferenceManager: PrefManager by inject()

    private val loginViewModel: LoginViewModel by viewModel()

    companion object {
        fun start(context: Context) {
            context.startActivity(
                context.intentFor<HomeActivity>(
                ).clearTask().newTask()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loginViewModel.getUserData(preferenceManager.getString(UserPreferenceKey.USERNAME))

        Handler(Looper.getMainLooper()).postDelayed({
            initUI()
        }, 500)

        initActions()
    }

    private fun initUI(){
        binding.tvUsername.text = String.format("Halo, ${loginViewModel.userData?.Nama}")

        binding.appbarLayoutMain.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when{
                abs(verticalOffset) - appBarLayout.totalScrollRange == 0 -> {
                    //Collapsed
                    binding.tvUsernameToolbar.text = String.format("Halo, ${loginViewModel.userData?.Nama}")
                    binding.ivUser.visibility = View.INVISIBLE
                    binding.tvUsername.visibility = View.INVISIBLE
                    binding.tvWelcome.visibility = View.INVISIBLE

                    binding.ivUserToolbar.visibility = View.VISIBLE
                    binding.tvUsernameToolbar.visibility = View.VISIBLE
                    binding.tvWelcomeToolbar.visibility = View.VISIBLE
                }
                verticalOffset <= 0 -> {
                    //Expanded
                    if (binding.ivUser.visibility == View.INVISIBLE){
                        binding.ivUser.visibility = View.VISIBLE
                        binding.tvUsername.visibility = View.VISIBLE
                        binding.tvWelcome.visibility = View.VISIBLE

                        binding.ivUserToolbar.visibility = View.INVISIBLE
                        binding.tvUsernameToolbar.visibility = View.INVISIBLE
                        binding.tvWelcomeToolbar.visibility = View.INVISIBLE
                    }
                }
                else -> {
                    //Idle
                }
            }
        })
    }

    private fun initActions(){
        binding.flUser.setOnClickListener {
            UserMenuFragment.newInstance(this).show(supportFragmentManager, "")
        }

        binding.btnDiagnosisPintar.setOnClickListener {
            startActivity<DiagnosisChatActivity>()
        }

        binding.btnDiseasepedia.setOnClickListener {  }
        binding.btnBukuCatatan.setOnClickListener {  }
        binding.btnRumahSakit.setOnClickListener {  }
        binding.btnMyRecords.setOnClickListener {  }
        binding.btnSambungGadget.setOnClickListener {  }
    }

    override fun logout() {
        preferenceManager.removeKey(UserPreferenceKey.IS_LOGGED_IN)
        preferenceManager.removeKey(UserPreferenceKey.USERNAME)

        LoginActivity.start(this)
    }
}