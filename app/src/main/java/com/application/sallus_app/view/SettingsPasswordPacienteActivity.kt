package com.application.sallus_app.view

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivitySettingsPasswordPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.model.UsuarioData
import com.application.sallus_app.view.fragments.ModalLoadingBottomSheet
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsPasswordPacienteActivity : AppCompatActivity() {
    private val pacienteViewModel: PacienteViewModel by viewModel()
    private lateinit var modalLoadingBottomSheet: ModalLoadingBottomSheet
    private lateinit var binding: ActivitySettingsPasswordPacienteBinding
    private lateinit var dadosPaciente: PacienteData;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPasswordPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dadosDoPaciente = intent.getStringExtra("pacienteDataSenha")
        dadosPaciente = tratarPacienteJsonToData(dadosDoPaciente!!)

        setupView()
    }

    fun setupView() {
        modalLoadingBottomSheet = ModalLoadingBottomSheet("Alterando sua senha, aguarde...")

        binding.includeToolbarSettings.textviewToolbarSettings.text = "Alteração de senha"

        val activity = this
        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            activity.finish()
        }

        binding.btnVoltar.setOnClickListener {
            activity.finish()
        }

        binding.btnSalvarAlteracao.setOnClickListener {
            val inputEditTextSenhaNova = findViewById<EditText>(R.id.edittext_nova_senha)
            val inputEditTextConfirmSenha =
                findViewById<EditText>(R.id.edittext_confirmar_nova_senha)

            val email = dadosPaciente.email
            val senhaNova = inputEditTextSenhaNova.text.toString()
            val confirmacaoSenha = inputEditTextConfirmSenha.text.toString()

            val data = UsuarioData(email, senhaNova)
            pacienteViewModel.alterarSenha(data)
            modalLoadingBottomSheet.show(supportFragmentManager, ModalLoadingBottomSheet.TAG)
        }

        pacienteViewModel.responseEditarSenhaPacienteBottomSheet.observe(this) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)

                if (it) {
                    modalLoadingBottomSheet.mostrarMensagemDeSucesso("senha alterada com sucesso!")
                    modalLoadingBottomSheet.retornarActivityAnterior()
                } else {
                    modalLoadingBottomSheet.mostrarMensagemDeErro("Houve um erro ao alterar sua senha!")
                }
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