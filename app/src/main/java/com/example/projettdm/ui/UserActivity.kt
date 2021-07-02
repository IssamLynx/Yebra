package com.example.projettdm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.projettdm.R
import com.example.projettdm.ui.affichageMedecins.ListeMedecins
import com.example.projettdm.ui.traitements.TraitementsFragment
import kotlinx.android.synthetic.main.activity_user.*

@Suppress("DEPRECATION")
class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        loadFragment(ListeMedecins())

        bottom_navigatin_view.setOnNavigationItemSelectedListener {menuItem ->
        when(menuItem.itemId) {
            R.id.medecins -> {
                loadFragment(ListeMedecins())
                true

            }
            R.id.traitements -> {
                loadFragment(TraitementsFragment())
                true
            }
            else -> false
        }

        }

    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView5,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}