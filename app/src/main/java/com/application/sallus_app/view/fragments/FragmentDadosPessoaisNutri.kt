import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroBinding
import com.application.sallus_app.view.fragments.FragmentCrn

class FragmentDadosPessoaisNutri : Fragment() {
    private lateinit var binding: FragmentCadastroBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)

        val fragmentDestino = FragmentCrn()

        binding.proximo1.setOnClickListener {

            checkInputs()

            val nome = binding.nomePaciente.text.toString()
            val telefone = binding.telefone.text.toString()
            val endereco = binding.endereco.text.toString()
            val genero = binding.genero.text.toString()

            Log.d("MeuFragmentDestino", "Dados teste: $nome $telefone $endereco $genero")

            val bundle = Bundle()
            bundle.putString("Nome", nome)
            bundle.putString("Telefone", telefone)
            bundle.putString("Endereco", endereco)
            bundle.putString("Genero", genero)

            fragmentDestino.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_cadastro, fragmentDestino)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }

    fun retornarFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
    }

    private fun checkInputs(): Boolean {
        val nome = binding.nomePaciente.text.toString()
        val telefone = binding.telefone.text.toString()
        val endereco = binding.endereco.text.toString()
        val genero = binding.genero.text.toString()

        if (nome.isBlank()) {
            binding.nomePaciente.error = "Preencha seu nome."
            return false
        } else if (telefone.isBlank() || telefone.length < 9) {
            binding.telefone.error = "Preencha um número válido."
            return false
        } else if (endereco.isBlank()) {
            binding.endereco.error = "Preencha o seu endereço."
            return false
        } else if (genero.isBlank()) {
            binding.genero.error = "Selecione o seu gênero."
            return false
        } else {
            return true
        }
    }
}
