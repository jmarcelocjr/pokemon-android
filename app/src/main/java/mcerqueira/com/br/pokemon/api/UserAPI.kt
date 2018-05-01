package mcerqueira.com.br.pokemon.api

import mcerqueira.com.br.pokemon.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {

    @POST("/user")
    fun save(@Body user: User) : Call<Void>

    @POST("/user/login")
    fun login(@Body user: User): Call<User>
}