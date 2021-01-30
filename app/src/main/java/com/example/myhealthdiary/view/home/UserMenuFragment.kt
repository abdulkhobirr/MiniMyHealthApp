package com.example.myhealthdiary.view.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhealthdiary.databinding.FragmentUserMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserMenuFragment(private val listener: OnUserMenuClickListener) : BottomSheetDialogFragment() {
    private var _binding : FragmentUserMenuBinding ? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(listener: OnUserMenuClickListener) = UserMenuFragment(listener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        _binding = FragmentUserMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActions()
    }

    private fun initActions(){
        binding.btnLogout.setOnClickListener {
            listener.logout()
        }

        binding.ivClose.setOnClickListener {
            dialog?.dismiss()
        }
    }
}

interface OnUserMenuClickListener{
    fun logout()
}