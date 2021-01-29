package com.example.myhealthdiary.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhealthdiary.databinding.FragmentSetDobBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class DatePickerBottomFragment(
        private val listener: DatePickerListener,
        day: Int,
        month: Int,
        year: Int
) : BottomSheetDialogFragment() {
    private var _binding: FragmentSetDobBinding ?= null
    private val binding get() = _binding!!

    private var selectedDay = day
    private var selectedMonth = month
    private var selectedYear = year

    companion object {
        fun newInstance(listener: DatePickerListener, day: Int, month: Int, year: Int
        ) = DatePickerBottomFragment(listener, day, month, year)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetDobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        initActions()
    }

    private fun initUI(){
        val c: Calendar = Calendar.getInstance()

        binding.datePickerDob.maxDate = c.timeInMillis
        binding.datePickerDob.init(selectedYear, selectedMonth, selectedDay, null)
    }

    private fun initActions(){
        binding.btnSimpanDob.setOnClickListener {
            selectedDay = binding.datePickerDob.dayOfMonth
            selectedMonth = binding.datePickerDob.month
            selectedYear = binding.datePickerDob.year
            listener.setDate(selectedDay.toString(), selectedMonth.toString(), selectedYear.toString())
            dialog?.dismiss()
        }

        binding.ivClose.setOnClickListener { dialog?.dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface DatePickerListener{
    fun setDate(day: String, month: String, year: String)
}