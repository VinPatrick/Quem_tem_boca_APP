package com.example.qtb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Cad_Restaurante : AppCompatActivity() {
    lateinit var Tipo_Padaria:RadioButton
    lateinit var Tipo_FastFood:RadioButton
    lateinit var Tipo_Restaurante:RadioButton
    lateinit var Tipo_hortifruti:RadioButton
    lateinit var Tipo_Bebidas:RadioButton
    lateinit var Tipo_Atacado:RadioButton
    lateinit var Nome_R_Camp:EditText
    lateinit var Bt_voltar_Home:Button
    lateinit var Bt_Cadastrar_Restaurante:Button
    lateinit var Cliente: String
    lateinit var DAO: DAO_Restaurantes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_cad_restaurante)
        // passando contexto dao
        DAO = DAO_Restaurantes(this)
        //Recebendo cliente
        Cliente = intent.getStringExtra("Cliente").toString()
        //linkando visual botoes as suas funçoes
        Tipo_Padaria = findViewById(R.id.Tipo_Padaria)
        Tipo_FastFood = findViewById(R.id.Tipo_FastFood)
        Tipo_Restaurante = findViewById(R.id.Tipo_Restaurante)
        Tipo_hortifruti = findViewById(R.id.Tipo_hortifruti)
        Tipo_Bebidas = findViewById(R.id.Tipo_Bebidas)
        Tipo_Atacado = findViewById(R.id.Tipo_Atacado)
        Nome_R_Camp = findViewById(R.id.Nome_R_Camp)
        Bt_voltar_Home = findViewById(R.id.Bt_voltar_Home)
        Bt_Cadastrar_Restaurante = findViewById(R.id.Bt_Cadastrar_Restaurante)

        //dando funçoes aos botoes
        Bt_voltar_Home.setOnClickListener {
            Goto_Home_Pg()
        }
        Bt_Cadastrar_Restaurante.setOnClickListener {
            if (validacadastro()){
                var novo = criandorestaurante()
                DAO.Adicionar(novo)
                Goto_Home_Pg()
            }
        }
    }
    //funçoes
    fun Goto_Home_Pg(){
        val goto_home = Intent(this,Home_Pg::class.java)
        goto_home.putExtra("ID_Cliente", Cliente)
        startActivity(goto_home)
        finish()
    }
    fun validacadastro():Boolean{
        if (Nome_R_Camp.text.toString() != ""){
            if (Tipo_Restaurante.isChecked || Tipo_Atacado.isChecked || Tipo_Bebidas.isChecked || Tipo_Padaria.isChecked || Tipo_hortifruti.isChecked || Tipo_FastFood.isChecked){
                return true
            }else{
                Toast.makeText(this, "Marque uma das opçoes de tipo de estabelecimento", Toast.LENGTH_SHORT).show()
                return false
            }
        }else
        {
            Toast.makeText(this, "Digite o nome do seu estabelecimento", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    //funçao para criar o novo restaurante identificando o tipo de restaurante
    fun criandorestaurante():Restaurante {
        if (Tipo_Restaurante.isChecked) {
            var novo = Restaurante(0,Nome_R_Camp.text.toString(),Cliente, "Restaurante")
            return novo
        } else if (Tipo_Atacado.isChecked) {
            var novo = Restaurante(0,Nome_R_Camp.text.toString(), Cliente, "Atacado")
            return novo
        } else if (Tipo_Bebidas.isChecked) {
            var novo = Restaurante(0,Nome_R_Camp.text.toString(), Cliente, "Bebidas")
            return novo
        } else if (Tipo_Padaria.isChecked) {
            var novo = Restaurante(0,Nome_R_Camp.text.toString(), Cliente, "Padaria")
            return novo
        } else if (Tipo_hortifruti.isChecked) {
            var novo = Restaurante(0,Nome_R_Camp.text.toString(), Cliente, "Hortifruti")
            return novo
        } else {
            var novo = Restaurante(0,Nome_R_Camp.text.toString(), Cliente, "Fastfood")
            return novo
        }
    }
}