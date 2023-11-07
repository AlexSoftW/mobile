package com.application.sallus_app.view.fragmentsNutricionista

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.adapter.CreateRoutineAdapter
import com.application.sallus_app.databinding.FragmentRegisterRoutineBinding
import com.application.sallus_app.model.DiarioPostData
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.view.fragments.ModalLoadingBottomSheet
import com.application.sallus_app.viewmodel.DiarioViewModel
import com.application.sallus_app.viewmodel.FoodViewModel
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FragmentCreateRoutine : Fragment() {

    private lateinit var binding: FragmentRegisterRoutineBinding
    private lateinit var adapter: CreateRoutineAdapter
    private val viewmodelFood: FoodViewModel by viewModel()
    private val viewmodelPatient: PacienteViewModel by viewModel()
    private val viewmodelDiary: DiarioViewModel by viewModel()

    //dados do nutricionista
    private lateinit var dadosNutricionista: NutritionistData

    //views
    private lateinit var autoCompleteAdapter: ArrayAdapter<String>
    private val pacienteIdMap = HashMap<String, String>()

    //bottomsheet
    private lateinit var modalLoadingBottomSheet: ModalLoadingBottomSheet

    //informações dos alimentos para criação da rotina alimentar
    lateinit var descricao: String
    var qtdCalorias: Double = 0.0
    lateinit var periodo: String
    var alimentos: String = ""
    var idNutricionista: Long = 0
    var idCliente: Long = 0
    lateinit var dataConsumir: String

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
        val intentFromActivity = requireActivity().intent

        modalLoadingBottomSheet = ModalLoadingBottomSheet("Criando rotina alimentar, aguarde...")

        val dadosNutricionistaEmString = intentFromActivity
            .getStringExtra("nutricionistaDataValue")
        dadosNutricionista = tratarNutricionistaJsonToData(dadosNutricionistaEmString!!)

        idNutricionista = dadosNutricionista.id

        autoCompleteAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line
        )

        adapter = CreateRoutineAdapter(viewmodelFood)

        binding.recyclerViewRegisterRoutine.adapter = adapter

        binding.autoCompletePatientsList.setAdapter(autoCompleteAdapter)

        binding.autoCompletePatientsList.setOnItemClickListener { _, _, position, _ ->
            val nomePacienteSelecionado = autoCompleteAdapter.getItem(position)
            val idPacienteSelecionado = pacienteIdMap[nomePacienteSelecionado]

            if (nomePacienteSelecionado != null && idPacienteSelecionado != null) {
                idCliente = idPacienteSelecionado.toLong()
            }
        }

        binding.editTextDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val currentDate = Calendar.getInstance()

            DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    if (calendar >= currentDate) {
                        val selectedDate = SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.getDefault()
                        ).format(calendar.time)

                        val formatoAmericano = SimpleDateFormat(
                            "yyyy-MM-dd",
                            Locale.getDefault()
                        ).format(calendar.time)

                        dataConsumir = formatoAmericano
                        binding.editTextDatePicker.setText(selectedDate)
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.minDate = currentDate.timeInMillis
                show()
            }
        }

        binding.buttonRegisterRoutine.setOnClickListener {

            periodo = binding.autoComleteTextViewTurno.text.toString()
            descricao = binding.textfieldObservacoesRoutine.text.toString()

            val novoDiario = DiarioPostData(
                descricao,
                qtdCalorias,
                periodo,
                alimentos,
                idNutricionista,
                idCliente,
                dataConsumir
            )

            viewmodelDiary.cadastrarNovoDiario(novoDiario)

//            viewmodelDiary.responseCriarDiarioAlimentarBottomSheet.value = false

            modalLoadingBottomSheet.show(childFragmentManager, ModalLoadingBottomSheet.TAG)
        }

        binding.buttonBackRegisterRoutine.setOnClickListener {
            retornarFragment()
        }

    }

    fun setupObservers() {
        val bundle = arguments
        val selectedFoods = bundle?.getString("selectedFoods")

        val redColorButton = ContextCompat.getColor(binding.root.context, R.color.red_default)
        val greyColorButton = ContextCompat.getColor(binding.root.context, R.color.grey_disabled)

        viewmodelPatient.fetchTodosPacientesComVinculoNutricionista(dadosNutricionista.id)

        viewmodelFood.converterAlimentosSelecionadoParaArrayList(selectedFoods!!)

        viewmodelPatient.listaTodosPacientesComVinculoNutricionista.observe(viewLifecycleOwner) {
            autoCompleteAdapter.clear()
            pacienteIdMap.clear()

            if (it.isEmpty()) {
                binding.buttonRegisterRoutine.isClickable = false
                binding.spinnerTypePatientsRegisterRoutine.hint = "Você não possui nenhum paciente"
            } else {
                binding.buttonRegisterRoutine.isClickable = true
                it.forEach { paciente ->
                    autoCompleteAdapter.add(paciente.nome)
                    pacienteIdMap[paciente.nome] = paciente.id.toString()
                }
            }
        }

        viewmodelFood.listaAlimentosSelecionadosParaCriarRotina.observe(viewLifecycleOwner) { alimento ->
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

            qtdCalorias = valorTotalCalorias
            alimento.forEach { food ->
                alimentos += "${food.nome}, "
            }

            if (alimento.isEmpty()) {
                binding.textviewListEmpty.visibility = View.VISIBLE
                binding.recyclerViewRegisterRoutine.visibility = View.GONE
                binding.buttonRegisterRoutine.isClickable = false
                binding.buttonRegisterRoutine.setBackgroundColor(greyColorButton)
                binding.buttonRegisterRoutine.text =
                    getString(R.string.text_validacao_botao_finalizar_rotina)
            } else {
                binding.textviewListEmpty.visibility = View.GONE
                binding.recyclerViewRegisterRoutine.visibility = View.VISIBLE
                binding.buttonRegisterRoutine.isClickable = true
                binding.buttonRegisterRoutine.setBackgroundColor(redColorButton)
                binding.buttonRegisterRoutine.text =
                    getString(R.string.text_create_routine)
            }
        }

        viewmodelDiary.responseCriarDiarioAlimentarBottomSheet.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(5000)

                if (it) {
                    modalLoadingBottomSheet.mostrarMensagemDeSucesso("Rotina alimentar cadastrado com sucesso!")
                } else {
                    modalLoadingBottomSheet.mostrarMensagemDeErro("Houve um erro ao cadastrar uma nova rotina!")
                }
            }
        }

    }

    fun retornarFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
    }

    fun tratarNutricionistaJsonToData(nutricionista: String): NutritionistData {
        val gson = Gson()
        val nutricionistaData: NutritionistData =
            gson.fromJson(nutricionista, NutritionistData::class.java)
        return nutricionistaData
    }

}