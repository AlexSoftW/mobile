package com.application.sallus_app.view.fragmentsPaciente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.adapter.DiarioAlimentarPacienteAdapter
import com.application.sallus_app.adapter.HistoricoDiarioPacienteAdapter
import com.application.sallus_app.databinding.FragmentHistoricoPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.viewmodel.DiarioViewModel
import com.google.gson.Gson

class FragmentHistoricoAlimentarPaciente : Fragment() {

    private lateinit var binding: FragmentHistoricoPacienteBinding
    private lateinit var viewModelDiario: DiarioViewModel
    private lateinit var adapter: HistoricoDiarioPacienteAdapter
    private lateinit var dadosPaciente: PacienteData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoricoPacienteBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {
        val intentFromActivity = requireActivity().intent

        viewModelDiario = DiarioViewModel()
        adapter = HistoricoDiarioPacienteAdapter()
        binding.recyclerviewDiarioHistoricoPaciente.adapter = adapter

        val dadosPacienteEmString = intentFromActivity.getStringExtra("pacienteDataValue")
        dadosPaciente = tratarPacienteJsonToData(dadosPacienteEmString!!)
    }

    private fun setupObservers() {
        viewModelDiario.buscarHistoricoAlimentarDoPaciente(dadosPaciente.id!!)

        viewModelDiario.listaHistoricoAlimentarPaciente.observe(viewLifecycleOwner) {
            if (it.isEmpty() || it == null) {
                binding.recyclerviewDiarioHistoricoPaciente.visibility = View.GONE
                binding.cardviewHistoricoPaciente.visibility = View.VISIBLE
            } else {
                binding.recyclerviewDiarioHistoricoPaciente.visibility = View.VISIBLE
                binding.cardviewHistoricoPaciente.visibility = View.GONE
                adapter.submitList(it)
            }
        }
    }

    fun tratarPacienteJsonToData(paciente: String): PacienteData {
        val gson = Gson()
        val pacienteData: PacienteData =
            gson.fromJson(paciente, PacienteData::class.java)
        return pacienteData
    }
}