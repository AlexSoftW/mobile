package com.application.sallus_app.view.fragmentsNutricionista

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.application.sallus_app.adapter.PacienteAdapter
import com.application.sallus_app.databinding.FragmentYoursPatientsBinding
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentYoursPatients : Fragment() {
    private lateinit var binding: FragmentYoursPatientsBinding
    private lateinit var adapter: PacienteAdapter
    private val viewmodel: PacienteViewModel by viewModel()

    //dados do nutricionista
    private lateinit var dadosNutricionista: NutritionistData
    private var idNutricionista: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYoursPatientsBinding.inflate(inflater, container, false)

        adapter = PacienteAdapter()
        binding.recyclerViewYoursPatients.adapter = adapter

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {
        val intentFromActivity = requireActivity().intent

        val dadosNutricionistaEmString =
            intentFromActivity.getStringExtra("nutricionistaDataValue")
        dadosNutricionista = tratarNutricionistaJsonToData(dadosNutricionistaEmString!!)

        idNutricionista = dadosNutricionista.id
    }

    private fun setupObservers() {
        viewmodel.fetchTodosPacientesComVinculoNutricionista(idNutricionista)

        viewmodel.listaTodosPacientesComVinculoNutricionista.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.searchBarYoursPatients.visibility = View.GONE
                binding.cardviewYourPatients.visibility = View.VISIBLE
                binding.recyclerViewYoursPatients.visibility = View.GONE
            } else {
                binding.searchBarYoursPatients.visibility = View.VISIBLE
                binding.cardviewYourPatients.visibility = View.GONE
                binding.recyclerViewYoursPatients.visibility = View.VISIBLE

                adapter.subitList(it)
            }
        }

    }

    fun tratarNutricionistaJsonToData(nutricionista: String): NutritionistData {
        val gson = Gson()
        val nutricionistaData: NutritionistData =
            gson.fromJson(nutricionista, NutritionistData::class.java)
        return nutricionistaData
    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}