package com.example.user.alejandrofinal

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class AdapterAlbum(var list: ArrayList<Album>):RecyclerView.Adapter<AdapterAlbum.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        //guardamos vista que vamos a inglar
        val v=LayoutInflater.from(parent?.context).inflate(R.layout.content_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //llamamos la funcion que creamos en view holder que seria bindItem
        holder.bindItem(list[position])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItem(data:Album){
            val title:TextView=itemView.findViewById(R.id.txtTitle)
            val count:TextView=itemView.findViewById(R.id.txtCount)
            val thumbnail:ImageView=itemView.findViewById(R.id.thumbnail)

            title.text=data.name
            count.text=data.descripcion

            Glide.with(itemView.context).load(data.thumbail).into(thumbnail)

            itemView.setOnClickListener({
                Toast.makeText(itemView.context,"Album de ${data.name}", Toast.LENGTH_LONG).show()
            })
        }
    }
}