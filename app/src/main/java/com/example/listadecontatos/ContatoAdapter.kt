package com.example.listadecontatos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_contato.view.*

class ContatoAdapter (
    val context: Context,
    val lista: List<Contato>,
    val onClick: ((Int) -> Unit)
):  RecyclerView.Adapter<ContatoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_contato, parent, false)
        return ContatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = lista[position]
        with(holder.itemView){
            tvNome.text = contato.nome
            tvTelefone.text = contato.telefone
            itemctt.setOnClickListener{ onClick(position) }
        }
    }

    override fun getItemCount(): Int = lista.size
}

class ContatoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)