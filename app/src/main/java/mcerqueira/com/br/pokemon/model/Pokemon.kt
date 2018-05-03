package mcerqueira.com.br.pokemon.model

data class Pokemon(
    var id: String?,
    var name: String,
    var type: Type,
    var pokeball: Pokeball
)