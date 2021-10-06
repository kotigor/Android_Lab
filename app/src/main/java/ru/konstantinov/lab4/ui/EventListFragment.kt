package ru.konstantinov.lab4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.konstantinov.lab4.R
import ru.konstantinov.lab4.databinding.FragmentEventsBinding
import ru.konstantinov.lab4.ui.event.EventAdapter
import ru.konstantinov.lab4.ui.event.EventViewModel

class EventListFragment : Fragment() {
    private lateinit var binding: FragmentEventsBinding
    private val eventAdapter = EventAdapter()
    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            adapter = eventAdapter
            layoutManager = LinearLayoutManager(context)
        }

        eventViewModel.getEvents().observe(viewLifecycleOwner){
            eventAdapter.add(it)
        }
    }
}