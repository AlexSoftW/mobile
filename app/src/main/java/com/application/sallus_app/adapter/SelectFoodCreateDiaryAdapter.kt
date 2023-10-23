package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewFoodsBinding
import com.application.sallus_app.model.FoodData
import java.util.Locale

class SelectFoodCreateDiaryAdapter() :
    RecyclerView.Adapter<SelectFoodCreateDiaryAdapter.SelectFoodCreateDiaryAdapterHolder>() {

    private val foodList = mutableListOf<FoodData>()
    val selectedFoods = mutableListOf<FoodData>()
    val buttonStateFoodList = MutableLiveData<List<FoodData>>()

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

        holder.itemView.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                if (food.isSelected) R.color.green_default else R.color.white_100
            )
        )

        holder.itemView.setOnClickListener {
            food.isSelected = !food.isSelected

            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    if (food.isSelected) R.color.green_default else R.color.white_100
                )
            )

            if (food.isSelected) {
                selectedFoods.add(food)
            } else {
                selectedFoods.remove(food)
            }

            buttonStateFoodList.value = selectedFoods
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(alimentos: List<FoodData>) {
        this.foodList.clear()
        this.foodList.addAll(alimentos)

        for (food in foodList) {
            food.isSelected = selectedFoods.contains(food)
        }

        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitListOnlyFood(alimento: FoodData) {
        this.foodList.clear()
        this.foodList.add(alimento)
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