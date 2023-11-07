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
import com.application.sallus_app.view.fragments.ModalFoodDetailsBottomSheet
import com.bumptech.glide.Glide

// nessa Classe vai ficar o extends Adapter.
// aqui a gente vai manipular os itens de uma lista.
// exemplo: em vez da gente criar fruta por fruta para exibir na tela(isso ia demorar uma eternidade),
// essa classe vai fazer isso automaticamente para gente,
// só precisamos criar a estrutura base dela.
class FoodAdapter() :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private val foodList = mutableListOf<FoodData>()
    private lateinit var modalFoodDetailsBottomSheet: ModalFoodDetailsBottomSheet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewFoodsBinding.inflate(inflater, parent, false)

        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.bind(food)

        val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager

        holder.itemView.setOnClickListener {

            modalFoodDetailsBottomSheet = ModalFoodDetailsBottomSheet(
                food.img,
                food.nome,
                food.tipo,
                (food.carboidrato + food.proteina + food.gorduraTotal),
                food.carboidrato,
                food.proteina,
                food.gorduraTotal,
                food.diabete,
                food.hipertensao,
                food.colesterol
            )

            modalFoodDetailsBottomSheet.show(fragmentManager, ModalFoodDetailsBottomSheet.TAG)
        }
    }

    override fun getItemCount(): Int = foodList.size

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

            Glide.with(binding.root.context)
                .load(food.img)
                .placeholder(R.mipmap.food_default)
                .error(R.mipmap.food_default)
                .into(binding.imageviewItemAlimento)

            binding.textviewNomeItemAlimento.text = food.nome

            binding.imageviewIconDiabetesItemAlimento.visibility =
                if (food.diabete) View.VISIBLE else View.GONE

            binding.imageviewIconColesterolItemAlimento.visibility =
                if (food.colesterol) View.VISIBLE else View.GONE

            binding.imageviewIconHipertensaoItemAlimento.visibility =
                if (food.hipertensao) View.VISIBLE else View.GONE

            binding.textviewAlimentoNaoIndicado.visibility =
                if (!food.diabete && !food.hipertensao && !food.colesterol) View.VISIBLE else View.GONE

        }
    }

}