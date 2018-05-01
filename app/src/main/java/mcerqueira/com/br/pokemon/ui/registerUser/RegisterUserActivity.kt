package mcerqueira.com.br.pokemon.ui.registerUser

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_user.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.api.RetrofitClient
import mcerqueira.com.br.pokemon.api.UserAPI
import mcerqueira.com.br.pokemon.model.User
import mcerqueira.com.br.pokemon.ui.main.MainActivity

class RegisterUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        save.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        val api = RetrofitClient
                .getInstance()
                .create(UserAPI::class.java)

        val user = User(null,
            input_name.editText?.text.toString(),
            input_login.editText?.text.toString(),
            input_password.editText?.text.toString()
        )

        api.save(user)

        Toast.makeText(
                application.baseContext,
                R.string.user_registered_successfully,
                Toast.LENGTH_SHORT
        ).show()

        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }
}