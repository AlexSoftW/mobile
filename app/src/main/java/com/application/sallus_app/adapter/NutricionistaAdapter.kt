package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewTodosNutricionistasBinding
import com.application.sallus_app.model.NutritionistData
import com.application.sallus_app.view.fragments.ModalLoadingBottomSheet
import com.application.sallus_app.viewmodel.NutritionistViewModel
import com.application.sallus_app.viewmodel.PacienteViewModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NutricionistaAdapter(
    private val idPaciente: Long,
    private val viewModelPaciente: PacienteViewModel,
) :
    RecyclerView.Adapter<NutricionistaAdapter.NutricionistaViewHolder>() {

    private var nutritioniostList = mutableListOf<NutritionistData>()
    private lateinit var modalLoadingBottomSheet: ModalLoadingBottomSheet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutricionistaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewTodosNutricionistasBinding.inflate(inflater, parent, false)

        return NutricionistaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NutricionistaViewHolder, position: Int) {
        val nutricionistas = nutritioniostList[position]
        holder.bind(nutricionistas)
    }

    override fun getItemCount(): Int = nutritioniostList.size

    @SuppressLint("NotifyDataSetChanged")
    fun subitList(newItems: List<NutritionistData>) {
        nutritioniostList.clear()
        nutritioniostList.addAll(newItems)
        notifyDataSetChanged()
    }

    fun decodeBase64ToBitmap(baseString: String): Bitmap {
        val decodedBytes = Base64.decode(baseString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    inner class NutricionistaViewHolder(private val binding: ItemRecyclerViewTodosNutricionistasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(nutricionista: NutritionistData) {
            modalLoadingBottomSheet = ModalLoadingBottomSheet("Vinculando...")

            binding.textviewNamePatientItemTodosNutricionistas.text = nutricionista.nome
            binding.textviewTelephonePatientItemTodosNutricionistas.text = nutricionista.telefone

            binding.imageviewGenderPatientItemTodosNutricionistas.setImageResource(
                if (nutricionista.genero == "Masculino") {
                    R.drawable.ic_male_gender
                } else if (nutricionista.genero == "Feminino") {
                    R.drawable.ic_female_gender
                } else {
                    com.google.android.material.R.drawable.navigation_empty_icon
                }
            )

            if (nutricionista.foto != null) {
                val bitmapImage = decodeBase64ToBitmap(nutricionista.foto!!)

                Glide.with(binding.root.context)
                    .load(bitmapImage)
                    .into(binding.imageviewPatientItemTodosNutricionistas)
            } else {
                Glide.with(binding.root.context)
                    .load(R.mipmap.default_profile)
                    .into(binding.imageviewPatientItemTodosNutricionistas)
            }


            binding.imagebuttonWhatsappItemTodosNutricionistas.setOnClickListener {

                binding.imagebuttonWhatsappItemTodosNutricionistas.visibility = View.GONE
                binding.progressbarItemTodosNutricionistas.visibility = View.VISIBLE

                viewModelPaciente.vincularComNutricionista(idPaciente, nutricionista.id)

                CoroutineScope(Dispatchers.Main).launch {
                    delay(2000)
                    viewModelPaciente.buscarNutricionistaVinculado(idPaciente)

                    binding.imagebuttonWhatsappItemTodosNutricionistas.visibility = View.VISIBLE
                    binding.progressbarItemTodosNutricionistas.visibility = View.GONE

                    val link = "https://api.whatsapp.com/send?phone=55${
                        nutricionista.telefone.trim()
                    }&text=Olá ${nutricionista.nome}, tudo bem? " +
                            "vim do aplicativo Salus Well, " +
                            "e gostaria de ter uma primeira consulta com você."

                    val context = binding.root.context
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

                    context.startActivity(intent)
                }


            }

        }

    }


}