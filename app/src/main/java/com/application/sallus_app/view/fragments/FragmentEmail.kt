package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.databinding.FragmentCadastroEmailBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.viewmodel.PacienteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentEmail : Fragment() {
    private val viewModel: PacienteViewModel by viewModel()
    private lateinit var binding: FragmentCadastroEmailBinding
    private lateinit var modalLoadingBottomSheet: ModalLoadingBottomSheet
    val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentCadastroEmailBinding.inflate(inflater, container, false)

        modalLoadingBottomSheet =
            ModalLoadingBottomSheet("Cadastrando sua conta, aguarde um momento...")

        val args = arguments
        if (args != null) {
            val nome = args.getString("Nome")
            val telefone = args.getString("Telefone")
            val endereco = args.getString("Endereco")
            val genero = args.getString("Genero")
            val diabete = args.getBoolean("Diabete")
            val colesterol = args.getBoolean("Colesterol")
            val hipertensao = args.getBoolean("Hipertensao")
            val nenhum = args.getBoolean("Nenhum")
            Log.d(
                "MeuFragmentDestino", "Dados recebidos: $nome $endereco $telefone $genero" +
                        "$diabete, $colesterol, $hipertensao, $nenhum"
            )

            binding.nextButton1.setOnClickListener {
                val email = binding.email.text.toString()
                val senha = binding.senha.text.toString()

                val confirmrSenha = binding.confirmarSenha.text.toString()
                bundle.putString("Email", email)
                bundle.putString("Senha", senha)
                bundle.putString("ConfirmarSenha", confirmrSenha)
                val fragmentDestino = FragmentEmail()
                fragmentDestino.arguments = bundle
                Log.d("Valores ", "Email = $email, senha = $senha")

                val pacienteData = PacienteData(
                    nome ?: "",
                    email,
                    senha,
                    0.0,
                    0,
                    genero ?: "",
                    endereco ?: "",
                    0,
                    telefone ?: "",
                    false,
                    "",
                    true,
                    null
                )

                viewModel.addingNewPaciente(pacienteData)

                modalLoadingBottomSheet.show(childFragmentManager, ModalLoadingBottomSheet.TAG)

            }
        }

        viewModel.responseCadastrarPacienteBottomSheet.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)

                if (it) {
                    modalLoadingBottomSheet.mostrarMensagemDeSucesso("Conta cadastrada com sucesso!")
                    modalLoadingBottomSheet.retornarActivityAnterior()
                } else {
                    modalLoadingBottomSheet.mostrarMensagemDeErro("Houve um erro ao cadastrar sua conta!")
                    modalLoadingBottomSheet.retornarActivityAnterior()
                }
            }
        }

        binding.backButton2.setOnClickListener {
            retornarFragment()
        }

        return binding.root

    }

    fun retornarFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
    }
}