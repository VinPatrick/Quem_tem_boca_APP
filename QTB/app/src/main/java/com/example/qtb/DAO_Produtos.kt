package com.example.qtb

import android.content.ContentValues
import android.content.Context
import android.widget.Toast

class DAO_Produtos(contexto : Context){
    val banco : Banco = Banco(contexto)

    fun Adicionar(Novo: Produto) {
        val Escritora = banco.writableDatabase
        val valores = ContentValues().apply {
            put("Id_Loja", Novo.Id_Loja)
            put("Nome_P", Novo.Nome)
            put("Tipo", Novo.Tipo)
            put("Valor", Novo.Valor.toString())
        }
        val resultado = Escritora.insert("TabProdutos", null, valores)
        if (resultado == -1L) {
            println("Erro ao inserir o produto no banco de dados")
        } else {
            println("Produto inserido com sucesso: $Novo")
        }
        Escritora.close()
    }

    fun Pesquisa(Produtos: ArrayList<Produto>, Id_Loja_R: String) {
        try {
            val Leitora = banco.readableDatabase
            val seletora = "SELECT * FROM TabProdutos"
            val cursor = Leitora.rawQuery(seletora, null)

            cursor.use {
                while (cursor.moveToNext()) {
                    val Id_Produtos = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Produtos"))
                    val Id_Loja = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Loja"))
                    val Nome_P = cursor.getString(cursor.getColumnIndexOrThrow("Nome_P"))
                    val Tipo = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))
                    val Valor = cursor.getString(cursor.getColumnIndexOrThrow("Valor"))
                    val Produto_visitado = Produto(Id_Produtos, Id_Loja, Nome_P, Tipo, Valor.toFloat())
                    println("Produto encontrado: $Produto_visitado")

                    if (Id_Loja_R.isNotBlank() && Produto_visitado.Id_Loja == Id_Loja_R.toInt()) {
                        Produtos.add(Produto_visitado)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun Pesquisa_N(Produtos: ArrayList<Produto>, Nome: String) {
        try {
            val Leitora = banco.readableDatabase
            val seletora = "SELECT * FROM TabProdutos"
            val cursor = Leitora.rawQuery(seletora, null)

            cursor.use {
                while (cursor.moveToNext()) {
                    val Id_Produtos = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Produtos"))
                    val Id_Loja = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Loja"))
                    val Nome_P = cursor.getString(cursor.getColumnIndexOrThrow("Nome_P"))
                    val Tipo = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))
                    val Valor = cursor.getString(cursor.getColumnIndexOrThrow("Valor"))
                    val Produto_visitado = Produto(Id_Produtos, Id_Loja, Nome_P, Tipo, Valor.toFloat())

                    if (Nome_P == Nome) {
                        Produtos.add(Produto_visitado)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun Receber(Produtos: ArrayList<Produto>) {
        try {
            val Leitora = banco.readableDatabase
            val seletora = "SELECT * FROM TabProdutos"
            val cursor = Leitora.rawQuery(seletora, null)

            cursor.use {
                while (cursor.moveToNext()) {
                    val Id_Produtos = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Produtos"))
                    val Id_Loja = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Loja"))
                    val Nome_P = cursor.getString(cursor.getColumnIndexOrThrow("Nome_P"))
                    val Tipo = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))
                    val Valor = cursor.getString(cursor.getColumnIndexOrThrow("Valor"))
                    val Produto_visitado = Produto(Id_Produtos, Id_Loja, Nome_P, Tipo, Valor.toFloat())
                    if (Produto_visitado != null) {
                        Produtos.add(Produto_visitado)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun Remover(Id_Produto: Int) {
        try {
            val db = banco.writableDatabase
            val selection = "Id_Produto = ?"
            val selectionArgs = arrayOf(Id_Produto.toString())
            db.delete("TabProdutos", selection, selectionArgs)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    /*
        "$Id_Produtos INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "$Id_Loja INTEGER," +
                    "$Nome_P TEXT," +
                    "$Tipo TEXT," +
                    "$Valor FLOAT, " +
                    "FOREIGN KEY(Id_Loja) REFERENCES TabRestaurantes(Id_Loja))"
         */
    fun update(Novo :Produto ,Id_Produto: Int) {
        try {
            val db = banco.writableDatabase
            val selection = "Id_Produto = ?"
            val selectionArgs = arrayOf(Id_Produto.toString())
            val valores = ContentValues().apply {
                put("Nome_P", Novo.Nome)
                put("Tipo", Novo.Tipo)
                put("Valor", Novo.Valor.toString())
            }
            db.update("TabProdutos",valores,selection,selectionArgs)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}