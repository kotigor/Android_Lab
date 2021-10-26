package ru.konstantinov.lab4.ui.eventdesc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.konstantinov.lab4.R
import ru.konstantinov.lab4.databinding.FragmentEventDescBinding
import ru.konstantinov.lab4.ui.addevent.AddEventVM

class EventDescFragment : Fragment() {
    private lateinit var binding: FragmentEventDescBinding
    private val addEventVM: AddEventVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDescBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = addEventVM
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eventDescToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.eventDescMenu_remove -> addEventVM.removeEvent()
                R.id.eventDescMenu_edit -> addEventVM.openAddView()
                else ->{
                }
            }
            true
        }
        binding.eventDescToolbar.setNavigationOnClickListener { addEventVM.closeView() }

        var id = arguments?.getInt("ARG_EVENT_ID")
        id?.let{
            addEventVM.loadData(it)
        }
    }
}