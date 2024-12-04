package com.example.qtb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home_Pg : AppCompatActivity() {
    //declarando variaveis
    lateinit var Bt_Restaurante: Button
    lateinit var Bt_Pedidos: Button
    lateinit var Bt_Config: Button
    lateinit var Pesquisa_camp: EditText
    //iniciando tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_home_pg)
        //Relacionando botoes aos endereços xml
        Bt_Restaurante = findViewById(R.id.Bt_Restaurante)
        Bt_Pedidos = findViewById(R.id.Bt_Pedidos)
        Bt_Config = findViewById(R.id.Bt_Config)
        Pesquisa_camp = findViewById(R.id.Pesquisa_camp)

        //Dando funçoes aos botoes
        Bt_Restaurante.setOnClickListener {
        }
        Bt_Pedidos.setOnClickListener {
        }
        Bt_Config.setOnClickListener {
        }
    }
    // Funções

}