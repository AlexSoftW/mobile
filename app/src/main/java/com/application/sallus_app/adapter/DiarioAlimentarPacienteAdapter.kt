package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerviewDiarioPacienteBinding
import com.application.sallus_app.model.DiarioGetData
import com.application.sallus_app.model.FoodData

class DiarioAlimentarPacienteAdapter :
    RecyclerView.Adapter<DiarioAlimentarPacienteAdapter.DiarioAlimentarPacienteViewHolder>() {

    private val refeicaoList = mutableListOf<DiarioGetData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiarioAlimentarPacienteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewDiarioPacienteBinding.inflate(inflater, parent, false)

        return DiarioAlimentarPacienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiarioAlimentarPacienteViewHolder, position: Int) {
        val refeicoes = refeicaoList[position]
        holder.bind(refeicoes)
    }

    override fun getItemCount(): Int = refeicaoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(refeicoes: List<DiarioGetData>) {
        this.refeicaoList.clear()
        this.refeicaoList.addAll(refeicoes)
        notifyDataSetChanged()
    }

    inner class DiarioAlimentarPacienteViewHolder(
        private val binding: ItemRecyclerviewDiarioPacienteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(refeicao: DiarioGetData) {

            binding.imageviewRefeicaoItemRecyclerviewDiarioPaciente.setImageResource(
                when (refeicao.periodo) {
                    "Manhã" -> R.mipmap.img_refeicao_cafe_manha
                    "Tarde" -> R.mipmap.img_refeicao_almoco
                    "Noite" -> R.mipmap.img_refeicao_jantar
                    else -> R.mipmap.img_refeicao_default
                }
            )

            binding.textviewNomeRefeicaoItemRecyclerviewDiarioPaciente.text =
                when (refeicao.periodo) {
                    "Manhã" -> "Café da manhã"
                    "Tarde" -> "Almoço"
                    "Noite" -> "Jantar"
                    else -> "Refeição"
                }

            binding.textviewInformacoesRefeicaoItemRecyclerviewDiarioPaciente.text =
                refeicao.alimentos

        }

    }

}