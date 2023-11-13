package com.application.sallus_app.view.fragmentsPaciente

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.application.sallus_app.R
import com.application.sallus_app.adapter.DiarioAlimentarPacienteAdapter
import com.application.sallus_app.databinding.FragmentDiarioAlimentarPacienteBinding
import com.application.sallus_app.model.DiarioGetData
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.viewmodel.DiarioViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class FragmentDiarioAlimentarPaciente : Fragment() {

    private lateinit var binding: FragmentDiarioAlimentarPacienteBinding
    private val viewModelDiario: DiarioViewModel by activityViewModels()
    private lateinit var adapter: DiarioAlimentarPacienteAdapter
    private lateinit var dadosPaciente: PacienteData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiarioAlimentarPacienteBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {
        val intentFromActivity = requireActivity().intent

        val backgroundCinza = ContextCompat.getColor(binding.root.context, R.color.grey_disabled)
        val backgroundVermelho = ContextCompat.getColor(binding.root.context, R.color.red_default)

        val dadosPacienteEmString = intentFromActivity.getStringExtra("pacienteDataValue")
        dadosPaciente = tratarPacienteJsonToData(dadosPacienteEmString!!)

        adapter =
            DiarioAlimentarPacienteAdapter(
                viewModelDiario, dadosPaciente.id!!
            )

        binding.recyclerviewDiarioAlimentarPaciente.adapter = adapter

        viewModelDiario.alimentosConsumidos.observe(viewLifecycleOwner) {
            binding.buttonDesfazerUltimoConsumidoDiarioAlimentarPaciente.setBackgroundColor(
                if (it.isNullOrEmpty()) backgroundCinza else backgroundVermelho
            )

            binding.buttonDesfazerUltimoConsumidoDiarioAlimentarPaciente.isClickable =
                !it.isNullOrEmpty()

            binding.buttonDesfazerUltimoConsumidoDiarioAlimentarPaciente.text =
                if (it.isNullOrEmpty()) "Nenhum alimento foi consumido dentro de 24h" else "Desfazer último alimento"
        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun setupObservers() {
        val dataHoraAtual = Date()
        val dataFormatada = SimpleDateFormat("yyyy-MM-dd")

        viewModelDiario.buscarTodosDiariosDoPaciente(
            dadosPaciente.id!!,
            dataFormatada.format(dataHoraAtual)
        )

        viewModelDiario.listaDiariosDoPaciente.observe(requireActivity()) {
            if (it.isEmpty() || it == null) {
                binding.recyclerviewDiarioAlimentarPaciente.visibility = View.GONE
                binding.cardviewSeuDiarioAlimentar.visibility = View.VISIBLE
            } else {
                binding.recyclerviewDiarioAlimentarPaciente.visibility = View.VISIBLE
                binding.cardviewSeuDiarioAlimentar.visibility = View.GONE
                adapter.submitList(it)
            }
        }

        binding.buttonDesfazerUltimoConsumidoDiarioAlimentarPaciente.setOnClickListener {
            viewModelDiario.desfazerUltimoAlimentoConsumido(
                dadosPaciente.id!!,
                dataFormatada.format(dataHoraAtual)
            )
        }

        viewModelDiario.atualizarListaDiario.observe(viewLifecycleOwner) {
            Log.i("tagAttFrag", "setupObservers: lista atualizada no fragment - $it")
            adapter.submitList(it)
        }

    }

    fun tratarPacienteJsonToData(paciente: String): PacienteData {
        val gson = Gson()
        val pacienteData: PacienteData =
            gson.fromJson(paciente, PacienteData::class.java)
        return pacienteData
    }

}