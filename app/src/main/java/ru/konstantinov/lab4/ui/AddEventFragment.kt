package ru.konstantinov.lab4.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.konstantinov.lab4.databinding.FragmentAddEventBinding
import ru.konstantinov.lab4.databinding.FragmentEventsBinding
import ru.konstantinov.lab4.model.EventReminder
import ru.konstantinov.lab4.ui.common.App
import ru.konstantinov.lab4.ui.event.EventViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class AddEventFragment : Fragment() {
    private lateinit var binding: FragmentAddEventBinding
    private val eventViewModel: EventViewModel by activityViewModels()
    private val nowDate = Date()
    private lateinit var dateOfEvent : Date

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var eventYear = nowDate.year
        var eventMonth = nowDate.month
        var eventDay = nowDate.date
        var eventHour = nowDate.hours
        var eventMinute = nowDate.minutes
        binding.timeButton.setOnClickListener {
            TimePickerDialog(activity,TimePickerDialog.OnTimeSetListener{ view, hourOfDay, minute ->
                binding.inputTimeEvents.setText(SimpleDateFormat("HH:mm").format(Date(0,0,0, hourOfDay, minute)))
                eventHour = hourOfDay
                eventMinute = minute
            }, nowDate.hours, nowDate.minutes, true).show()
        }
        binding.dateButton.setOnClickListener {
            DatePickerDialog(requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                binding.inputDateEvents.setText(SimpleDateFormat("dd.MM.yy").format(Date(year, monthOfYear, dayOfMonth)))
                eventYear = year
                eventMonth = monthOfYear
                eventDay = dayOfMonth
            }, nowDate.year + 1900, nowDate.month, nowDate.date).show()
        }
        binding.buttonAddEvent.setOnClickListener {
            eventViewModel.addEvent(EventReminder(binding.inputTitleEvents.text.toString(),
                binding.inputDescriptionEvents.text.toString(),
                Date(eventYear, eventMonth, eventDay, eventHour, eventMinute)))
            eventViewModel.closeAddEventFragment()
        }
        }
}