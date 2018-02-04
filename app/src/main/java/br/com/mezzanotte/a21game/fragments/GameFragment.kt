package br.com.mezzanotte.a21game.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import br.com.mezzanotte.a21game.EstourouPontuacaoActivity

import br.com.mezzanotte.a21game.R
import br.com.mezzanotte.a21game.dao.CartaDAO
import br.com.mezzanotte.a21game.model.Carta
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*
import kotlin.collections.ArrayList


class GameFragment : Fragment() {

    val cartaDAO = CartaDAO()
    val gerador = Random()
    var cartas: MutableList<Carta> = ArrayList()
    var cartaSelecionadaId: Int = 0

    lateinit var btRecomecar: Button
    lateinit var tvPontuacao: TextView

    companion object {
        val PONTUACAO_TAG = "PONTUACAO"
        val CARTAS = "CARTAS"
        val POSICAO_SELECIONADA = "POSICAO_SELECIONADA"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_game, container, false)
        btRecomecar = view.findViewById(R.id.btRecomecar)
        tvPontuacao = view.findViewById(R.id.tvPontuacao)

        btRecomecar.setOnClickListener({
            iniciarPartida()
        })

        iniciarPartida()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btProximaCarta.setOnClickListener({
            realizarJogada()
        })

        if (savedInstanceState != null) {
            cartas = savedInstanceState.getParcelableArray(CARTAS).toMutableList() as MutableList<Carta>
            tvPontuacao.text = savedInstanceState.getString(PONTUACAO_TAG)
            cartaSelecionadaId = savedInstanceState.getInt(POSICAO_SELECIONADA)
            ivCarta.setImageDrawable(ContextCompat.getDrawable(context, cartaSelecionadaId))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putParcelableArray(CARTAS, cartas.toTypedArray())
        outState.putString(PONTUACAO_TAG, tvPontuacao.text.toString())
        outState.putInt(POSICAO_SELECIONADA, cartaSelecionadaId)
    }

    fun iniciarPartida() {
        tvPontuacao.setText("0")
        cartas = cartaDAO.getBaralho().toMutableList()
    }

    fun realizarJogada() {
        var posicaoCartaSelecionada = gerador.nextInt(cartas.size)
        val cartaSelecionada = cartas.get(posicaoCartaSelecionada)
        cartaSelecionadaId = cartaSelecionada.resourceId

        val pontuacaoAtualizada = tvPontuacao.text.toString().toInt() + cartaSelecionada.pontuacao
        tvPontuacao.text = pontuacaoAtualizada.toString()
        if (pontuacaoAtualizada > 21) {
            val intent = Intent(context, EstourouPontuacaoActivity::class.java)
            intent.putExtra(PONTUACAO_TAG, tvPontuacao.text.toString())
            startActivity(intent)
        } else {
            cartas.removeAt(posicaoCartaSelecionada)
            ivCarta.setImageDrawable(ContextCompat.getDrawable(context, cartaSelecionadaId))
        }
    }


}// Required empty public constructor
