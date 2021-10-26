package ru.konstantinov.lab4.ui.addevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import ru.konstantinov.lab4.databinding.FragmentAddEventBinding
import java.util.*

class AddEventFragment : Fragment() {
    private lateinit var binding: FragmentAddEventBinding
    private val addEventVM: AddEventVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEventBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = addEventVM
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = arguments?.getInt("ARG_EVENT_ID")
        id?.let{
            addEventVM.loadData(it)
        }
        binding.dateButton.setOnClickListener {
            initDatePicker()
        }
        binding.buttonAddEvent.setOnClickListener {
            addEventVM.saveData()
        }
    }

    private fun initDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setSelection(addEventVM.getDate().time)
            .build()
        datePicker.addOnPositiveButtonClickListener {
            addEventVM.updateDate(Date(datePicker.selection!!))
        }
        datePicker.show(requireFragmentManager(), "tag")
    }
}