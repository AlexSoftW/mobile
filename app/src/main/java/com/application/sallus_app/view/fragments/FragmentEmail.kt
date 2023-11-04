package com.application.sallus_app.view.fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroEmailBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.viewmodel.PacienteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentEmail : Fragment() {

    private lateinit var binding: FragmentCadastroEmailBinding
    private val viewModel: PacienteViewModel by viewModel()

    val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentCadastroEmailBinding.inflate(inflater, container, false)

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
                "MeuFragmentDestino", "Dados recebidos: $nome $endereco $telefone $genero " +
                        "$diabete, $colesterol, $hipertensao, $nenhum"
            )

            binding.nextButton1.setOnClickListener {

                checkInputs()

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
                    true,
                    null
                )

                viewModel.addingNewPaciente(pacienteData)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_cadastro, fragmentDestino)
                    .addToBackStack(null)
                    .commit()
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

    private fun checkInputs() {
        val email = binding.email.text.toString()
        val password = binding.senha.text.toString()
        val confirmPassword = binding.confirmarSenha.text.toString()

        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Preencha um email válido."
        } else if (!isPasswordValid(password)) {
            binding.senha.error = "Preencha uma senha válida."
        } else if (password != confirmPassword) {
            binding.confirmarSenha.error = "As senhas não coincidem."
        } else {
            Toast.makeText(context, "Teste", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        if (password.isBlank()) {
            return false  // A senha está vazia, portanto, é inválida
        }

        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { it.isLetterOrDigit().not() }
        val isLengthValid = password.length >= 8

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar && isLengthValid
    }


}