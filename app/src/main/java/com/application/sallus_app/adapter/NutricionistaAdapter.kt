package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.databinding.ItemRecyclerViewTodosNutricionistasBinding
import com.application.sallus_app.model.NutritionistData

class NutricionistaAdapter() :
    RecyclerView.Adapter<NutricionistaAdapter.NutricionistaViewHolder>() {

    private var nutritioniostList = mutableListOf<NutritionistData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutricionistaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewTodosNutricionistasBinding.inflate(inflater, parent, false)

        return NutricionistaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NutricionistaViewHolder, position: Int) {
        val nutricionistas = nutritioniostList[position]
        holder.bind(nutricionistas)
    }

    override fun getItemCount(): Int = nutritioniostList.size

    @SuppressLint("NotifyDataSetChanged")
    fun subitList(newItems: List<NutritionistData>) {
        nutritioniostList.clear()
        nutritioniostList.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class NutricionistaViewHolder(private val binding: ItemRecyclerViewTodosNutricionistasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(item: NutritionistData) {
            binding.textviewNamePatientItemTodosNutricionistas.text = item.nome
            binding.textviewTagTelephoneItemTodosNutricionistas.text = item.telefone

        }

    }


}