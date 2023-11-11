package com.application.sallus_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.databinding.ItemRecyclerViewDiarioBinding
import com.application.sallus_app.model.DiarioGetData

class DiarioAlimentarPacienteAdapter() :
    RecyclerView.Adapter<DiarioAlimentarPacienteAdapter.DiarioAlimentarPacienteHolder>() {

    private val diarioList = mutableListOf<DiarioGetData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiarioAlimentarPacienteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewDiarioBinding.inflate(inflater, parent, false)

        return DiarioAlimentarPacienteHolder(binding)
    }

    override fun onBindViewHolder(holder: DiarioAlimentarPacienteHolder, position: Int) {
        val diario = diarioList[position]
        holder.bind(diario)
    }

    override fun getItemCount(): Int = diarioList.size

    inner class DiarioAlimentarPacienteHolder(private val binding: ItemRecyclerViewDiarioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(diario: DiarioGetData) {

        }

    }
}