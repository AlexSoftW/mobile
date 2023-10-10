package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
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
//    val alimentosSelecionados = mutableListOf<List<FoodData>>()

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
//            alimentosSelecionados.add(foodList)
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


    inner class FoodViewHolder(private val binding: ItemRecyclerViewFoodsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: FoodData) {

            binding.imageviewItemAlimento.setImageResource(getImageResource(food.nome))

            binding.textviewNomeItemAlimento.text = food.nome

//            if (food.indicadoDiabete) {
//                binding.imageviewIconDiabetesItemAlimento.visibility = View.VISIBLE
//            } else {
//                binding.imageviewIconDiabetesItemAlimento.visibility = View.GONE
//            }
//
//            if (food.indicadoHipertensao) {
//                binding.imageviewIconHipertensaoItemAlimento.visibility = View.VISIBLE
//            } else {
//                binding.imageviewIconHipertensaoItemAlimento.visibility = View.GONE
//            }
//
//            if (food.indicadoColesterol) {
//                binding.imageviewIconColesterolItemAlimento.visibility = View.VISIBLE
//            } else {
//                binding.imageviewIconColesterolItemAlimento.visibility = View.GONE
//            }

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