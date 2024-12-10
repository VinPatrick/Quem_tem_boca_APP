package com.example.qtb

import android.content.ContentValues
import android.content.Context

class DAO_Restaurantes(contexto : Context){
    val banco : Banco = Banco(contexto)

    fun Adicionar(Novo : Restaurante){
        val Escritora = banco.writableDatabase
        val valores = ContentValues().apply {
            put("Dono", Novo.Dono)
            put("Nome_R", Novo.Nome_R)
            put("Tipo", Novo.Tipo)
        }
        Escritora.insert("TabRestaurantes",null,valores)
        Escritora.close()
    }

    fun Pesquisa(Restautantes: ArrayList<Restaurante>, Dono_P : String)
    {
        try {
            val Leitora = banco.readableDatabase
            val seletora = "SELECT * FROM TabRestaurantes"
            val cursor = Leitora.rawQuery(seletora, null)

            cursor.use {
                while (cursor.moveToNext()) {
                    val Dono = cursor.getString(cursor.getColumnIndexOrThrow("Dono"))
                    val Nome_R = cursor.getString(cursor.getColumnIndexOrThrow("Nome_R"))
                    val Tipo = cursor.getString(cursor.getColumnIndexOrThrow("Tipo"))
                    val Id_Loja = cursor.getInt(cursor.getColumnIndexOrThrow("Id_Loja"))
                    val Restaurante_visitado = Restaurante(Id_Loja, Nome_R, Dono, Tipo)
                    if (Restaurante_visitado.Dono == Dono_P) {
                        Restautantes.add(Restaurante_visitado)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun Remover(Dono: String) {
        try {
            val db = banco.writableDatabase
            val selection = "Dono = ?"
            val selectionArgs = arrayOf(Dono.toString())
            db.delete("TabRestaurantes", selection, selectionArgs)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun update(Novo :Restaurante ,Dono: String) {
        try {
            val db = banco.writableDatabase
            val selection = "Dono = ?"
            val selectionArgs = arrayOf(Dono.toString())
            val valores = ContentValues().apply {
                put("Nome_R", Novo.Nome_R)
                put("Tipo", Novo.Tipo)
            }
            db.update("TabRestaurantes",valores,selection,selectionArgs)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}