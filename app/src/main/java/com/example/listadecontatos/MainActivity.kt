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
import com.example.listadecontatos.ContatoAplication
import kotlinx.android.synthetic.main.activity_contato.*

class MainActivity : BaseActivity() {

    private var adapter: ContatoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolBar("Lista de Contatos", false)
        setupListView()
        setupOnClicks()
    }

    private fun setupListView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onPostResume() {
        super.onPostResume()
        buscar()
    }

    private fun setupOnClicks() {
        botaoBuscar.setOnClickListener { buscar() }
        botaoAdd.setOnClickListener { adicionar() }
    }

    fun buscar(){
        var stringBusca = caixaTextoBusca.text.toString()
        var listaFiltrada: List<Contato> = mutableListOf()

        progressBarMain.visibility = View.VISIBLE
        Thread( Runnable {
            Thread.sleep(1000)
            try {
                listaFiltrada = ContatoAplication.instance.helperDB?.buscarContato(stringBusca, false) ?: mutableListOf()
            }catch (ex: Exception){
                ex.printStackTrace()
            }

            runOnUiThread {
                adapter = ContatoAdapter(this, listaFiltrada.sortedBy { it.nome.toLowerCase() }) { onClickItemRecyclerView(it)}
                recyclerView.adapter = adapter
                Toast.makeText(this,"${listaFiltrada.size} resultados encontrados",Toast.LENGTH_SHORT).show()
                progressBarMain.visibility = View.GONE
            }
        }).start()
    }

    fun adicionar(){
        val intent = Intent(this, ContatoActivity::class.java)
        startActivity(intent)
    }

    fun onClickItemRecyclerView(index: Int){
        val intent = Intent(this, ContatoActivity::class.java) // implementar
        intent.putExtra("index", index)
        startActivity(intent)
    }
}
