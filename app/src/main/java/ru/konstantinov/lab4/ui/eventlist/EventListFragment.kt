package ru.konstantinov.lab4.ui.eventlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import ru.konstantinov.lab4.databinding.FragmentEventsBinding

class EventListFragment : Fragment() {
    private lateinit var binding: FragmentEventsBinding
    private val eventAdapter = EventAdapter()
    private val eventsViewModel: EventsViewModel by activityViewModels()

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

        binding.addEventButton.setOnClickListener {
            eventsViewModel.showAddEventFragment()
        }

        binding.recyclerView.apply {
            adapter = eventAdapter
            layoutManager = LinearLayoutManager(context)
        }

        eventsViewModel.getEvents().observe(viewLifecycleOwner){
            eventAdapter.reload(it)
        }
    }
}