package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewFoodsBinding
import com.application.sallus_app.model.FoodData
import java.util.Locale

class FoodCreateRoutineAdapter() :
    RecyclerView.Adapter<FoodCreateRoutineAdapter.FoodCreateRoutineAdapterHolder>() {

    private val foodList = mutableListOf<FoodData>()
    val selectedFoods = mutableListOf<FoodData>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): FoodCreateRoutineAdapterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewFoodsBinding.inflate(inflater, parent, false)
        return FoodCreateRoutineAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCreateRoutineAdapterHolder, position: Int) {
        val food = foodList[position]
        holder.bind(food)

        holder.itemView.setOnClickListener {

        }
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


    inner class FoodCreateRoutineAdapterHolder(private val binding: ItemRecyclerViewFoodsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(food: FoodData) {

            binding.imageviewItemAlimento.setImageResource(getImageResource(food.nome))

            binding.textviewNomeItemAlimento.text = food.nome

            if (selectedFoods.contains(food)) {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, R.color.green_default
                    )
                )
            } else {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, android.R.color.white
                    )
                )
            }


            binding.root.setOnClickListener {
                toggleFoodSelection(food)
                notifyDataSetChanged()
            }


        }

        private fun toggleFoodSelection(food: FoodData) {
            if (selectedFoods.contains(food)) {
                selectedFoods.remove(food)
            } else {
                selectedFoods.add(food)
            }
        }

        private fun getImageResource(foodName: String): Int {
            return when (foodName.lowercase(Locale.ROOT)) {
                "picanha" -> FoodImageEnum.PICANHA.imageResource
                "alface" -> FoodImageEnum.ALFACE.imageResource
                "arroz" -> FoodImageEnum.ARROZ.imageResource
                else -> R.drawable.baseline_circle_24
            }
        }

    }

}