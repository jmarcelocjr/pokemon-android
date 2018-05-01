package mcerqueira.com.br.pokemon.api

import mcerqueira.com.br.pokemon.model.Pokeball
import retrofit2.Call
import retrofit2.http.GET

interface PokeballAPI {

    @GET("/pokeball")
    fun getAll() : Call<List<Pokeball>>
}