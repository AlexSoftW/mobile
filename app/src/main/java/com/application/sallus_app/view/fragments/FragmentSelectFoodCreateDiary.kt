package com.application.sallus_app.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.adapter.SelectFoodCreateDiaryAdapter
import com.application.sallus_app.databinding.FragmentFoodsBinding
import com.application.sallus_app.viewmodel.FoodViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentSelectFoodCreateDiary : Fragment() {

    lateinit var binding: FragmentFoodsBinding
    private val viewmodel: FoodViewModel by viewModel()
    private lateinit var adapter: SelectFoodCreateDiaryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodsBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }


    fun setupView() {
        adapter = SelectFoodCreateDiaryAdapter()
        binding.recyclerViewFood.adapter = adapter

        binding.textviewTitleFoods.text = "estou aqui em outra telinha"
//        binding.buttonCriarRotina.visibility = View.VISIBLE

        binding.buttonCriarRotina.setOnClickListener {
            val bundle = Bundle()
            val selectedFoods = adapter.selectedFoods
            val gson = Gson()
            val json = gson.toJson(selectedFoods)
            bundle.putString("selectedFoods", json)

            val fragment = FragmentCreateRoutine()
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_nutricionista, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupObservers() {

        val sugestoesAlimentos = mutableListOf<String>()

        val adapterSearchbarFoods = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            sugestoesAlimentos
        )

        viewmodel.buscarTodosAlimentos()

        viewmodel.listAlimentos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.searchBarFoods.setAdapter(adapterSearchbarFoods)

        binding.searchBarFoods.setOnItemClickListener { parent, _, position, _ ->
            val selectedFood = parent.getItemAtPosition(position) as String
            viewmodel.buscarAlimentoPeloNome(selectedFood)

            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE

            viewmodel.alimentoInformadoSearchbar.observe(viewLifecycleOwner) {
                adapter.submitListOnlyFood(it)
            }
        }

        viewmodel.listAlimentos.observe(viewLifecycleOwner) { alimentos ->
            for (alimento in alimentos) {
                sugestoesAlimentos.add(alimento.nome)
            }
        }

        binding.searchBarFoods.setOnItemClickListener { parent, _, position, _ ->
            val selectedFood = parent.getItemAtPosition(position) as String
            viewmodel.buscarAlimentoPeloNome(selectedFood)

            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE

            viewmodel.alimentoInformadoSearchbar.observe(viewLifecycleOwner) {
                adapter.submitListOnlyFood(it)
            }

            binding.searchBarFoods.hideKeyboard()
        }

        binding.textviewButtonLimparSelecao.setOnClickListener {

            binding.textviewButtonLimparSelecao.visibility = View.GONE
            binding.searchBarFoods.setText("")

            viewmodel.listAlimentos.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewCarnesCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Frango")
            viewmodel.tipoAlimentoInformadoCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewFrutasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Fruta")
            viewmodel.tipoAlimentoInformadoCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewGraosCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Grão")
            viewmodel.tipoAlimentoInformadoCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewMassasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Massas")
            viewmodel.tipoAlimentoInformadoCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewVerdurasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Verdura")
            viewmodel.tipoAlimentoInformadoCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewLegumesCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Legume")
            viewmodel.tipoAlimentoInformadoCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

    }

    private fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

}