package com.openclassrooms.arista.ui.sleep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.arista.R
import com.openclassrooms.arista.domain.model.Sleep
import java.time.format.DateTimeFormatter

/**
 * RecyclerView adapter for displaying a list of [Sleep] records.
 *
 * @property sleeps The initial list of sleep records to display.
 */
class SleepAdapter(private var sleeps: List<Sleep>) :
    RecyclerView.Adapter<SleepAdapter.SleepViewHolder>() {

    /**
     * Creates a new [SleepViewHolder] by inflating the item layout.
     *
     * @param parent The parent [ViewGroup].
     * @param viewType The view type of the new View.
     * @return A new [SleepViewHolder] instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sleep, parent, false)
        return SleepViewHolder(itemView)
    }

    /**
     * Binds data to the [SleepViewHolder] for the item at the given position.
     *
     * @param holder The [SleepViewHolder] to bind data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        val sleep = sleeps[position]
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        holder.tvStartTime.text = "Start Time: ${sleep.startTime.format(formatter)}"
        holder.tvDuration.text = "Duration: ${sleep.duration} minutes"
        holder.tvQuality.text = "Quality: ${sleep.quality}"
    }

    /**
     * Returns the total number of sleep records in the list.
     *
     * @return The size of the [sleeps] list.
     */
    override fun getItemCount() = sleeps.size

    /**
     * ViewHolder class for a [Sleep] item view.
     *
     * @property itemView The item view.
     */
    inner class SleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvStartTime: TextView
        var tvDuration: TextView
        var tvQuality: TextView

        init {
            tvStartTime = itemView.findViewById(R.id.tv_start_time)
            tvDuration = itemView.findViewById(R.id.tv_duration)
            tvQuality = itemView.findViewById(R.id.tv_quality)
        }
    }

    /**
     * Updates the data of the adapter with a new list of sleep records.
     * Notifies the RecyclerView to refresh the views.
     *
     * @param newSleeps The new list of sleep records.
     */
    fun updateData(newSleeps: List<Sleep>) {
        this.sleeps = newSleeps
        notifyDataSetChanged()
    }
}
