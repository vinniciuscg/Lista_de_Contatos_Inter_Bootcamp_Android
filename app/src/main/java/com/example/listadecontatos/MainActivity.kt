package com.example.listadecontatos

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var adapter: ContatoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.GONE

        geraListaDeContatos()
        setupToolBar("Lista de Contatos", false)
        setupListView()
        setupOnClicks()
    }

    private fun geraListaDeContatos(){
        ContatoSingleton.lista.add(Contato(1, "Vine", "9999-9999"))
        ContatoSingleton.lista.add(Contato(2, "Marcos", "8888-9999"))
        ContatoSingleton.lista.add(Contato(2, "Gomes", "7777-9999"))
    }

    private fun setupListView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContatoAdapter(this, ContatoSingleton.lista){ onClickItemRecyclerView(it) }
        recyclerView.adapter = adapter
    }

    private fun setupOnClicks() {
        botaoBuscar.setOnClickListener { buscar() }
        botaoAdd.setOnClickListener { adicionar() }
    }

    fun buscar(){
        var stringBusca = caixaTextoBusca.text.toString()
        var listaFiltrada: List<Contato> = ContatoSingleton.lista

        if(!stringBusca.isNullOrEmpty()){
            listaFiltrada = ContatoSingleton.lista.filter { contato ->
                if (contato.nome.toLowerCase().contains(stringBusca.toLowerCase())){
                    return@filter true
                }
                return@filter false
            }
        }

        adapter = ContatoAdapter(this, listaFiltrada) { onClickItemRecyclerView(it)}
        recyclerView.adapter = adapter
        Toast.makeText(this,"Buscando por $stringBusca",Toast.LENGTH_SHORT).show()
    }

    fun adicionar(){}

    fun onClickItemRecyclerView(index: Int){
        val intent = Intent(this, ContatoActivity::class.java) // implementar
        intent.putExtra("index", index)
        startActivity(intent)
    }
}
