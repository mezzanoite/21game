package br.com.mezzanotte.a21game

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    //lateinit var ivLogo: ImageView (Se quiser usar um ivLogo = findViewById... no onCreate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        carregar()
    }

    fun carregar() {
        val animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        ivLogo.clearAnimation()
        ivLogo.startAnimation(animacao)

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
