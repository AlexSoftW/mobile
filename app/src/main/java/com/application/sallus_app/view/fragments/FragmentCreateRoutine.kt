package com.application.sallus_app.view.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.adapter.CreateRoutineAdapter
import com.application.sallus_app.databinding.FragmentRegisterRoutineBinding
import com.application.sallus_app.viewmodel.FoodViewModel
import com.application.sallus_app.viewmodel.PacienteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class FragmentCreateRoutine : Fragment() {

    private lateinit var binding: FragmentRegisterRoutineBinding
    private lateinit var adapter: CreateRoutineAdapter
    private val viewmodelFood: FoodViewModel by viewModel()
    private val viewmodelPatient: PacienteViewModel by viewModel()

    //views
    private lateinit var autoCompleteAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterRoutineBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setupView() {
        adapter = CreateRoutineAdapter()
        binding.recyclerViewRegisterRoutine.adapter = adapter

        autoCompleteAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        )

        binding.editTextDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = String.format(
                        "%02d/%02d/%04d",
                        selectedDay,
                        selectedMonth + 1,
                        selectedYear
                    )
                    binding.editTextDatePicker.setText(selectedDate)
                },
                year,
                month,
                day
            )

            datePickerDialog.datePicker.minDate = System.currentTimeMillis()

            datePickerDialog.show()
        }
    }


    fun setupObservers() {
        val bundle = arguments
        val selectedFoods = bundle?.getString("selectedFoods")
        viewmodelFood.tratarAlimentosSelecionados(selectedFoods!!)



        viewmodelFood.listaAlimentosCriarRotina.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.i("alimentList", "alimentos no create routine convertido: $it")

            val valorTotalCarboidratos = it.sumOf { it.carboidrato }
            val valorTotalProteinas = it.sumOf { it.proteina }
            val valorTotalGordurasTotais = it.sumOf { it.gorduraTotal }
            val valorTotalCalorias = it.sumOf { it.calorias!! }

            binding.textviewValueCarboidratoRegisterRoutine.text =
                valorTotalCarboidratos.toString()

            binding.textviewValueProteinaRegisterRoutine.text =
                valorTotalProteinas.toString()

            binding.textviewValueGorduraTotalRegisterRoutine.text =
                valorTotalGordurasTotais.toString()

            binding.textviewValueCaloriasRegisterRoutine.text =
                valorTotalCalorias.toString()
        }

        viewmodelPatient.fetchTodosPacientes()

        binding.autoCompletePatientsList.setAdapter(autoCompleteAdapter)

        viewmodelPatient.listaTodosPacientes.observe(viewLifecycleOwner) {
            autoCompleteAdapter.clear()

            it.forEach { paciente ->
                autoCompleteAdapter.add(paciente.nome)
            }
        }
    }


}