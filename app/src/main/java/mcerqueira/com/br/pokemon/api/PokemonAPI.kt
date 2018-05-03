package mcerqueira.com.br.pokemon.api

import mcerqueira.com.br.pokemon.model.Pokemon
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PokemonAPI {

    @POST("/pokemon")
    fun save(@Body pokemon: Pokemon) : Call<Void>

    @GET("/pokemon/name/{name}")
    fun findByName(@Path("name") name: String): Call<Pokemon>

    @GET("/pokemon")
    fun findAll(): Call<List<Pokemon>>

    @GET("/pokemon/type/{type}")
    fun findByType(@Path("type") type: String): Call<List<Pokemon>>
}