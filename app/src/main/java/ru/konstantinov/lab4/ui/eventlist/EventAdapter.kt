package ru.konstantinov.lab4.ui.eventlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.konstantinov.lab4.BR
import ru.konstantinov.lab4.databinding.RecyclerviewEventItemBinding
import java.text.SimpleDateFormat

internal class EventAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var events = mutableListOf<EventVM>()

    fun reload(newEvents: List<EventVM>){
        events.clear()
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
    fun bind(eventReminder: EventVM){
        binding.apply {
            setVariable(BR.viewModel, eventReminder)
            title.text = eventReminder.eventReminder.title
            description.text = eventReminder.eventReminder.desc
            date.text = SimpleDateFormat("dd.MM.yy").format(eventReminder.eventReminder.dateStart)
        }
    }
}