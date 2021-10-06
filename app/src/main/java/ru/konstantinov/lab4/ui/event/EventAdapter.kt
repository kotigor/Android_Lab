package ru.konstantinov.lab4.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.konstantinov.lab4.R
import ru.konstantinov.lab4.databinding.RecyclerviewEventItemBinding
import ru.konstantinov.lab4.model.EventReminder
import java.text.SimpleDateFormat

internal class EventAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var events = mutableListOf<EventReminder>()

    fun add(newEvents: List<EventReminder>){
        events.addAll(newEvents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(RecyclerviewEventItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size
}

internal class ViewHolder(val binding: RecyclerviewEventItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(eventReminder: EventReminder){
        binding.apply {
            title.text = eventReminder.title
            description.text = eventReminder.desc
            date.text = SimpleDateFormat("MMM dd yyyy HH:mma").format(eventReminder.dateStart)
        }
    }
}