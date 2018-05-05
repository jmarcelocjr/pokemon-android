package mcerqueira.com.br.pokemon.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import mcerqueira.com.br.pokemon.R
import mcerqueira.com.br.pokemon.ui.about.AboutFragment
import mcerqueira.com.br.pokemon.ui.list.ListFragment
import mcerqueira.com.br.pokemon.ui.registerPokemon.RegisterPokemonFragment

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_list -> {
                changeFragment(ListFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_new -> {
                changeFragment(RegisterPokemonFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                changeFragment(AboutFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun changeFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerFragment, fragment)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        changeFragment(ListFragment())
    }
}
