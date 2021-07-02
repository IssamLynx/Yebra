package com.example.projettdm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.projettdm.R
import com.example.projettdm.ui.affichageMedecins.ListeMedecins
import com.example.projettdm.ui.traitements.TraitementsFragment
import kotlinx.android.synthetic.main.activity_user.*
import androidx.navigation.ui.setupActionBarWithNavController

@Suppress("DEPRECATION")
class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
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
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView2)
        return navController.navigateUp()
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView2,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



}