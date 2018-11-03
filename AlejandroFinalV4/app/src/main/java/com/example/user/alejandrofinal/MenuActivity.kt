package com.example.user.alejandrofinal

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView

import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setSupportActionBar(toolbar)

        val GV=this.findViewById(R.id.GV) as GridView
        val adapter= custom_adapter(this, R.layout.customlayout, data )

        GV.adapter= adapter

        fab.setOnClickListener { view ->
            val intento1 = Intent(this, HomeActivity::class.java)
            startActivity(intento1)
        }
    }

    val data: ArrayList<customlayout>
    get() {
        val item_liste: ArrayList<customlayout> = ArrayList<customlayout>()
        item_liste.add(customlayout(R.drawable.menu1,"Paquete Mix"))
        item_liste.add(customlayout(R.drawable.menu2,"Paquete 10 Piezas"))
        item_liste.add(customlayout(R.drawable.menu3,"Paquete 8 Piezas"))
        item_liste.add(customlayout(R.drawable.menu4,"Paquete trío"))
        item_liste.add(customlayout(R.drawable.menu5,"Megabox"))
        item_liste.add(customlayout(R.drawable.menu6,"Kebox"))
        item_liste.add(customlayout(R.drawable.menu7,"Pop corn"))
        item_liste.add(customlayout(R.drawable.menu8,"Chizza"))

        return item_liste

    }

}
