package com.example.qtb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Capa_Pg : AppCompatActivity() {
    lateinit var Bt_goto_login : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visual_capa_pg)
        Bt_goto_login = findViewById(R.id.Bt_goto_login)
        Bt_goto_login.setOnClickListener{
            Goto_login_Pg()
        }
    }
    fun Goto_login_Pg(){
        val goto_login = Intent(this,Login_Pg::class.java)
        startActivity(goto_login)
        finish()
    }
}