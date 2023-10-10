package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.databinding.ItemRecyclerViewTodosNutricionistasBinding
import com.application.sallus_app.model.NutritionistData

class NutricionistasAdapter : RecyclerView.Adapter<NutricionistasAdapter.NutricionistaViewHolder>(){

    private var items = mutableListOf<NutritionistData>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<NutritionistData>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutricionistaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewTodosNutricionistasBinding.inflate(inflater,parent,false)

        return NutricionistaViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NutricionistaViewHolder, position: Int) {
        val nutricionistas = items[position]
        holder.bind(nutricionistas)

    }

    inner class NutricionistaViewHolder(private val binding : ItemRecyclerViewTodosNutricionistasBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: NutritionistData){
            binding.textviewNamePatientItemTodosNutricionistas.text = item.nome
            binding.textviewTagTelephoneItemTodosNutricionistas.text = item.telefone

        }

    }


}