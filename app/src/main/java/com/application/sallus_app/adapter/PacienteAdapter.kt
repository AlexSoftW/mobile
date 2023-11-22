package com.application.sallus_app.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.sallus_app.R
import com.application.sallus_app.databinding.ItemRecyclerViewYoursPatientsBinding
import com.application.sallus_app.model.PacienteDetailsData
import com.bumptech.glide.Glide

class PacienteAdapter : RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>() {

    private var pacientesList = mutableListOf<PacienteDetailsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerViewYoursPatientsBinding.inflate(inflater, parent, false)

        return PacienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = pacientesList[position]
        holder.bind(paciente)
    }

    override fun getItemCount(): Int = pacientesList.size

    @SuppressLint("NotifyDataSetChanged")
    fun subitList(newItems: List<PacienteDetailsData>) {
        pacientesList.clear()
        pacientesList.addAll(newItems)
        notifyDataSetChanged()
    }

//    fun decodeBase64ToBitmap(baseString: String): Bitmap {
//        val decodedBytes = Base64.decode(baseString, Base64.DEFAULT)
//        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
//    }

    inner class PacienteViewHolder(private val binding: ItemRecyclerViewYoursPatientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("PrivateResource")
        fun bind(paciente: PacienteDetailsData) {
            binding.textviewNamePatientItemYoursPatients.text = paciente.nome

            binding.imageviewGenderPatientItemYoursPatients.setImageResource(
                if (paciente.genero == "Masculino") {
                    R.drawable.ic_male_gender
                } else if (paciente.genero == "Feminino") {
                    R.drawable.ic_female_gender
                } else {
                    com.google.android.material.R.drawable.navigation_empty_icon
                }
            )

//            if (paciente.foto != null) {
//                val bitmapImage = decodeBase64ToBitmap(paciente.foto!!)
//
//                Glide.with(binding.root.context)
//                    .load(bitmapImage)
//                    .into(binding.imageviewPatientItemYoursPatients)
//            } else {
//                Glide.with(binding.root.context)
//                    .load(R.mipmap.default_profile)
//                    .into(binding.imageviewPatientItemYoursPatients)
//            }

            binding.textviewTelephonePatientItemYoursPatients.text = paciente.telefone

        }
    }

}