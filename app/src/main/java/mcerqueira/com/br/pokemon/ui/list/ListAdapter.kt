package mcerqueira.com.br.pokemon.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.model.Pokemon

class ListAdapter(
    private val pokemons: List<Pokemon>,
    private val context: Context
): RecyclerView.Adapter<ListAdapter.MeuViewHolder>() {

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MeuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MeuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeuViewHolder?, position: Int) {
        val pokemon = pokemons.get(position)
        holder?.let {
            it.bindView(pokemon)
        }
    }

    class MeuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemon: Pokemon) {
            itemView.tvName.text = "Pokemon " + pokemon.name
            itemView.tvLevel.text = "Type " + pokemon.type.name
            itemView.tvPokeball.text = "Pokeball Captured " + pokemon.pokeball.name
        }
    }
}