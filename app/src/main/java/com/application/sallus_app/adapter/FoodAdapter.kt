package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewFoodsBinding
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.view.fragments.FoodDetailsDialogFragment
import java.util.Locale

// nessa Classe vai ficar o extends Adapter.
// aqui a gente vai manipular os itens de uma lista.
// exemplo: em vez da gente criar fruta por fruta para exibir na tela(isso ia demorar uma eternidade),
// essa classe vai fazer isso automaticamente para gente,
// só precisamos criar a estrutura base dela.
class FoodAdapter() :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private val foodList = mutableListOf<FoodData>()
    private var selectedFood: FoodData? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewFoodsBinding.inflate(inflater, parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.bind(food)

        holder.itemView.setOnClickListener {
            selectedFood = food
            val fragmentManager =
                (holder.itemView.context as AppCompatActivity).supportFragmentManager
            val foodModalDialog = FoodDetailsDialogFragment()
            foodModalDialog.setFoodData(food)
            foodModalDialog.show(fragmentManager, "food_dialog")
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

    @SuppressLint("NotifyDataSetChanged")
    fun submitListOnlyFood(alimento: FoodData) {
        this.foodList.clear()
        this.foodList.add(alimento)
        notifyDataSetChanged()
    }

    inner class FoodViewHolder(private val binding: ItemRecyclerViewFoodsBinding) :
        RecyclerView.ViewHolder(binding.root) {
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