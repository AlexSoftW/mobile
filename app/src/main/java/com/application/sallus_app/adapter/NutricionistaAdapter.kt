package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
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
        fun bind(nutricionista: NutritionistData) {
            binding.textviewNamePatientItemTodosNutricionistas.text = nutricionista.nome
            binding.textviewTelephonePatientItemTodosNutricionistas.text = nutricionista.telefone

            binding.imageviewGenderPatientItemTodosNutricionistas.setImageResource(
                if (nutricionista.genero == "Masculino") {
                    R.drawable.ic_male_gender
                } else if (nutricionista.genero == "Feminino") {
                    R.drawable.ic_female_gender
                } else {
                    com.google.android.material.R.drawable.navigation_empty_icon
                }
            )

            binding.imagebuttonWhatsappItemTodosNutricionistas.setOnClickListener {
                val link = "https://api.whatsapp.com/send?phone=55${
                    nutricionista.telefone.trim()
                }&text=Olá ${nutricionista.nome}, tudo bem? " +
                        "vim do aplicativo Salus Well, " +
                        "e gostaria de ter uma primeira consulta com você."

                val context = binding.root.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

                context.startActivity(intent)
            }

        }

    }


}