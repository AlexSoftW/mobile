import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.sallus_app.R
import com.application.sallus_app.databinding.FragmentCadastroBinding
import com.application.sallus_app.view.fragments.FragmentComorbidade
import com.application.sallus_app.view.fragments.FragmentCrn
import com.application.sallus_app.view.fragments.FragmentEmail

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
}
