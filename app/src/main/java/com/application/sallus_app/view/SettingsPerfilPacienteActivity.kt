package com.application.sallus_app.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivitySettingsPerfilPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.model.PerfilData
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsPerfilPacienteActivity : AppCompatActivity() {
    private val pacienteViewModel: PacienteViewModel by viewModel()
    private lateinit var binding: ActivitySettingsPerfilPacienteBinding
    private lateinit var dadosPaciente : PacienteData;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPerfilPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dadosDoPaciente = intent.getStringExtra("pacienteDataPerfil")
        dadosPaciente = tratarPacienteJsonToData(dadosDoPaciente!!)

        binding.includeToolbarSettings.textviewToolbarSettings.text = "Perfil"
        binding.nomeUsuario.text = dadosPaciente.nome
        binding.edittextEmailPacienteSettings.setText(dadosPaciente.email)
        binding.edittextNomeCompleto.setText(dadosPaciente.nome)
        binding.edittextSenhaPacienteSettings.setText(dadosPaciente.senha)

        val activity = this
        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            activity.finish()
        }

        binding.btnVoltar.setOnClickListener {
            activity.finish()
        }

//        binding.btnRedefinirSenha.setOnClickListener {
//            val intent = Intent(this, SettingsPasswordPacienteActivity::class.java)
//            startActivity(intent)
//        }

        binding.btnSalvarAlteracao.setOnClickListener {
            val inputEditTextNome = findViewById<EditText>(R.id.edittext_nome_completo)

            val nome = inputEditTextNome.text.toString()
            Log.i("CONTENT","NOME: $nome | ID: ${dadosPaciente.id}")
            val data = PerfilData(dadosPaciente.id, nome, dadosPaciente.avatar)
            pacienteViewModel.alterarDadosPerfil(data)
        }

    }

    fun tratarPacienteJsonToData(paciente: String): PacienteData {
        val gson = Gson()
        val pacienteData: PacienteData =
            gson.fromJson(paciente, PacienteData::class.java)
        return pacienteData
    }
}