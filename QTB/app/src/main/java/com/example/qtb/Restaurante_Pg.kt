package com.example.qtb

import SimpleAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Restaurante_Pg : AppCompatActivity() {
    lateinit var Cliente:String
    lateinit var nome:String
    lateinit var tipo:String
    lateinit var Id_Loja: String
    lateinit var Nome_Restaurante_label : TextView
    lateinit var Bt_Pedidos_Restaurante : Button
    lateinit var Bt_volta_home_restaurante : Button
    lateinit var Bt_adicionar_produto : Button
    lateinit var RecyclerView:RecyclerView
    lateinit var DAO: DAO_Produtos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_restaurante_pg)
        DAO = DAO_Produtos(this)
        //Recebendo informaçoes do cliente
        Cliente = intent.getStringExtra("ID_Dono").toString()
        nome = intent.getStringExtra("Nome_R").toString()
        tipo = intent.getStringExtra("Tipo").toString()
        Id_Loja = intent.getStringExtra("ID_Loja").toString()

        //linkando visual com variaveis no app
        Nome_Restaurante_label = findViewById(R.id.Nome_Restaurante_label)
        Bt_Pedidos_Restaurante = findViewById(R.id.Bt_Pedidos_Restaurante)
        Bt_volta_home_restaurante = findViewById(R.id.Bt_volta_home_restaurante)
        Bt_adicionar_produto = findViewById(R.id.Bt_adicionar_produto)
        RecyclerView = findViewById(R.id.RecyclerView)
        //inicializando a tela
        Nome_Restaurante_label.text = nome
        //Atribuindo funçoes aos botões
        Bt_volta_home_restaurante.setOnClickListener {
            Goto_Home_Pg()
        }
        Bt_Pedidos_Restaurante.setOnClickListener {
        }
        Bt_adicionar_produto.setOnClickListener {
            Goto_Cadastra_produto()
        }
        var Lista_de_Produtos = ArrayList<Produto>()
        DAO.Pesquisa(Lista_de_Produtos,Id_Loja)
        var Lista = strigficar(Lista_de_Produtos)
        setupRecyclerView(RecyclerView,Lista)

    }
    //funções
    fun Goto_Home_Pg(){
        val goto_home = Intent(this,Home_Pg::class.java)
        goto_home.putExtra("ID_Cliente", Cliente)
        startActivity(goto_home)
        finish()
    }
    fun Goto_Cadastra_produto(){
        val goto_cadProd = Intent(this,Cadastra_Produto::class.java)
        goto_cadProd.putExtra("Id_Loja",Id_Loja)
        goto_cadProd.putExtra("Tipo",tipo)
        goto_cadProd.putExtra("ID_Dono",Cliente)
        goto_cadProd.putExtra("Nome_R",nome)
        startActivity(goto_cadProd)
        finish()
    }
    fun setupRecyclerView(
        recyclerView: RecyclerView,
        items: ArrayList<String>
    ) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = SimpleAdapter(items) { item, position ->
            // Adicionando o clique na lista e mostrando o índice do item
            Toast.makeText(this, "Item clicado: ${item} na posição ${position}", Toast.LENGTH_SHORT).show()
        }
    }


    fun strigficar(Lista: ArrayList<Produto>): ArrayList<String> {
        val nova = ArrayList<String>()
        for (produto in Lista) {
            nova.add(produto.Nome + "\n" +"R$" +produto.Valor)
        }
        return nova
    }

}