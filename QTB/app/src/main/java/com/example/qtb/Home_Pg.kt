package com.example.qtb

import SimpleAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home_Pg : AppCompatActivity() {
    //declarando variaveis
    lateinit var Produtos_homes_list: RecyclerView
    lateinit var Bt_Restaurante: Button
    lateinit var Bt_Pedidos: Button
    lateinit var Bt_Config: Button
    lateinit var Pesquisa_camp: EditText
    lateinit var DAO: DAO_Restaurantes
    lateinit var DAO2: DAO_Produtos
    lateinit var Cliente: String
    lateinit var Lista_de_Produtos: ArrayList<Produto>
    //iniciando tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_home_pg)
        // passando contexto dao
        DAO = DAO_Restaurantes(this)
        DAO2 = DAO_Produtos(this)
        //Recebendo cliente
        Cliente = intent.getStringExtra("ID_Cliente").toString()
        //Relacionando botoes aos endereços xml
        Bt_Restaurante = findViewById(R.id.Bt_Restaurante)
        Bt_Pedidos = findViewById(R.id.Bt_Pedidos)
        Bt_Config = findViewById(R.id.Bt_Config)
        Pesquisa_camp = findViewById(R.id.Pesquisa_camp)
        Produtos_homes_list = findViewById(R.id.Produtos_homes_list)
        //Dando funçoes aos botoes
        Bt_Restaurante.setOnClickListener {
            verifica_restaurante()
        }
        Bt_Pedidos.setOnClickListener {
            Goto_Pedidos_Pg()
        }
        Bt_Config.setOnClickListener {
        }
        Pesquisa_camp.setOnClickListener{
            if(Pesquisa_camp.text.toString() == ""){
                Lista_de_Produtos = ArrayList<Produto>()
                DAO2.Receber(Lista_de_Produtos)
                var Lista = strigficar(Lista_de_Produtos)
                setupRecyclerView(Produtos_homes_list,Lista)
            }else{
                Lista_de_Produtos = ArrayList<Produto>()
                DAO2.Pesquisa_N(Lista_de_Produtos,Pesquisa_camp.text.toString())
                var Lista = strigficar(Lista_de_Produtos)
                setupRecyclerView(Produtos_homes_list,Lista)
            }
        }
        Lista_de_Produtos = ArrayList<Produto>()
        DAO2.Receber(Lista_de_Produtos)
        var Lista = strigficar(Lista_de_Produtos)
        setupRecyclerView(Produtos_homes_list,Lista)

    }
    // Funções
    //verificando se o restaurante existe ou se é nescessario criar um
    fun verifica_restaurante(){
        var rest = ArrayList<Restaurante>()
        DAO.Pesquisa(rest , Cliente)
        if(rest.size >= 1){
            Goto_Restaurante_Pg(rest.get(0))
        }else{
            Goto_cadastro_Pg()
        }
    }
    fun Goto_Pedidos_Pg(){
        val goto_Pedidos = Intent(this,Pedidos_Pg::class.java)
        //avisando para a proxima pagina qual o tipo de busca por pedido ela deve utilizar (por dono ou por restalrante)
        goto_Pedidos.putExtra("Cliente", Cliente)
        startActivity(goto_Pedidos)
    }
    fun Goto_Restaurante_Pg(rest:Restaurante){
        val goto_Restaurante=Intent(this,Restaurante_Pg::class.java)
        goto_Restaurante.putExtra("ID_Dono", rest.Dono.toString())
        goto_Restaurante.putExtra("Nome_R", rest.Nome_R.toString())
        goto_Restaurante.putExtra("Tipo", rest.Tipo.toString())
        goto_Restaurante.putExtra("ID_Loja", rest.Id.toString())
        startActivity(goto_Restaurante)
        finish()
    }
    //Este goto_cadastro transfere para a pagina de cadastro de restaurante nao de usuarios nome usado apenas é parecido
    fun Goto_cadastro_Pg(){
        val goto_cadastro = Intent(this,Cad_Restaurante::class.java)
        goto_cadastro.putExtra("Cliente", Cliente)
        startActivity(goto_cadastro)
        finish()
    }
    fun setupRecyclerView(
        recyclerView: RecyclerView,
        items: ArrayList<String>
    ) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = SimpleAdapter(items) { item, position ->
            // Adicionando o clique na lista e mostrando o índice do item
            pedir_iten(position)
        }
    }

    fun strigficar(Lista: ArrayList<Produto>): ArrayList<String> {
        val nova = ArrayList<String>()
        for (produto in Lista) {
            nova.add(produto.Nome + "\n" +"R$" +produto.Valor)
        }
        return nova
    }
    fun pedir_iten(indice:Int){
        val goto_Pedidos = Intent(this,Cadastra_Pedido::class.java)
        //avisando para a proxima pagina qual o tipo de busca por pedido ela deve utilizar (por dono ou por restalrante)
        goto_Pedidos.putExtra("Id_produto", Lista_de_Produtos.get(indice).Id)
        goto_Pedidos.putExtra("Cliente", Cliente)
        goto_Pedidos.putExtra("Nome_P", Lista_de_Produtos.get(indice).Nome)
        goto_Pedidos.putExtra("Valor", Lista_de_Produtos.get(indice).Valor)
        startActivity(goto_Pedidos)
    }
}