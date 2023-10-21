package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewFoodsBinding
import com.application.sallus_app.model.FoodData
import java.util.Locale

class SelectFoodCreateDiaryAdapter() :
    RecyclerView.Adapter<SelectFoodCreateDiaryAdapter.SelectFoodCreateDiaryAdapterHolder>() {

    private val foodList = mutableListOf<FoodData>()
    val selectedFoods = mutableListOf<FoodData>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SelectFoodCreateDiaryAdapterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewFoodsBinding.inflate(inflater, parent, false)

        return SelectFoodCreateDiaryAdapterHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: SelectFoodCreateDiaryAdapterHolder, position: Int) {
        val food = foodList[position]
        holder.bind(food)

        holder.itemView.setOnClickListener {

            holder.foodSelected = !holder.foodSelected

            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    if (holder.foodSelected) R.color.green_default else R.color.white_100
                )
            )

            if (holder.foodSelected) {
                selectedFoods.add(food)
                Log.i("tagapert", "onBindViewHolder: foiClick $selectedFoods")
            } else {
                selectedFoods.remove(food)
                Log.i("tagapert", "onBindViewHolder: foiDesClick $selectedFoods")
            }

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


    inner class SelectFoodCreateDiaryAdapterHolder(private val binding: ItemRecyclerViewFoodsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var foodSelected = false

        @SuppressLint("NotifyDataSetChanged")
        fun bind(food: FoodData) {
            binding.imageviewItemAlimento.setImageResource(getImageResource(food.nome))
            binding.textviewNomeItemAlimento.text = food.nome

            if (food.diabete) {
                binding.imageviewIconDiabetesItemAlimento.visibility = View.VISIBLE
            } else {
                binding.imageviewIconDiabetesItemAlimento.visibility = View.GONE
            }

            if (food.colesterol) {
                binding.imageviewIconColesterolItemAlimento.visibility = View.VISIBLE
            } else {
                binding.imageviewIconColesterolItemAlimento.visibility = View.GONE
            }

            if (food.hipertensao) {
                binding.imageviewIconHipertensaoItemAlimento.visibility = View.VISIBLE
            } else {
                binding.imageviewIconHipertensaoItemAlimento.visibility = View.GONE
            }

            if (!food.diabete && !food.hipertensao && !food.colesterol) {
                binding.textviewAlimentoNaoIndicado.visibility = View.VISIBLE
            } else {
                binding.textviewAlimentoNaoIndicado.visibility = View.GONE
            }
        }

        private fun getImageResource(foodName: String): Int {
            return when (foodName.lowercase(Locale.ROOT)) {
                "picanha" -> R.mipmap.food_default
                else -> R.mipmap.food_default
            }
        }
    }

}