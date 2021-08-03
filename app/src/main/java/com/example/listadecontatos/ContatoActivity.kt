package com.example.listadecontatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_contato.*
import kotlinx.android.synthetic.main.activity_main.*

class ContatoActivity : BaseActivity() {

    private var index: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)

        setupToolBar("Contato", true)
        setupContato()
        setListeners()
    }

    fun setupContato(){

        var index = intent.getIntExtra("index", -1)
        if(index == -1){ // Novo Contato - Criando Contato
            btnExcluir.visibility = View.GONE
            return
        }
        txNome.setText(ContatoSingleton.lista[index].nome)
        txTelefone.setText(ContatoSingleton.lista[index].telefone)
    }

    private fun setListeners(){
        btnSalvar.setOnClickListener { salvarContato() }
        btnExcluir.setOnClickListener { excluiContato() }
    }

    private fun salvarContato(){
        var nome = txNome.text.toString()
        var telefone = txTelefone.text.toString()
        val contato = Contato(ContatoSingleton.lista.size, nome, telefone)

        if (index == -1){ // Novo Contato
            ContatoSingleton.lista.add(contato)
        }else{
            ContatoSingleton.lista.add(index, contato)
        }
        finish()
    }

    private fun excluiContato(){
        if (index > -1){
            ContatoSingleton.lista.removeAt(index)
            finish()
        }
    }
}