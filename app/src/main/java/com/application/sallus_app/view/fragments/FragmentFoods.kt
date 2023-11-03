package com.application.sallus_app.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.application.sallus_app.adapter.FoodAdapter
import com.application.sallus_app.databinding.FragmentFoodsBinding
import com.application.sallus_app.viewmodel.FoodViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentFoods : Fragment() {

    lateinit var binding: FragmentFoodsBinding
    private val viewmodel: FoodViewModel by viewModel()
    lateinit var adapter: FoodAdapter

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

    private fun setupView() {
        adapter = FoodAdapter()
        binding.recyclerViewFood.adapter = adapter
    }

    private fun setupObservers() {

        val sugestoesAlimentos = mutableListOf<String>()

        val adapterSearchbarFoods = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            sugestoesAlimentos
        )

        viewmodel.buscarTodosAlimentos()

        binding.searchBarFoods.setAdapter(adapterSearchbarFoods)

        viewmodel.listAlimentos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
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
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewFrutasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Fruta")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewGraosCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Grão")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewMassasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Massas")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewVerdurasCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Verdura")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }

        binding.imageviewLegumesCategoryFoods.setOnClickListener {
            binding.textviewButtonLimparSelecao.visibility = View.VISIBLE
            viewmodel.buscarAlimentosPorTipo("Legume")
            viewmodel.listaAlimentoPorCategoria.observe(viewLifecycleOwner) {
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