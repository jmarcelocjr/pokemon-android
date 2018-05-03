package mcerqueira.com.br.pokemon.ui.registerPokemon

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_register_pokemon.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.api.PokemonAPI
import mcerqueira.com.br.pokemon.api.RetrofitClient
import mcerqueira.com.br.pokemon.model.Pokeball
import mcerqueira.com.br.pokemon.model.Pokemon
import mcerqueira.com.br.pokemon.model.Type
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPokemonFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.fragment_register_pokemon, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btSave.setOnClickListener {
            val api = RetrofitClient.getInstance().create(PokemonAPI::class.java)

            val pokemonType = Type(null, inputType.editText?.text.toString())
            val pokeballCaptured = Pokeball(null, inputPokeball.editText?.text.toString())

            val pokemon = Pokemon(null,
                inputName.editText?.text.toString(),
                pokemonType,
                pokeballCaptured
            )

            api.save(pokemon).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>?, t: Throwable?) {
                    Log.e("Pokemon", t?.message)
                }

                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    if(response?.isSuccessful == true) {
                        Toast.makeText(context, R.string.pokemon_registered_successfully, Toast.LENGTH_SHORT).show()

                        cleanFields()
                    } else {
                        Toast.makeText(context, R.string.message_error, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun cleanFields() {
        inputName.editText?.setText("")
        inputType.editText?.setText("")
        inputPokeball.editText?.setText("")
    }

}
