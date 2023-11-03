package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewFoodsBinding
import com.application.sallus_app.model.FoodData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class SelectFoodCreateDiaryAdapter() :
    RecyclerView.Adapter<SelectFoodCreateDiaryAdapter.SelectFoodCreateDiaryAdapterHolder>() {

    private val foodList = mutableListOf<FoodData>()
    private lateinit var bitmapImage: Bitmap

    private val _alimentosSelecionados = mutableSetOf<FoodData>()
    val alimentosSelecionados: MutableSet<FoodData> = _alimentosSelecionados

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

    inner class SelectFoodCreateDiaryAdapterHolder(private val binding: ItemRecyclerViewFoodsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(food: FoodData) {

            //Apenas exibir os dados no card
            exibirInformacoes(food, food.img)

            //Lógica para adicionar os cards após clicar
            selecionarAlimento(food)
        }

        @OptIn(DelicateCoroutinesApi::class)
        private fun exibirInformacoes(food: FoodData, img: String?) {
            GlobalScope.launch(Dispatchers.IO) {
                if (food.img != null && img!!.startsWith("https")) {
                    val imageUrl = URL(food.img)
                    bitmapImage = BitmapFactory.decodeStream(imageUrl.openStream())
                } else {
                    val context = binding.root.context
                    bitmapImage =
                        BitmapFactory.decodeResource(context.resources, R.mipmap.food_default)
                }

                launch(Dispatchers.Main) {
                    binding.imageviewItemAlimento.setImageBitmap(bitmapImage)
                }
            }

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

        @SuppressLint("NotifyDataSetChanged")
        private fun selecionarAlimento(food: FoodData) {

            val isSelected = _alimentosSelecionados.contains(food)
            val colorGreen = ContextCompat.getColor(binding.root.context, R.color.green_default)
            val colorWhite = ContextCompat.getColor(binding.root.context, R.color.white_100)

            val backgroundColor = if (isSelected) {
                colorGreen
            } else {
                colorWhite
            }
            binding.constraintlayoutCardFoods.setBackgroundColor(backgroundColor)

            //Lógica para adicionar na lista os alimentos selecionados
            itemView.setOnClickListener {
                if (isSelected) {
                    _alimentosSelecionados.remove(food)
                } else {
                    _alimentosSelecionados.add(food)
                }

                val updateBackgroundColor = if (isSelected) {
                    colorWhite
                } else {
                    colorGreen
                }
                binding.constraintlayoutCardFoods.setBackgroundColor(updateBackgroundColor)

                Log.i("tagAlimentosSelected", "lista atual: $_alimentosSelecionados")
                notifyDataSetChanged()
            }
        }

    }

}