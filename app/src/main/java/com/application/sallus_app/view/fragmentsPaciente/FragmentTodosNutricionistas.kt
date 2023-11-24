package com.application.sallus_app.view.fragmentsPaciente

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.adapter.NutricionistaAdapter
import com.application.sallus_app.databinding.FragmentTodosNutricionistaBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.view.fragments.ModalTwoOptionsBottomSheet
import com.application.sallus_app.viewmodel.NutritionistViewModel
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentTodosNutricionistas : Fragment() {
    private val viewmodelNutricionista: NutritionistViewModel by viewModel()
    private val viewmodelPaciente: PacienteViewModel by viewModel()
    private lateinit var binding: FragmentTodosNutricionistaBinding
    private lateinit var adapter: NutricionistaAdapter
    private lateinit var dadosPaciente: PacienteData

    private lateinit var modalTwoOptionsBottomSheet: ModalTwoOptionsBottomSheet


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodosNutricionistaBinding.inflate(inflater, container, false)

        setupView()
        setupObservers()

        return binding.root
    }

    private fun setupView() {
        val intentFromActivity = requireActivity().intent

        val dadosPacienteEmString = intentFromActivity.getStringExtra("pacienteDataValue")
        dadosPaciente = tratarPacienteJsonToData(dadosPacienteEmString!!)

        adapter =
            NutricionistaAdapter(
                dadosPaciente.id!!,
                viewmodelPaciente
            )
        binding.recyclerViewTodosNutricionistas.adapter = adapter

        modalTwoOptionsBottomSheet = ModalTwoOptionsBottomSheet(
            "Tem certeza que deseja desvincular com o este nutricionista?",
            object : ModalTwoOptionsBottomSheet.BottomSheetListener {
                override fun onButtonYesClick() {
                    viewmodelPaciente.desvincularPacienteComNutricionista(dadosPaciente.id!!)
                    modalTwoOptionsBottomSheet.mostrarCarregamento("Aguarde...")

                    viewmodelPaciente.isVinculado.observe(viewLifecycleOwner) {
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(3000)
                            if (!it) {
                                modalTwoOptionsBottomSheet.mostrarMensagemDeSucesso("Desvinculado com sucesso!")
                                modalTwoOptionsBottomSheet.fecharModal()
                            }
                        }
                    }
                }

                override fun onButtonNoClick() {
                    modalTwoOptionsBottomSheet.dismiss()
                }

            }
        )
    }

    private fun setupObservers() {
        viewmodelNutricionista.fetchTodosNutricionistas()
        viewmodelPaciente.buscarNutricionistaVinculado(dadosPaciente.id!!)

        viewmodelPaciente.nutricionistaVinculado.observe(viewLifecycleOwner) { nutricionista ->

            if (nutricionista.foto != null) {
                val bitmapImage = decodeBase64ToBitmap(nutricionista.foto)

                Glide.with(binding.root.context)
                    .load(bitmapImage)
                    .into(binding.imageviewNutritionistItemSeuNutricionista)
            } else {
                Glide.with(binding.root.context)
                    .load(R.mipmap.default_profile)
                    .into(binding.imageviewNutritionistItemSeuNutricionista)
            }

            binding.textviewNameNutritionistItemSeuNutricionista.text = nutricionista.nome
            binding.textviewTelephoneNutritionistItemSeuNutricionista.text = nutricionista.telefone
            binding.textviewCrnNutricionistaItemSeuNutricionista.text = nutricionista.crn
            binding.textviewEmailNutricionistaItemSeuNutricionista.text = nutricionista.email

            binding.imagebuttonWhatsappItemSeuNutricionista.setOnClickListener {
                val link = "https://api.whatsapp.com/send?phone=55${
                    nutricionista.telefone.trim()
                }&text=Olá ${nutricionista.nome}, tudo bem? " +
                        "gostaria de continuar acompanhando minha rotina com você."

                val context = binding.root.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

                context.startActivity(intent)
            }

            binding.buttonDesvincularNutricionistaSeuNutricionista.setOnClickListener {
                modalTwoOptionsBottomSheet.show(
                    requireActivity().supportFragmentManager,
                    ModalTwoOptionsBottomSheet.TAG
                )
            }
        }

        viewmodelNutricionista.listNutricionista.observe(viewLifecycleOwner) {
            adapter.subitList(it)
        }

        viewmodelPaciente.isVinculado.observe(viewLifecycleOwner) {
            if (it) {
                binding.recyclerViewTodosNutricionistas.visibility = View.GONE
                binding.cardviewSeuNutricionista.visibility = View.VISIBLE
                binding.textviewTitleTodosNutricionistas.text = "Seu nutricionista"
                binding.textviewSubtitleTodosNutricionistas.text =
                    "Acompanhe sua rotina alimentar com o seu nutricionista"
            } else {
                binding.recyclerViewTodosNutricionistas.visibility = View.VISIBLE
                binding.cardviewSeuNutricionista.visibility = View.GONE
                binding.textviewTitleTodosNutricionistas.text = "Todos os nutricionistas"
                binding.textviewSubtitleTodosNutricionistas.text =
                    "Procure um nutricionista oara começar sua nova rotina"
            }
        }

    }

    fun tratarPacienteJsonToData(paciente: String): PacienteData {
        val gson = Gson()
        val pacienteData: PacienteData =
            gson.fromJson(paciente, PacienteData::class.java)
        return pacienteData
    }

    fun decodeBase64ToBitmap(baseString: String): Bitmap {
        val decodedBytes = Base64.decode(baseString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}