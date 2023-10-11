package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewRegisterRoutineBinding
import com.application.sallus_app.model.FoodData

class CreateRoutineAdapter() :
    RecyclerView.Adapter<CreateRoutineAdapter.CreateRoutinerAdapterHolder>() {

    private val foodList = mutableListOf<FoodData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateRoutinerAdapterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewRegisterRoutineBinding.inflate(inflater, parent, false)

        return CreateRoutinerAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: CreateRoutinerAdapterHolder, position: Int) {
        val food = foodList[position]
        holder.bind(food)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(alimentos: List<FoodData>) {
        this.foodList.clear()
        this.foodList.addAll(alimentos)
        notifyDataSetChanged()
    }

    inner class CreateRoutinerAdapterHolder(private val binding: ItemRecyclerViewRegisterRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(food: FoodData) {
            binding.imageviewFoodItemRegisterRoutine.setImageResource(R.drawable.baseline_circle_24)
            binding.textviewNameFoodItemRegisterRoutine.text = food.nome
        }

    }
}