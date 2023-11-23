package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerviewDiarioPacienteBinding
import com.application.sallus_app.model.DiarioGetData
import com.application.sallus_app.viewmodel.DiarioViewModel
import java.text.SimpleDateFormat
import java.util.Date

class DiarioAlimentarPacienteAdapter(
    private val viewModel: DiarioViewModel,
    private val idPaciente: Long
) :
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

        @SuppressLint("SetTextI18n")
        fun bind(refeicao: DiarioGetData) {

            val dataHoraAtual = Date()
            val dataFormatada = SimpleDateFormat("yyyy-MM-dd")

            if (refeicao.consumido) {
                binding.imageviewRefeicaoItemRecyclerviewDiarioPaciente.setImageResource(
                    R.mipmap.refeicao_concluida
                )
                binding.buttonConsumidoItemRecyclerviewDiarioPaciente.visibility = View.GONE
            } else {
                binding.imageviewRefeicaoItemRecyclerviewDiarioPaciente.setImageResource(
                    when (refeicao.periodo) {
                        "Manhã" -> R.mipmap.img_refeicao_cafe_manha
                        "Tarde" -> R.mipmap.img_refeicao_almoco
                        "Noite" -> R.mipmap.img_refeicao_jantar
                        else -> R.mipmap.img_refeicao_default
                    }
                )
                binding.buttonConsumidoItemRecyclerviewDiarioPaciente.visibility = View.VISIBLE
            }

            binding.textviewNomeRefeicaoItemRecyclerviewDiarioPaciente.text =
                when (refeicao.periodo) {
                    "Manhã" -> "Café da manhã"
                    "Tarde" -> "Almoço"
                    "Noite" -> "Jantar"
                    else -> "Refeição"
                }

            binding.textviewQtdCaloriasItemRecyclerviewDiarioPaciente.text =
                refeicao.qtdCalorias.toString()

            binding.textviewInformacoesRefeicaoItemRecyclerviewDiarioPaciente.text =
                refeicao.alimentos

            binding.textviewTagDescricaoItemRecyclerviewDiarioPaciente.text =
                "Observações: " + if (refeicao.descricao.isNullOrEmpty()) {
                    ""
                } else {
                    refeicao.descricao
                }

            binding.buttonConsumidoItemRecyclerviewDiarioPaciente.setOnClickListener {
                viewModel.consumirAlimento(
                    idPaciente,
                    refeicao.id!!,
                    dataFormatada.format(dataHoraAtual)
                )
            }

        }

    }

}