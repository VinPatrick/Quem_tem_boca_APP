package com.example.qtb

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pedidos_Pg : AppCompatActivity() {
    lateinit var Bt_Voltar_home_Pg:Button
    lateinit var DAO:DAO_Pedidos
    lateinit var Lista_pedidos : ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_pedidos_pg)
        DAO = DAO_Pedidos(this)
        Lista_pedidos = findViewById(R.id.Lista_pedidos)
        Bt_Voltar_home_Pg = findViewById(R.id.Bt_Voltar_home_Pg)
        Bt_Voltar_home_Pg.setOnClickListener {
            Goto_Home_Pg()
        }
        var List = ArrayList<Pedido>()
        DAO.Pesquisa(List)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,Roda(List))
        Lista_pedidos.adapter = adaptador
    }
    fun Goto_Home_Pg(){
        finish()
    }
    fun Roda(ListaObjeto : ArrayList<Pedido>) : ArrayList<String>{
        var ListaT = ArrayList<String>()
        for (item in ListaObjeto)
        {
            ListaT.add(item.Id_Produtos.toString()+" : "+ item.Quantidade.toString())
        }
        return ListaT
    }
}