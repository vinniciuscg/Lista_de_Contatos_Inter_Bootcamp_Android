package com.example.listadecontatos

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_contato.*
import com.example.listadecontatos.ContatoAplication

class ContatoActivity : BaseActivity() {

    private var idContato: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)

        setupToolBar("Contato", true)
        setupContato()
        setListeners()
    }

    fun setupContato(){

        idContato = intent.getIntExtra("index", -1)
        if(idContato == -1){ // Novo Contato - Criando Contato
            btnExcluir.visibility = View.GONE
            return
        }
        var lista = ContatoAplication.instance.helperDB?.buscarContato("$idContato", true) ?: return
        var contato = lista.getOrNull(0) ?: return
        txNome.setText(contato.nome)
        txTelefone.setText(contato.telefone)
    }

    private fun setListeners(){
        btnSalvar.setOnClickListener { salvarContato() }
        btnExcluir.setOnClickListener { excluiContato() }
    }

    private fun salvarContato(){
        var nome = txNome.text.toString()
        var telefone = txTelefone.text.toString()
        val contato = Contato(idContato, nome, telefone)

        if (idContato == -1){ // Novo Contato
            ContatoAplication.instance.helperDB?.salvarContato(contato)
        }else{
            ContatoAplication.instance.helperDB?.updateContato(contato)
        }
        finish()
    }

    private fun excluiContato(){
        if (idContato > -1){
            ContatoAplication.instance.helperDB?.deletarContato(idContato)
            finish()
        }
    }
}