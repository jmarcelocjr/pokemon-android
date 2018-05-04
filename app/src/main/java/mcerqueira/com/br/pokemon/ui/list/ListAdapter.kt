package mcerqueira.com.br.pokemon.ui.list

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.model.Pokemon
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import mcerqueira.com.br.pokemon.api.PokemonAPI
import mcerqueira.com.br.pokemon.api.RetrofitClient
import mcerqueira.com.br.pokemon.ui.edit.EditPokemonFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            itemView.tvType.text = "Type " + pokemon.type.name
            itemView.tvPokeball.text = "Pokeball Captured " + pokemon.pokeball.name

            itemView.setOnClickListener {
                val activity = itemView.context as AppCompatActivity
                val editPokemonFragment = EditPokemonFragment()

                val bundle = Bundle()
                bundle.putString("name", pokemon.name)

                editPokemonFragment.arguments = bundle

                activity.supportFragmentManager.beginTransaction().replace(
                    R.id.containerFragment,
                    editPokemonFragment
                ).addToBackStack(null).commit()
            }

            itemView.setOnLongClickListener {
                var api = RetrofitClient.getInstance().create(PokemonAPI::class.java)

                val builder = AlertDialog.Builder(itemView.context)

                builder.setTitle("Delete")
                builder.setMessage("Are you sure want to delete? There's no turning back")

                builder.setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                        Toast.makeText(itemView.context, "Deleting...", Toast.LENGTH_SHORT).show()

                        api.delete(pokemon.id!!).enqueue(object : Callback<Void> {
                            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                                Toast.makeText(itemView.context, "Deleted Successfully", Toast.LENGTH_SHORT).show()

                                itemView.visibility = View.GONE
                            }
                        })
                        dialogInterface.dismiss()
                })
                builder.setNegativeButton("No", DialogInterface.OnClickListener {
                    dialogInterface, i ->
                        dialogInterface.dismiss()
                })

                val alert = builder.create()
                alert.show()

                true
            }
        }
    }
}