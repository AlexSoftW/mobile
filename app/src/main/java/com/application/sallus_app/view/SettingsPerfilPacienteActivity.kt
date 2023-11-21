package com.application.sallus_app.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.ActivitySettingsPerfilPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.bumptech.glide.Glide
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream

class SettingsPerfilPacienteActivity : AppCompatActivity() {

    private val pacienteViewModel: PacienteViewModel by viewModel()
    private lateinit var binding: ActivitySettingsPerfilPacienteBinding
    private lateinit var dadosPaciente: PacienteData;

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri = Uri.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPerfilPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        setupObservers()
    }

    private fun setupView() {
        val dadosDoPaciente = intent.getStringExtra("pacienteDataPerfil")
        dadosPaciente = tratarPacienteJsonToData(dadosDoPaciente!!)

        binding.constraintlayoutSettingsPerfilPaciente.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
        }

        binding.includeToolbarSettings.textviewToolbarSettings.text = "Perfil"
        binding.nomeUsuario.text = dadosPaciente.nome
        binding.edittextEmailPacienteSettings.setText(dadosPaciente.email)
        binding.edittextNomeCompleto.setText(dadosPaciente.nome)
        binding.edittextSenhaPacienteSettings.setText(dadosPaciente.senha)

        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            finish()
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun setupObservers() {

//        binding.btnSalvarAlteracao.setOnClickListener {
//
//            // Verifica se a URI não é nula
//            selectedImageUri.let { uri ->
//                contentResolver.openInputStream(uri)?.use { inputStream ->
//                    val outputStrem = ByteArrayOutputStream()
//                    inputStream.copyTo(outputStrem)
//
//                    val byteArray = outputStrem.toByteArray()
//
//                    val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), byteArray)
//                    val fotoPart =
//                        MultipartBody.Part.createFormData("foto", "foto.jpg", requestBody)
//
//                    pacienteViewModel.alterarFoto(dadosPaciente.id!!, fotoPart)
//                } ?: run {
//                    // Trate o caso em que não foi possível abrir o InputStream
//                    Log.i("tagFotoSettings", "metodFoto: não foi possivel abrir o inputStrem")
//                }
//            }
//
//        }

//        binding.btnSalvarAlteracao.setOnClickListener {
//            val inputEditTextNome = findViewById<EditText>(R.id.edittext_nome_completo)
//
//            val nome = inputEditTextNome.text.toString()
//            Log.i("CONTENT", "NOME: $nome | ID: ${dadosPaciente.id}")
//            val data = PerfilData(dadosPaciente.id, nome, dadosPaciente.avatar)
//            pacienteViewModel.alterarDadosPerfil(data)
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data!!
            Log.i("tagImageUri", "onActivityResult: $selectedImageUri")

            val imageviewFotoEscolhida = binding.fotoPaciente

            Glide.with(this)
                .load(selectedImageUri)
                .into(imageviewFotoEscolhida)
        }
    }

    fun tratarPacienteJsonToData(paciente: String): PacienteData {
        val gson = Gson()
        val pacienteData: PacienteData =
            gson.fromJson(paciente, PacienteData::class.java)
        return pacienteData
    }
}