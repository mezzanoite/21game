package br.com.mezzanotte.a21game

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.mezzanotte.a21game.fragments.GameFragment
import kotlinx.android.synthetic.main.activity_estourou_pontuacao.*

class EstourouPontuacaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estourou_pontuacao)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        tvPontuacaoFinal.text = intent.getStringExtra(GameFragment.PONTUACAO_TAG)

        val mediaPlayer = MediaPlayer.create(this, R.raw.explosao)
        //mediaPlayer.start()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
