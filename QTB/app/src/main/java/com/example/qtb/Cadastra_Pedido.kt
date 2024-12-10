package com.example.qtb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cadastra_Pedido : AppCompatActivity() {
    lateinit var Cliente:String
    lateinit var Id_Produto:String
    lateinit var Nome_P:String
    lateinit var Valor:String
    lateinit var Bt_Back: Button
    lateinit var Produto_Exp: TextView
    lateinit var editTextNumber: EditText
    lateinit var Bt_Finalizar_Pedido: Button
    lateinit var DAO: DAO_Pedidos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_cadastra_pedido)
        Bt_Finalizar_Pedido = findViewById(R.id.Bt_Finalizar_Pedido)
        Bt_Back = findViewById(R.id.Bt_Back)
        editTextNumber = findViewById(R.id.editTextNumber)
        Produto_Exp = findViewById(R.id.Produto_Exp)
        DAO = DAO_Pedidos(this)
        Cliente = intent.getStringExtra("Clente").toString()
        Nome_P = intent.getStringExtra("Nome_P").toString()
        Valor = intent.getStringExtra("Valor").toString()
        Id_Produto= intent.getStringExtra("Id_produto").toString()
        Bt_Back.setOnClickListener {
            Gotohome()
        }
        Bt_Finalizar_Pedido.setOnClickListener {
            if(Valida_pedido()){
                //var Novo = Pedido(0,Id_Produto.toInt(),Cliente, editTextNumber.text.toString().toInt())
                //Toast.makeText(this, Id_Produto, Toast.LENGTH_SHORT).show()
                //DAO.Adicionar(Novo)
            }
        }
        Produto_Exp.text = Nome_P + "\n" + "Preço unitario: "+Valor+"\n"+"entrega para : "+Cliente
    }
    fun Valida_pedido():Boolean{
        if (editTextNumber.text.toString() == "")
        {
            Toast.makeText(this, "preencha uma quantidade", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }
    fun Gotohome(){
        finish()
    }
}