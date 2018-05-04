package mcerqueira.com.br.pokemon.model

data class Type(
        var id: String?,
        var name: String
){
    constructor(): this(null, "")
}