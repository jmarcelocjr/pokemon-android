package mcerqueira.com.br.pokemon.ui.login;

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.api.RetrofitClient
import mcerqueira.com.br.pokemon.api.UserAPI
import mcerqueira.com.br.pokemon.model.User
import mcerqueira.com.br.pokemon.ui.main.MainActivity
import mcerqueira.com.br.pokemon.ui.registerUser.RegisterUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        log_in_button.setOnClickListener { doLogin() }

        register.setOnClickListener{
            startActivity(Intent(this, RegisterUserActivity::class.java))
        }
    }

    private fun doLogin() {
        val api = RetrofitClient
                .getInstance()
                .create(UserAPI::class.java)

        val user = User(null,
            null,
            input_login.editText?.text.toString(),
            input_password.editText?.text.toString()
        )

        api.login(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Toast.makeText(
                        application.baseContext,
                        R.string.login_failed,
                        Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                if (response?.body() != null) {
                    startMainActivity()
                } else {
                    Toast.makeText(
                            application.baseContext,
                            R.string.login_failed,
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }
}
