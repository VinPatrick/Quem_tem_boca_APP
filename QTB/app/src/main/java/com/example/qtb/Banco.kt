package com.example.qtb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Banco(contexto : Context) : SQLiteOpenHelper(contexto,"SuperBanco",null,1){
    /*
    init {
        contexto.deleteDatabase("SuperBanco")// descomente esta linha para apagar o banco de dados completamente
    }
    */
    //criaçao do banco
    override fun onCreate(db: SQLiteDatabase?) {
        //nomeando tabelas
        val nomt1 = "TabUsuarios"
        val nomt2 = "TabRestaurantes"
        val nomt3 = "TabProdutos"
        val nomt4 = "TabPedidos"

        val CPF = "CPF"
        val Nome = "Nome"
        val Senha = "Senha"
        val Endereco = "Endereco"
        val Numero = "Numero"
        val Email = "Email"
        val Cidade = "Cidade"
        val Bairro = "Bairro"
        val Telefone = "Telefone"
        val Num = "Num"
        val Id_Loja = "Id_Loja"
        val Dono = "Dono"
        val Tipo = "Tipo"
        val Id_Produtos = "Id_Produtos"
        val Id_Pedidos = "Id_Pedidos"
        val Nome_R = "Nome_R"
        val Nome_P = "Nome_P"
        val Valor = "Valor"
        val Comprador = "Comprador"
        val Quantidade = "Quantidade"

        //criando tabelas
        //tabela de usuarios
        val Criando_TabUsuarios =
                    "CREATE TABLE $nomt1 (" +
                    "$CPF TEXT PRIMARY KEY NOT NULL," +
                    "$Nome TEXT," +
                    "$Senha TEXT," +
                    "$Email TEXT," +
                    "$Bairro TEXT," +
                    "$Cidade TEXT," +
                    "$Endereco TEXT," +
                    "$Numero INTEGER,"+
                    "$Telefone TEXT,"+
                    "$Num INTEGER)"
        if (db != null) {
            db.execSQL(Criando_TabUsuarios)
        }
        //tabela de restaurantes
        val Criando_TabRestaurantes =
            "CREATE TABLE $nomt2 (" +
                    "$Id_Loja INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "$Nome_R TEXT," +
                    "$Tipo TEXT," +
                    "$Dono TEXT, FOREIGN KEY(Dono) REFERENCES TabUsuarios(CPF))"
        if (db != null) {
            db.execSQL(Criando_TabRestaurantes)
        }
        //tabela de produdos
        val Criando_TabProdutos =
            "CREATE TABLE $nomt3 (" +
                    "$Id_Produtos INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "$Id_Loja INTEGER," +
                    "$Nome_P TEXT," +
                    "$Tipo TEXT," +
                    "$Valor TEXT, " +
                    "FOREIGN KEY(Id_Loja) REFERENCES TabRestaurantes(Id_Loja))"
        if (db != null) {
            db.execSQL(Criando_TabProdutos)
        }
        //tabela pedidos
        val Criando_TabPedidos =
            "CREATE TABLE $nomt4 (" +
                    "$Id_Pedidos INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "$Id_Produtos INTEGER,"+
                    "$Comprador TEXT," +
                    "$Quantidade INTEGER," +
                    "FOREIGN KEY(Comprador) REFERENCES TabUsuarios(CPF))"
        if (db != null) {
            db.execSQL(Criando_TabPedidos)
        }
    }
    //atualizando Banco
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            val SQL_exclusao = "DROP TABLE IF EXISTS TabUsuarios"
            db.execSQL(SQL_exclusao)
            onCreate(db)

            val SQL_exclusao2 = "DROP TABLE IF EXISTS TabRestaurantes"
            db.execSQL(SQL_exclusao2)
            onCreate(db)

            val SQL_exclusao3 = "DROP TABLE IF EXISTS TabProdutos"
            db.execSQL(SQL_exclusao3)
            onCreate(db)

            val SQL_exclusao4 = "DROP TABLE IF EXISTS TabPedidos"
            db.execSQL(SQL_exclusao4)
            onCreate(db)
        }
    }
}