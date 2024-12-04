package com.example.qtb

import android.content.ContentValues
import android.content.Context
import android.icu.lang.UCharacter.NumericType
import android.provider.ContactsContract.CommonDataKinds.Email

class DAO_Usuarios(contexto : Context) {
    val banco : Banco = Banco(contexto)

    fun Adicionar(Novo : Usuario){
        val Escritora = banco.writableDatabase
        val valores = ContentValues().apply {
            put("CPF", Novo.CPF)
            put("Nome", Novo.Nome)
            put("Senha", Novo.Senha)
            put("Email",Novo.Email)
            put("Bairro",Novo.Bairro)
            put("Cidade",Novo.Cidade)
            put("Endereco",Novo.Endereco)
            put("Numero", Novo.Numero )
            put("Telefone",Novo.Telefone)
            put("Num",1)
        }
        Escritora.insert("TabUsuarios",null,valores)
        Escritora.close()
    }

    fun Pesquisa(Usuarios: ArrayList<Usuario>, Email_buscado : String)
    {
        try {
            val Leitora = banco.readableDatabase
            val seletora = "SELECT * FROM TabUsuarios"
            val cursor = Leitora.rawQuery(seletora, null)

            cursor.use {
                while (cursor.moveToNext()) {
                    val CPF = cursor.getString(cursor.getColumnIndexOrThrow("CPF"))
                    val Nome = cursor.getString(cursor.getColumnIndexOrThrow("Nome"))
                    val Senha = cursor.getString(cursor.getColumnIndexOrThrow("Senha"))
                    val Email = cursor.getString(cursor.getColumnIndexOrThrow("Email"))
                    val Bairro = cursor.getString(cursor.getColumnIndexOrThrow("Bairro"))
                    val Cidade = cursor.getString(cursor.getColumnIndexOrThrow("Cidade"))
                    val Endereco = cursor.getString(cursor.getColumnIndexOrThrow("Endereco"))
                    val Numero = cursor.getInt(cursor.getColumnIndexOrThrow("Numero"))
                    val Telefone = cursor.getString(cursor.getColumnIndexOrThrow("Telefone"))
                    val Num = cursor.getInt(cursor.getColumnIndexOrThrow("Num"))
                    val Usuario_visitado = Usuario(CPF, Nome, Senha, Email, Bairro, Cidade, Endereco, Numero, Telefone)
                    if (Usuario_visitado.Email == Email_buscado) {
                        Usuarios.add(Usuario_visitado)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}