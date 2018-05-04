package mcerqueira.com.br.pokemon.ui.edit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_register_pokemon.*
import kotlinx.android.synthetic.main.loading.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.api.PokemonAPI
import mcerqueira.com.br.pokemon.api.RetrofitClient
import mcerqueira.com.br.pokemon.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPokemonFragment : Fragment() {

    var callPokemon : Call<Pokemon>? = null
    var api = RetrofitClient.getInstance().create(PokemonAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = arguments.getString("name")

        callPokemon = api.findByName(name)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val inflateView = inflater!!.inflate(R.layout.fragment_register_pokemon, container, false)

        return inflateView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loading.visibility = View.VISIBLE

        var pokemon : Pokemon? = null

        callPokemon!!.enqueue(object: Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
                Log.e("API Fail", t?.message)
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>?) {
                pokemon = response?.body()

                inputName.editText?.setText(pokemon?.name)
                inputType.editText?.setText(pokemon?.type?.name)
                inputPokeball.editText?.setText(pokemon?.pokeball?.name)

                loading.visibility = View.GONE
            }
        })

        btSave.setOnClickListener {

            pokemon?.name = inputName.editText?.text.toString()
            pokemon?.type?.name = inputType.editText?.text.toString()
            pokemon?.pokeball?.name = inputPokeball.editText?.text.toString()

            this.api.save(pokemon!!).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    Log.e("Pokemon Update", t?.message)
                }

                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    if(response?.isSuccessful == true) {
                        Toast.makeText(context, R.string.pokemon_updated_successfully, Toast.LENGTH_SHORT).show()

                        activity.onBackPressed()
                    } else {
                        Toast.makeText(context, R.string.message_error, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}