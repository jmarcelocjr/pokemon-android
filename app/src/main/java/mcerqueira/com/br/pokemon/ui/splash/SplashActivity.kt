package mcerqueira.com.br.pokemon.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loadAnimation()

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }, 3000)
    }

    fun loadAnimation() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        ivLogoSplash.startAnimation(animation)
    }
}
