package com.caio.caiocardozo.partidabasquete_kotlin

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0

    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView
    private lateinit var nomeTimeA: TextView
    private lateinit var nomeTimeB: TextView
    private lateinit var layoutTimeA: LinearLayout
    private lateinit var layoutTimeB: LinearLayout
    private lateinit var bTresPontosTimeA: Button
    private lateinit var bDoisPontosTimeA: Button
    private lateinit var bTLivreTimeA: Button
    private lateinit var bTresPontosTimeB: Button
    private lateinit var bDoisPontosTimeB: Button
    private lateinit var bTLivreTimeB: Button

    private var corPlacarA: Int = 0
    private var corPlacarB: Int = 0
    private var corDestaqueA: Int = 0
    private var corDestaqueB: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pTimeA           = findViewById(R.id.placarTimeA)
        pTimeB           = findViewById(R.id.placarTimeB)
        nomeTimeA        = findViewById(R.id.timeA)
        nomeTimeB        = findViewById(R.id.timeB)
        layoutTimeA      = findViewById(R.id.layoutTimeA)
        layoutTimeB      = findViewById(R.id.layoutTimeB)
        bTresPontosTimeA = findViewById(R.id.tresPontosA)
        bDoisPontosTimeA = findViewById(R.id.doisPontosA)
        bTLivreTimeA     = findViewById(R.id.tiroLivreA)
        bTresPontosTimeB = findViewById(R.id.tresPontosB)
        bDoisPontosTimeB = findViewById(R.id.doisPontosB)
        bTLivreTimeB     = findViewById(R.id.tiroLivreB)

        val bReiniciar: Button = findViewById(R.id.reiniciarPartida)
        val bTema: Button      = findViewById(R.id.selecionarTema)

        bTresPontosTimeA.setOnClickListener { adicionarPontos(3, "A") }
        bDoisPontosTimeA.setOnClickListener { adicionarPontos(2, "A") }
        bTLivreTimeA.setOnClickListener     { adicionarPontos(1, "A") }
        bTresPontosTimeB.setOnClickListener { adicionarPontos(3, "B") }
        bDoisPontosTimeB.setOnClickListener { adicionarPontos(2, "B") }
        bTLivreTimeB.setOnClickListener     { adicionarPontos(1, "B") }
        bReiniciar.setOnClickListener       { reiniciarPartida() }
        bTema.setOnClickListener            { mostrarSeletorTema() }

        nomeTimeA.setOnClickListener { renomearTime("A") }
        nomeTimeB.setOnClickListener { renomearTime("B") }

        aplicarTemaLakers()
    }

    fun adicionarPontos(pontos: Int, time: String) {
        if (time == "A") {
            pontuacaoTimeA += pontos
        } else {
            pontuacaoTimeB += pontos
        }
        atualizarPlacar(time)
    }

    fun atualizarPlacar(time: String) {
        if (time == "A") {
            pTimeA.text = pontuacaoTimeA.toString()
        } else {
            pTimeB.text = pontuacaoTimeB.toString()
        }

        if (pontuacaoTimeA > pontuacaoTimeB) {
            pTimeA.setTextColor(corDestaqueA)
            pTimeB.setTextColor(corPlacarB)
        } else if (pontuacaoTimeB > pontuacaoTimeA) {
            pTimeB.setTextColor(corDestaqueB)
            pTimeA.setTextColor(corPlacarA)
        } else {
            pTimeA.setTextColor(corPlacarA)
            pTimeB.setTextColor(corPlacarB)
        }
    }

    fun reiniciarPartida() {
        pontuacaoTimeA = 0
        pontuacaoTimeB = 0
        pTimeA.text = "0"
        pTimeB.text = "0"
        pTimeA.setTextColor(corPlacarA)
        pTimeB.setTextColor(corPlacarB)
        Toast.makeText(this, "Placar Reiniciado", Toast.LENGTH_SHORT).show()
    }

    fun renomearTime(time: String) {
        val campo = EditText(this)
        campo.hint = "Digite o nome do time"

        AlertDialog.Builder(this)
            .setTitle("Renomear Time $time")
            .setView(campo)
            .setPositiveButton("OK") { _, _ ->
                val novoNome = campo.text.toString()
                if (novoNome.isNotEmpty()) {
                    if (time == "A") {
                        nomeTimeA.text = novoNome
                    } else {
                        nomeTimeB.text = novoNome
                    }
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    fun mostrarSeletorTema() {
        val temas = arrayOf("Lakers", "Celtics", "Golden State", "Bulls")

        AlertDialog.Builder(this)
            .setTitle("Selecionar Tema")
            .setItems(temas) { _, indice ->
                if (indice == 0) aplicarTemaLakers()
                else if (indice == 1) aplicarTemaCeltics()
                else if (indice == 2) aplicarTemaGoldenState()
                else if (indice == 3) aplicarTemaBulls()
            }
            .show()
    }

    fun aplicarTemaLakers() {
        corPlacarA   = resources.getColor(android.R.color.white)
        corPlacarB   = resources.getColor(android.R.color.white)
        corDestaqueA = resources.getColor(R.color.lakers_roxo)
        corDestaqueB = resources.getColor(R.color.lakers_amarelo)

        layoutTimeA.setBackgroundColor(resources.getColor(R.color.lakers_amarelo))
        layoutTimeB.setBackgroundColor(resources.getColor(R.color.lakers_azul))

        bTresPontosTimeA.setBackgroundResource(R.drawable.btn_lakers_a)
        bDoisPontosTimeA.setBackgroundResource(R.drawable.btn_lakers_a)
        bTLivreTimeA.setBackgroundResource(R.drawable.btn_lakers_a)
        bTresPontosTimeA.setTextColor(resources.getColor(R.color.lakers_amarelo))
        bDoisPontosTimeA.setTextColor(resources.getColor(R.color.lakers_amarelo))
        bTLivreTimeA.setTextColor(resources.getColor(R.color.lakers_amarelo))

        bTresPontosTimeB.setBackgroundResource(R.drawable.btn_lakers_b)
        bDoisPontosTimeB.setBackgroundResource(R.drawable.btn_lakers_b)
        bTLivreTimeB.setBackgroundResource(R.drawable.btn_lakers_b)
        bTresPontosTimeB.setTextColor(resources.getColor(android.R.color.black))
        bDoisPontosTimeB.setTextColor(resources.getColor(android.R.color.black))
        bTLivreTimeB.setTextColor(resources.getColor(android.R.color.black))

        nomeTimeA.setTextColor(resources.getColor(R.color.lakers_roxo))
        nomeTimeB.setTextColor(resources.getColor(R.color.lakers_amarelo))
        pTimeA.setTextColor(corPlacarA)
        pTimeB.setTextColor(corPlacarB)
    }

    fun aplicarTemaCeltics() {
        corPlacarA   = resources.getColor(android.R.color.white)
        corPlacarB   = resources.getColor(R.color.celtics_verde)
        corDestaqueA = resources.getColor(R.color.celtics_dourado)
        corDestaqueB = resources.getColor(R.color.celtics_dourado)

        layoutTimeA.setBackgroundColor(resources.getColor(R.color.celtics_verde))
        layoutTimeB.setBackgroundColor(resources.getColor(android.R.color.white))

        bTresPontosTimeA.setBackgroundResource(R.drawable.btn_celtics_a)
        bDoisPontosTimeA.setBackgroundResource(R.drawable.btn_celtics_a)
        bTLivreTimeA.setBackgroundResource(R.drawable.btn_celtics_a)
        bTresPontosTimeA.setTextColor(resources.getColor(R.color.celtics_verde))
        bDoisPontosTimeA.setTextColor(resources.getColor(R.color.celtics_verde))
        bTLivreTimeA.setTextColor(resources.getColor(R.color.celtics_verde))

        bTresPontosTimeB.setBackgroundResource(R.drawable.btn_celtics_b)
        bDoisPontosTimeB.setBackgroundResource(R.drawable.btn_celtics_b)
        bTLivreTimeB.setBackgroundResource(R.drawable.btn_celtics_b)
        bTresPontosTimeB.setTextColor(resources.getColor(android.R.color.white))
        bDoisPontosTimeB.setTextColor(resources.getColor(android.R.color.white))
        bTLivreTimeB.setTextColor(resources.getColor(android.R.color.white))

        nomeTimeA.setTextColor(resources.getColor(android.R.color.white))
        nomeTimeB.setTextColor(resources.getColor(R.color.celtics_verde))
        pTimeA.setTextColor(corPlacarA)
        pTimeB.setTextColor(corPlacarB)
    }

    fun aplicarTemaGoldenState() {
        corPlacarA   = resources.getColor(android.R.color.white)
        corPlacarB   = resources.getColor(android.R.color.white)
        corDestaqueA = resources.getColor(R.color.gsw_amarelo)
        corDestaqueB = resources.getColor(R.color.gsw_azul)

        layoutTimeA.setBackgroundColor(resources.getColor(R.color.gsw_azul))
        layoutTimeB.setBackgroundColor(resources.getColor(R.color.gsw_amarelo))

        bTresPontosTimeA.setBackgroundResource(R.drawable.btn_gsw_a)
        bDoisPontosTimeA.setBackgroundResource(R.drawable.btn_gsw_a)
        bTLivreTimeA.setBackgroundResource(R.drawable.btn_gsw_a)
        bTresPontosTimeA.setTextColor(resources.getColor(android.R.color.black))
        bDoisPontosTimeA.setTextColor(resources.getColor(android.R.color.black))
        bTLivreTimeA.setTextColor(resources.getColor(android.R.color.black))

        bTresPontosTimeB.setBackgroundResource(R.drawable.btn_gsw_b)
        bDoisPontosTimeB.setBackgroundResource(R.drawable.btn_gsw_b)
        bTLivreTimeB.setBackgroundResource(R.drawable.btn_gsw_b)
        bTresPontosTimeB.setTextColor(resources.getColor(android.R.color.white))
        bDoisPontosTimeB.setTextColor(resources.getColor(android.R.color.white))
        bTLivreTimeB.setTextColor(resources.getColor(android.R.color.white))

        nomeTimeA.setTextColor(resources.getColor(R.color.gsw_amarelo))
        nomeTimeB.setTextColor(resources.getColor(R.color.gsw_azul))
        pTimeA.setTextColor(corPlacarA)
        pTimeB.setTextColor(corPlacarB)
    }

    fun aplicarTemaBulls() {
        corPlacarA   = resources.getColor(android.R.color.white)
        corPlacarB   = resources.getColor(android.R.color.white)
        corDestaqueA = resources.getColor(android.R.color.black)
        corDestaqueB = resources.getColor(R.color.bulls_vermelho)

        layoutTimeA.setBackgroundColor(resources.getColor(R.color.bulls_vermelho))
        layoutTimeB.setBackgroundColor(resources.getColor(R.color.bulls_preto))

        bTresPontosTimeA.setBackgroundResource(R.drawable.btn_bulls_a)
        bDoisPontosTimeA.setBackgroundResource(R.drawable.btn_bulls_a)
        bTLivreTimeA.setBackgroundResource(R.drawable.btn_bulls_a)
        bTresPontosTimeA.setTextColor(resources.getColor(android.R.color.white))
        bDoisPontosTimeA.setTextColor(resources.getColor(android.R.color.white))
        bTLivreTimeA.setTextColor(resources.getColor(android.R.color.white))

        bTresPontosTimeB.setBackgroundResource(R.drawable.btn_bulls_b)
        bDoisPontosTimeB.setBackgroundResource(R.drawable.btn_bulls_b)
        bTLivreTimeB.setBackgroundResource(R.drawable.btn_bulls_b)
        bTresPontosTimeB.setTextColor(resources.getColor(android.R.color.white))
        bDoisPontosTimeB.setTextColor(resources.getColor(android.R.color.white))
        bTLivreTimeB.setTextColor(resources.getColor(android.R.color.white))

        nomeTimeA.setTextColor(resources.getColor(android.R.color.white))
        nomeTimeB.setTextColor(resources.getColor(R.color.bulls_vermelho))
        pTimeA.setTextColor(corPlacarA)
        pTimeB.setTextColor(corPlacarB)
    }
}