package com.example.myhealthdiary.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhealthdiary.databinding.FragmentBottomGenderPickerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GenderPickerBottomFragment(
    private val listener: GenderOnClickListener
) : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomGenderPickerBinding?= null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(listener: GenderOnClickListener) = GenderPickerBottomFragment(listener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomGenderPickerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActions()
    }

    private fun initActions(){
        binding.genderLaki.setOnClickListener {
            listener.onGenderClick("Laki-laki")
            dialog?.dismiss()
        }
        binding.genderPerempuan.setOnClickListener {
            listener.onGenderClick("Perempuan")
            dialog?.dismiss()
        }
        binding.ivClose.setOnClickListener { dialog?.dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface GenderOnClickListener{
    fun onGenderClick(gender: String)
}