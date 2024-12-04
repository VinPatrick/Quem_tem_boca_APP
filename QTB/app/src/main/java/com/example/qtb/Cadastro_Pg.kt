package com.example.qtb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cadastro_Pg : AppCompatActivity() {
    lateinit var Nome_camp: EditText
    lateinit var CPF_camp: EditText
    lateinit var Email_camp: EditText
    lateinit var Bairro_camp: EditText
    lateinit var Cidade_camp: EditText
    lateinit var Endereco_camp: EditText
    lateinit var Numero_camp: EditText
    lateinit var Telefone_camp: EditText
    lateinit var senha_camp: EditText
    lateinit var senha_confirm_camp: EditText
    lateinit var Bt_Volta_Login: Button
    lateinit var Bt_Cadastrar: Button
    lateinit var DAO: DAO_Usuarios
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_cadastro_pg)
        //ligaçao componentes e variaveis
        Nome_camp = findViewById(R.id.Nome_camp)
        CPF_camp = findViewById(R.id.CPF_camp)
        Email_camp = findViewById(R.id.Email_camp)
        Bairro_camp = findViewById(R.id.Bairro_camp)
        Cidade_camp = findViewById(R.id.Cidade_camp)
        Endereco_camp = findViewById(R.id.Endereco_camp)
        Numero_camp = findViewById(R.id.Numero_camp)
        Telefone_camp = findViewById(R.id.Telefone_camp)
        senha_camp = findViewById(R.id.senha_camp)
        senha_confirm_camp = findViewById(R.id.senha_confirm_camp)
        Bt_Volta_Login = findViewById(R.id.Bt_Volta_Login)
        Bt_Cadastrar = findViewById(R.id.Bt_Cadastrar)
        DAO = DAO_Usuarios(this)

        //funções aos botoes
        Bt_Volta_Login.setOnClickListener {
            Goto_login_Pg()
        }
        Bt_Cadastrar.setOnClickListener {
            if (validar_cadastro()) {
                var Novo_membro = Usuario(
                    CPF_camp.text.toString(),
                    Nome_camp.text.toString(),
                    senha_camp.text.toString(),
                    Email_camp.text.toString(),
                    Bairro_camp.text.toString(),
                    Cidade_camp.text.toString(),
                    Endereco_camp.text.toString(),
                    Numero_camp.text.toString().toInt(),
                    Telefone_camp.text.toString()
                )
                DAO.Adicionar(Novo_membro)
                Goto_login_Pg()
            }
        }
    }

    //funções
    fun Goto_login_Pg() {
        val goto_login = Intent(this, Login_Pg::class.java)
        startActivity(goto_login)
        finish()
    }

    fun validar_cadastro(): Boolean {
        var Usuario = ArrayList<Usuario>()
        if (senha_camp.text.toString() == senha_confirm_camp.text.toString()) {
            DAO.Pesquisa(Usuario, Email_camp.text.toString())
            if (Usuario.size >= 1) {
                Toast.makeText(this, "Email ja esta cadastrado", Toast.LENGTH_SHORT).show()
                return false
            }else{
                return true
            }
        } else {
            Toast.makeText(this, "Confirmação de senha errada", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}