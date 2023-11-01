package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewFoodsBinding
import com.application.sallus_app.model.FoodData
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import java.util.Locale

class SelectFoodCreateDiaryAdapter() :
    RecyclerView.Adapter<SelectFoodCreateDiaryAdapter.SelectFoodCreateDiaryAdapterHolder>() {

    private val foodList = mutableListOf<FoodData>()
    private lateinit var bitmapImage: Bitmap

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

        @OptIn(DelicateCoroutinesApi::class)
        @SuppressLint("NotifyDataSetChanged")
        fun bind(food: FoodData) {

            GlobalScope.launch(Dispatchers.IO) {
                if (food.img != null && food.img.startsWith("https")) {
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

    }

}