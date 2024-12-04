package com.example.qtb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login_Pg : AppCompatActivity() {
    //declarando variaveis
    lateinit var Bt_voltar_capa_pg: Button
    lateinit var Bt_to_cadastro_pg: Button
    lateinit var Email_Camp: EditText
    lateinit var Password_Camp: EditText
    lateinit var DAO: DAO_Usuarios
    lateinit var Bt_in: Button
    //iniciando tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_tela_login)
        //passando contexto dao
        DAO = DAO_Usuarios(this)
        //Relacionando botoes aos endereços xml
        Email_Camp = findViewById(R.id.Email_Camp)
        Password_Camp = findViewById(R.id.Password_Camp)
        Bt_voltar_capa_pg = findViewById(R.id.Bt_voltar_capa_pg)
        Bt_to_cadastro_pg = findViewById(R.id.Bt_to_cadastro_pg)
        Bt_in = findViewById(R.id.Bt_in)
        //Dando funçoes aos botoes
        Bt_voltar_capa_pg.setOnClickListener{
            Goto_capa_Pg()
        }
        Bt_to_cadastro_pg.setOnClickListener{
            Goto_cadastro_Pg()
        }
        Bt_in.setOnClickListener{
            if (valida_login()) {
                Goto_Home_Pg()
            }
        }

    }
    // Funções
    fun Goto_capa_Pg(){
        val goto_capa = Intent(this,Capa_Pg::class.java)
        startActivity(goto_capa)
        finish()
    }

    fun Goto_cadastro_Pg(){
        val goto_cadastro = Intent(this,Cadastro_Pg::class.java)
        startActivity(goto_cadastro)
        finish()
    }

    fun Goto_Home_Pg(){
        val goto_home = Intent(this,Home_Pg::class.java)
        startActivity(goto_home)
        finish()
    }
    fun valida_login(): Boolean{
        var log = ArrayList<Usuario>()
        DAO.Pesquisa(log , Email_Camp.text.toString())
        if(log.size == 1) {
            if (log.get(0).Senha.toString() == Password_Camp.text.toString()) {
                return true
            } else {
                Toast.makeText(this, "Senha errada", Toast.LENGTH_SHORT).show()
                return false
            }
        }else{
            Toast.makeText(this, "Email não encontrado", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}