package mcerqueira.com.br.pokemon.ui.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.error.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.loading.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.api.PokemonAPI
import mcerqueira.com.br.pokemon.api.RetrofitClient
import mcerqueira.com.br.pokemon.model.Pokemon

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater!!.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    fun loadData(){
        val api = RetrofitClient
                .getInstance()
                .create(PokemonAPI::class.java)

        loading.visibility = View.VISIBLE

        api.findAll().enqueue(object : Callback<List<Pokemon>> {
            override fun onFailure(call: Call<List<Pokemon>>?, t: Throwable?) {
                containerError.visibility = View.VISIBLE
                tvMensageError.text = t?.message
                loading.visibility = View.GONE
            }

            override fun onResponse(call: Call<List<Pokemon>>?, response: Response<List<Pokemon>>?) {
                if(response?.isSuccessful == true) {
                    setupList(response?.body())
                }else{
                    containerError.visibility = View.VISIBLE
                    tvMensageError.text = response?.errorBody()?.charStream()?.readText()
                }

                loading.visibility = View.GONE
            }
        })
    }

    fun setupList(pokemons: List<Pokemon>?) {
        pokemons.let {
            rvPokemon.adapter = ListAdapter(pokemons!!, context)
            val layoutManager = LinearLayoutManager(context)
            rvPokemon.layoutManager = layoutManager
        }
    }
}
