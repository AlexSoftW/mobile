package com.application.sallus_app.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.application.sallus_app.viewmodel.PacienteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClienteActivity : AppCompatActivity() {

    private val pacienteViewModel: PacienteViewModel by viewModel();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pacienteViewModel.fetchTodosPacientes()
    }
}