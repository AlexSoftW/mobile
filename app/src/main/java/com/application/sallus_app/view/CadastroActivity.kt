package com.application.sallus_app.view

import FragmentDadosPessoais
import FragmentDadosPessoaisNutri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ActivityCadastroBinding

import com.application.sallus_app.viewmodel.PacienteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastroActivity : AppCompatActivity() {
    private val cadastroViewModel: PacienteViewModel by viewModel()
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var proximoButton: Button
    private lateinit var textInputEditTextNome: EditText
    private lateinit var textInputEditTextTelefone: EditText
    private lateinit var textInputEditTextEndereco: EditText
    private lateinit var textInputEditTextGenero: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        textInputEditTextNome = findViewById(binding.)
//        textInputEditTextTelefone = findViewById(R.id.telefone)
//        textInputEditTextEndereco = findViewById(R.id.endereco)
//        textInputEditTextGenero = findViewById(R.id.genero)


        val primeiroFragment = FragmentDadosPessoaisNutri()
        //val primeiroFragment = FragmentDadosPessoaisNutri()
        replaceFragmentManager(primeiroFragment)


        //proximoButton = findViewById(R.id.proximo1)

//        proximoButton.setOnClickListener {
//            val nome = textInputEditTextNome.text.toString()
//            enviarNomeParaAPI(nome)
//        }
    }

    private fun enviarNomeParaAPI(nome: String) {
        Toast.makeText(this, "Enviando nome: $nome para a API", Toast.LENGTH_SHORT).show()
    }

    fun replaceFragmentManager(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_cadastro, fragment)
        fragmentTransaction.commit()
    }
}
    //Tudo que for manipular da view
    // (se quiser inicialiar alguma textView com um valor específico, etc)
    // (adicionar a ação do botão para ir para outro fragment é aqui tbm)
    /*fun setupView() {
        val fragmentHome = FragmentNutritionist()
        replaceFragmentManager(fragmentHome)



        binding.includeBadgeNutricionista.imagebuttonPatients.setOnClickListener {
            replaceFragmentManager(FragmentYoursPatients())
        }

    }*/


