package mcerqueira.com.br.pokemon.model

data class Pokeball(
    var id: String?,
    var name: String
) {
    constructor(): this(null, "")
}