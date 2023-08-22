package com.application.sallus_app

import android.app.Application

// Classe que extends Application.
// ela serve para ser inicializada antes de qualquer outra classe,
// para que a injeção de dependências possar injetar em todas classes e não só na MainActivity.

class MyApplication : Application() {
}