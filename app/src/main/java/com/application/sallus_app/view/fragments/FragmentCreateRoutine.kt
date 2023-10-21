package com.application.sallus_app.view.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
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
        adapter = CreateRoutineAdapter(viewmodelFood)
        binding.recyclerViewRegisterRoutine.adapter = adapter

        autoCompleteAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        )

        binding.autoCompletePatientsList.setAdapter(autoCompleteAdapter)

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

        binding.buttonBackRegisterRoutine.setOnClickListener {
            retornarFragment()
        }
    }


    fun setupObservers() {
        val bundle = arguments
        val selectedFoods = bundle?.getString("selectedFoods")

        viewmodelPatient.fetchTodosPacientes()
        viewmodelFood.tratarAlimentosSelecionados(selectedFoods!!)

        viewmodelFood.listaAlimentosCriarRotina.observe(viewLifecycleOwner) { alimento ->
            adapter.submitList(alimento)
            Log.i("alimentList", "alimentos no create routine convertido: $alimento")

            val valorTotalCarboidratos = alimento.sumOf { it.carboidrato }
            val valorTotalProteinas = alimento.sumOf { it.proteina }
            val valorTotalGordurasTotais = alimento.sumOf { it.gorduraTotal }
            val valorTotalCalorias = alimento.sumOf { it.calorias!! }

            binding.textviewValueCarboidratoRegisterRoutine.text =
                valorTotalCarboidratos.toString()

            binding.textviewValueProteinaRegisterRoutine.text =
                valorTotalProteinas.toString()

            binding.textviewValueGorduraTotalRegisterRoutine.text =
                valorTotalGordurasTotais.toString()

            binding.textviewValueCaloriasRegisterRoutine.text =
                valorTotalCalorias.toString()

            if (alimento.isEmpty()) {
                binding.buttonRegisterRoutine.setBackgroundColor(Color.BLUE)
                binding.buttonRegisterRoutine.text = "Adicione pelo menos 1 alimento antes"
                binding.buttonRegisterRoutine.isClickable = false
                binding.recyclerViewRegisterRoutine.visibility = View.INVISIBLE
                binding.textviewListEmpty.visibility = View.VISIBLE
            } else {
                binding.buttonRegisterRoutine.setOnClickListener { btn ->
                    Log.i("logiButtonTest", "setupObservers: LIBERADO TSUTSU, ${alimento}")
                }
            }
        }

        viewmodelPatient.listaTodosPacientes.observe(viewLifecycleOwner) {
            autoCompleteAdapter.clear()

            it.forEach { paciente ->
                autoCompleteAdapter.add(paciente.nome)
            }
        }
    }

    fun retornarFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
    }

}