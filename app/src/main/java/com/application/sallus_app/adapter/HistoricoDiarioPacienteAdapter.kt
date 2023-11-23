package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerviewHistoricoDiarioPacienteBinding
import com.application.sallus_app.model.DiarioGetData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class HistoricoDiarioPacienteAdapter :
    RecyclerView.Adapter<HistoricoDiarioPacienteAdapter.HistoricoDiarioPacienteViewHolder>() {

    private val refeicaoList = mutableListOf<DiarioGetData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoricoDiarioPacienteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ItemRecyclerviewHistoricoDiarioPacienteBinding.inflate(inflater, parent, false)

        return HistoricoDiarioPacienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoricoDiarioPacienteViewHolder, position: Int) {
        val refeicoes = refeicaoList[position]
        holder.bind(refeicoes)
    }

    override fun getItemCount(): Int = refeicaoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(refeicoes: List<DiarioGetData>) {
        this.refeicaoList.clear()
        this.refeicaoList.addAll(refeicoes.reversed())
        notifyDataSetChanged()
    }

    fun formatarData(date: String): String {
        val formatoEntrada = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
        val formatoSaida = SimpleDateFormat("dd/MM/yyyy 'às' HH'h'mm", Locale.getDefault())

        try {
            val calendar = Calendar.getInstance()
            calendar.time = formatoEntrada.parse(date)

            // Ajustar o fuso horário para o Brasil (-3 horas)
            val timeZone = TimeZone.getTimeZone("America/Sao_Paulo")
            calendar.timeZone = timeZone
            calendar.add(Calendar.HOUR_OF_DAY, -3)

            return formatoSaida.format(calendar.time)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return date
    }


    inner class HistoricoDiarioPacienteViewHolder(
        private val binding: ItemRecyclerviewHistoricoDiarioPacienteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(refeicao: DiarioGetData) {

            binding.imageviewRefeicaoItemRecyclerviewHistoricoDiarioPaciente.setImageResource(
                when (refeicao.periodo) {
                    "Manhã" -> R.mipmap.img_refeicao_cafe_manha
                    "Tarde" -> R.mipmap.img_refeicao_almoco
                    "Noite" -> R.mipmap.img_refeicao_jantar
                    else -> R.mipmap.img_refeicao_default
                }
            )

            binding.textviewNomeRefeicaoItemRecyclerviewHistoricoDiarioPaciente.text =
                when (refeicao.periodo) {
                    "Manhã" -> "Café da manhã"
                    "Tarde" -> "Almoço"
                    "Noite" -> "Jantar"
                    else -> "Refeição"
                }

            binding.textviewQtdCaloriasItemRecyclerviewHistoricoDiarioPaciente.text =
                refeicao.qtdCalorias.toString()

            binding.textviewInformacoesRefeicaoItemRecyclerviewHistoricoDiarioPaciente.text =
                refeicao.alimentos

            binding.textviewTagDescricaoItemRecyclerviewHistoricoDiarioPaciente.text =
                "Observações: " + if (refeicao.descricao.isNullOrEmpty()) {
                    ""
                } else {
                    refeicao.descricao
                }

            binding.textviewHorarioConsumidoItemRecyclerviewHistoricoDiarioPaciente.text =
                formatarData(refeicao.dataConsumida.toString())

        }

    }

}