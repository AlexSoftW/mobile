package com.application.sallus_app.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.databinding.ActivitySettingsSuporteBinding

class SettingsSuporteActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsSuporteBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsSuporteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    fun setupView(){

        binding.includeToolbarSettings.textviewToolbarSettings.text = "Suporte"

        val activity = this
        binding.includeToolbarSettings.buttonBackToolbarSettings.setOnClickListener {
            activity.finish()
        }


        binding.btnEnviarFormulario.setOnClickListener{
            val assunto = binding.textFieldAssunto.text.toString()
            val mensagem = binding.textFieldMensagem.text.toString()
            val userEmail = binding.textFieldEmail.text.toString()
            enviarFormulario(assunto, mensagem, userEmail)
        }
    }

    fun enviarFormulario(assunto : String, mensagem : String, userEmail : String){
        val email = Intent(Intent.ACTION_SEND)

        email.data = Uri.parse("mailto")
        email.type = "message/rfc822"

        email.putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf("saluswell_suporte@outlook.com")
        )
        email.putExtra(
            Intent.EXTRA_SUBJECT,
            "SUPORTE: $assunto"
        )
        email.putExtra(
            Intent.EXTRA_TEXT,
            "$mensagem\n\n\n$userEmail"
        )
        startActivity(Intent.createChooser(email, "Enviar seu e-mail para o suporte\nSalus Well"))
    }

}