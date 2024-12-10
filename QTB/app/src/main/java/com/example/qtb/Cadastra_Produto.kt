package com.example.qtb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Cadastra_Produto : AppCompatActivity() {
    lateinit var Id_Loja: String
    lateinit var Tipo: String
    lateinit var Cliente:String
    lateinit var nome : String

    lateinit var Nome_Produto_Camp: EditText
    lateinit var Valor_Produto_Camp: EditText
    lateinit var Bt_Cadastrar_Produto: Button
    lateinit var Bt_Back_Pg: Button
    lateinit var DAO: DAO_Produtos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_cadastra_produto)
        // Recebendo id da loja
        Id_Loja = intent.getStringExtra("Id_Loja").toString()
        Tipo = intent.getStringExtra("Tipo").toString()

        Cliente = intent.getStringExtra("ID_Dono").toString()
        nome = intent.getStringExtra("Nome_R").toString()
        Tipo = intent.getStringExtra("Tipo").toString()

        //passando context para o DAO
        DAO=DAO_Produtos(this)
        // linkando variaveis aos seus visuais
        Nome_Produto_Camp = findViewById(R.id.Nome_Produto_Camp)
        Valor_Produto_Camp = findViewById(R.id.Valor_Produto_Camp)
        Bt_Cadastrar_Produto = findViewById(R.id.Bt_Cadastrar_Produto)
        Bt_Back_Pg = findViewById(R.id.Bt_Back_Pg)
        //dando funçoes aos botoes
        Bt_Cadastrar_Produto.setOnClickListener {
            if (valida_Produto()){
                var novo = Produto(0, Id_Loja.toInt(),Nome_Produto_Camp.text.toString(),Tipo,Valor_Produto_Camp.text.toString().toFloat())
                DAO.Adicionar(novo)
                Goto_Back_Pg()
            }
        }
        Bt_Back_Pg.setOnClickListener {
            Goto_Back_Pg()
        }
    }
    //funçoes
    fun Goto_Back_Pg(){
        val goto_Restaurante=Intent(this,Restaurante_Pg::class.java)
        goto_Restaurante.putExtra("ID_Dono", Cliente.toString())
        goto_Restaurante.putExtra("Nome_R", nome.toString())
        goto_Restaurante.putExtra("Tipo", Tipo.toString())
        goto_Restaurante.putExtra("ID_Loja", Id_Loja.toString())
        startActivity(goto_Restaurante)
        finish()
    }
    fun valida_Produto(): Boolean{
        if (Nome_Produto_Camp.text.toString() == "")
        {
            Toast.makeText(this, "De um nome ao produto", Toast.LENGTH_SHORT).show()
            return false
        }else if (Valor_Produto_Camp.text.toString() == ""){
            Toast.makeText(this, "De um valor ao produto", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }

    }
}