package com.glucode.about_you.engineers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer

class EngineersRecyclerViewAdapter(
    private var engineers: List<Engineer>,
    private val onClick: (Engineer) -> Unit
) : RecyclerView.Adapter<EngineersRecyclerViewAdapter.EngineerViewHolder>() {

    override fun getItemCount() = engineers.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        return EngineerViewHolder(ItemEngineerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position], onClick)
    }

    inner class EngineerViewHolder(private val binding: ItemEngineerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(engineer: Engineer, onClick: (Engineer) -> Unit) {
            binding.name.text = engineer.name
            binding.role.text = engineer.role
            binding.root.setOnClickListener {
                onClick(engineer)
            }
            //TODO - set profile picture
//            statusIcon.setDrawable(item.icon)
            //binding.profileImage.setImageResource(engineer.defaultImageName.toInt())
            try {
                val imageResId = engineer.defaultImageName.toInt()
                binding.profileImage.setImageResource(imageResId)
            } catch (_: NumberFormatException) {
            }
            binding.bugValue.text = engineer.quickStats.bugs.toString()
            binding.yearsValue.text = engineer.quickStats.years.toString()
            binding.coffeeValue.text = engineer.quickStats.coffees.toString()

        }
    }
 fun sortByCoffee(){
     engineers = engineers.sortedBy { it.quickStats.coffees }
     notifyDataSetChanged()
 }
    fun sortByBugs(){
        engineers = engineers.sortedBy { it.quickStats.bugs }
        notifyDataSetChanged()
    }

    fun sortByYears(){
        engineers = engineers.sortedBy { it.quickStats.years }
        notifyDataSetChanged()
    }
    // It is worthy to note that diffUtil would be a great substitute since notifyDataSetChanged is deprecated
}