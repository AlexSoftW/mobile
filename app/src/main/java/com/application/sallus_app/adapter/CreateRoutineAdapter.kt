package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewRegisterRoutineBinding
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.viewmodel.FoodViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class CreateRoutineAdapter(private val viewModel: FoodViewModel) :
    RecyclerView.Adapter<CreateRoutineAdapter.CreateRoutinerAdapterHolder>() {

    private val foodList = mutableListOf<FoodData>()
    private lateinit var bitmapImage: Bitmap

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

        @OptIn(DelicateCoroutinesApi::class)
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
                    binding.imageviewFoodItemRegisterRoutine.setImageBitmap(bitmapImage)
                }
            }

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