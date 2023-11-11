package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewRegisterRoutineBinding
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.viewmodel.FoodViewModel
import com.bumptech.glide.Glide

class CreateRoutineAdapter(private val viewModel: FoodViewModel) :
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


    fun removeItem(position: Int) {
        if (foodList.size == 1) {
            foodList.removeAt(foodList.size - 1)
            notifyItemRemoved(foodList.size)
        } else {
            if (position in 0 until foodList.size) {
                foodList.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    inner class CreateRoutinerAdapterHolder(val binding: ItemRecyclerViewRegisterRoutineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(food: FoodData) {

            Glide.with(binding.root.context)
                .load(food.img)
                .placeholder(R.mipmap.img_refeicao_default)
                .error(R.mipmap.img_refeicao_default)
                .into(binding.imageviewFoodItemRegisterRoutine)

            binding.textviewNameFoodItemRegisterRoutine.text = food.nome

            binding.textviewTagTypeFoodItemRegisterRoutine.text = food.tipo

            binding.imageviewDiabetesItemRegisterRoutine.visibility =
                if (food.diabete) View.VISIBLE else View.GONE

            binding.imageviewColesterolItemRegisterRoutine.visibility =
                if (food.colesterol) View.VISIBLE else View.GONE

            binding.imageviewHipertensaoItemRegisterRoutine.visibility =
                if (food.hipertensao) View.VISIBLE else View.GONE

            binding.buttonRemoverAlimentoRegisterRoutine.setOnClickListener {
                val position = adapterPosition
                if (position >= 0 && position < foodList.size) {
                    val alimentoRemovido = foodList[position]
                    removeItem(position)
                    // Chame o método na ViewModel para remover o alimento da lista da rotina
                    viewModel.removerAlimentoDaRotina(alimentoRemovido)
                } else {
                    Log.i("AdapterError", "Invalid position: $position")
                }
                Log.i("adapterFoodCreate", "bind: cliquei no botão do alimento: $food")
                Log.i("adapterFoodCreateList", "bind: esta é a lista atual no adapter: $foodList")
            }

        }

    }
}