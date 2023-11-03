package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewYoursPatientsBinding
import com.application.sallus_app.model.FoodData
import com.application.sallus_app.model.PacienteDetailsData

class PacienteAdapter : RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>() {

    private var pacientesList = mutableListOf<PacienteDetailsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewYoursPatientsBinding.inflate(inflater, parent, false)

        return PacienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = pacientesList[position]
        holder.bind(paciente)
    }

    override fun getItemCount(): Int = pacientesList.size

    @SuppressLint("NotifyDataSetChanged")
    fun subitList(newItems: List<PacienteDetailsData>) {
        pacientesList.clear()
        pacientesList.addAll(newItems)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitListOnlyFood(paciente: List<PacienteDetailsData>) {
        this.pacientesList.clear()
        this.pacientesList.addAll(paciente)
        notifyDataSetChanged()
    }

    inner class PacienteViewHolder(private val binding: ItemRecyclerViewYoursPatientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(paciente: PacienteDetailsData) {
            binding.textviewNamePatientItemYoursPatients.text = paciente.nome

//            binding.imageviewGenderPatientItemYoursPatients.setImageResource(
//                if (paciente.genero == "Masculino") {
//                    R.drawable.ic_male_gender
//                } else if (paciente.genero == "Feminino") {
//                    R.drawable.ic_female_gender
//                } else {
//                    com.google.android.material.R.drawable.navigation_empty_icon
//                }
//            )

//            binding.textviewTelephonePatientItemYoursPatients.text = paciente.telefone

        }
    }

}