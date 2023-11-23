package com.application.sallus_app.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivitySettingsPerfilPacienteBinding
import com.application.sallus_app.model.PacienteData
import com.application.sallus_app.model.PerfilData
import com.application.sallus_app.view.fragments.ModalLoadingBottomSheet
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class SettingsPerfilPacienteActivity : AppCompatActivity() {

    private val pacienteViewModel: PacienteViewModel by viewModel()
    private lateinit var binding: ActivitySettingsPerfilPacienteBinding
    private lateinit var modalLoadingBottomSheet: ModalLoadingBottomSheet
    private lateinit var dadosPaciente: PacienteData;

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri = Uri.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPerfilPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dadosDoPaciente = intent.getStringExtra("pacienteDataPerfil")
        dadosPaciente = tratarPacienteJsonToData(dadosDoPaciente!!)

        setupView()

        setupObservers()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data!!
            Log.i("tagImageUri", "onActivityResult: $selectedImageUri")

            if (isFileSizeWithinLimit(selectedImageUri, 175)) {
                Glide.with(this).load(selectedImageUri).into(binding.fotoPaciente)
                binding.legenda.text = "Alterar imagem"
                binding.legenda.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context, R.color.grey_oppacity
                    )
                )
            } else {
                Glide.with(this).load(R.mipmap.default_profile).into(binding.fotoPaciente)
                binding.legenda.text =
                    "Imagem excede o limite máximo de 175kb\nselecione outra imagem"
                binding.legenda.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context, R.color.red_error_oppacity
                    )
                )
                selectedImageUri = Uri.EMPTY
            }

        }
    }

    private fun setupView() {
        modalLoadingBottomSheet = ModalLoadingBottomSheet("Alterando suas informações, aguarde...")

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
        binding.btnSalvarAlteracao.setOnClickListener {
            val inputEditTextNome = findViewById<EditText>(R.id.edittext_nome_completo)
            val nome = inputEditTextNome.text.toString()
            Log.i("CONTENT", "NOME: $nome | ID: ${dadosPaciente.id}")
            val data = PerfilData(dadosPaciente.id, nome, dadosPaciente.avatar)

            if (selectedImageUri != Uri.EMPTY) {
                val file = createTempFile(this, selectedImageUri)
                val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
                val fotoPart = MultipartBody.Part.createFormData("foto", file.name, requestBody)
                pacienteViewModel.alterarFoto(dadosPaciente.id!!, fotoPart)
            }

            pacienteViewModel.alterarDadosPerfil(data, dadosPaciente.id!!)
            modalLoadingBottomSheet.show(supportFragmentManager, ModalLoadingBottomSheet.TAG)
        }

        pacienteViewModel.responseEditarDadosPacienteBottomSheet.observe(this) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)

                if (it) {
                    modalLoadingBottomSheet.mostrarMensagemDeSucesso(
                        "Dados alterados com sucesso!\n" +
                                "desconecte e entre novamente para realizar a alteração."
                    )
                    modalLoadingBottomSheet.retornarTelaLogin()
                }
            }
        }
    }

    fun tratarPacienteJsonToData(paciente: String): PacienteData {
        val gson = Gson()
        val pacienteData: PacienteData = gson.fromJson(paciente, PacienteData::class.java)
        return pacienteData
    }

    // Função para criar um arquivo temporário a partir de um URI
    fun createTempFile(context: Context, uri: Uri): File {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_file")
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file
    }

    fun isFileSizeWithinLimit(uri: Uri, maxSize: Int): Boolean {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val fileSizeInBytes: Long = inputStream?.available()?.toLong() ?: 0
        val fileSizeInKb = fileSizeInBytes / 1024
        return fileSizeInKb <= maxSize
    }
}