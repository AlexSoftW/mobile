package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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

        holder.binding.buttonRemoverAlimentoRegisterRoutine.setOnClickListener {
            removeItem(position)
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


    fun removeItem(position: Int) {
        if (position in 0 until foodList.size) {
            foodList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    inner class CreateRoutinerAdapterHolder(val binding: ItemRecyclerViewRegisterRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(food: FoodData) {
            binding.imageviewFoodItemRegisterRoutine.setImageResource(R.drawable.baseline_circle_24)
            binding.textviewNameFoodItemRegisterRoutine.text = food.nome

//            binding.buttonRemoverAlimentoRegisterRoutine.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    removeItem(position)
//                }
//                Log.i("adapterFoodCreate", "bind: cliquei no botão do alimento: $food")
//                Log.i("adapterFoodCreateList", "bind: esta é a lista atual no adapter: $foodList")
//            }

            if (food.diabete) {
                binding.imageviewDiabetesItemRegisterRoutine.visibility = View.VISIBLE
            } else {
                binding.imageviewDiabetesItemRegisterRoutine.visibility = View.GONE
            }

            if (food.colesterol) {
                binding.imageviewColesterolItemRegisterRoutine.visibility = View.VISIBLE
            } else {
                binding.imageviewColesterolItemRegisterRoutine.visibility = View.GONE
            }

            if (food.hipertensao) {
                binding.imageviewHipertensaoItemRegisterRoutine.visibility = View.VISIBLE
            } else {
                binding.imageviewHipertensaoItemRegisterRoutine.visibility = View.GONE
            }

        }

    }
}