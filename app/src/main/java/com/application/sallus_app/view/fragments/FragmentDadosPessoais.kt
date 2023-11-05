import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroBinding
import com.application.sallus_app.view.fragments.FragmentComorbidade

class FragmentDadosPessoais : Fragment() {
    private lateinit var binding: FragmentCadastroBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)

        val fragmentDestino = FragmentComorbidade()

        binding.telefone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Limita o campo de telefone a 9 dígitos
                if (s?.length ?: 0 > 11) {
                    binding.telefone.setText(s?.subSequence(0, 11))
                    binding.telefone.setSelection(11)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        binding.proximo1.setOnClickListener {

            if (checkInputs()) {

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
            } else {
                Toast.makeText(context, "Tente novamente", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun checkInputs(): Boolean {
        val nome = binding.nomePaciente.text.toString()
        val telefone = binding.telefone.text.toString()
        val endereco = binding.endereco.text.toString()
        val genero = binding.genero.text.toString()

        if (nome.isBlank()) {
            binding.nomePaciente.error = "Preencha seu nome."
            return false
        } else if (telefone.isBlank()) {
            binding.telefone.error = "Preencha seu telefone."
            return false
        } else if (telefone.isBlank()) {
            binding.telefone.error = "Preencha seu telefone."
            return false
        } else if (endereco.isBlank()) {
            binding.endereco.error = "Preencha o seu endereço."
            return false
        } else if (genero.isBlank()) {
            binding.genero.error = "Selecione o seu gênero."
            return false
        } else {
        }
        return true
    }
}
