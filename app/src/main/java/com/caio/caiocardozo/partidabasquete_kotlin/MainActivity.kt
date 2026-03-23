package com.caio.caiocardozo.partidabasquete_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0

    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)

        val bTresPontosTimeA: Button = findViewById(R.id.tresPontosA)
        val bDoisPontosTimeA: Button = findViewById(R.id.doisPontosA)
        val bTLivreTimeA: Button = findViewById(R.id.tiroLivreA)

        val bTresPontosTimeB: Button = findViewById(R.id.tresPontosB)
        val bDoisPontosTimeB: Button = findViewById(R.id.doisPontosB)
        val bTLivreTimeB: Button = findViewById(R.id.tiroLivreB)

        val bReiniciar: Button = findViewById(R.id.reiniciarPartida)

        bTresPontosTimeA.setOnClickListener {
            adicionarPontos(3, "A")
        }

        bDoisPontosTimeA.setOnClickListener {
            adicionarPontos(2, "A")
        }

        bTLivreTimeA.setOnClickListener {
            adicionarPontos(1, "A")
        }

        bTresPontosTimeB.setOnClickListener {
            adicionarPontos(3, "B")
        }

        bDoisPontosTimeB.setOnClickListener {
            adicionarPontos(2, "B")
        }

        bTLivreTimeB.setOnClickListener {
            adicionarPontos(1, "B")
        }

        bReiniciar.setOnClickListener {
            reiniciarPartida()
        }
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
            pTimeA.setTextColor(resources.getColor(R.color.purple_500))
            pTimeB.setTextColor(resources.getColor(android.R.color.white))
        } else if (pontuacaoTimeB > pontuacaoTimeA) {
            pTimeB.setTextColor(resources.getColor(R.color.yellow_lakers))
            pTimeA.setTextColor(resources.getColor(android.R.color.white))
        } else {
            pTimeA.setTextColor(resources.getColor(android.R.color.white))
            pTimeB.setTextColor(resources.getColor(android.R.color.white))
        }
    }

    fun reiniciarPartida() {
        pontuacaoTimeA = 0
        pTimeA.setText(pontuacaoTimeA.toString())
        pontuacaoTimeB = 0
        pTimeB.setText(pontuacaoTimeB.toString())
        Toast.makeText(this, "Placar Reiniciado", Toast.LENGTH_SHORT).show()

        pTimeA.setTextColor(resources.getColor(android.R.color.white))
        pTimeB.setTextColor(resources.getColor(android.R.color.white))
    }
}