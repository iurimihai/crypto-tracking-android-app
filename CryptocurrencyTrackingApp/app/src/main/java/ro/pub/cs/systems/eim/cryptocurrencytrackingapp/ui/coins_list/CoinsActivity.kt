package ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.coins_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.R
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.databinding.ActivityMainBinding
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.ui.login.LoginActivity
import ro.pub.cs.systems.eim.cryptocurrencytrackingapp.utils.Constants

class CoinsActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)

        val drawer = findViewById<DrawerLayout>(R.id.user_drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView = findViewById<NavigationView>(R.id.user_nav_view)
        navView.setNavigationItemSelectedListener { option ->
            when (option.itemId) {
                R.id.itmLogin ->
                    Intent(this@CoinsActivity, LoginActivity::class.java).also {
                        startActivity(it)
                    }
                R.id.itmRegister ->
                    Intent(this@CoinsActivity, LoginActivity::class.java).also {
                        it.putExtra(Constants.REGISTER, true)
                        startActivity(it)
                    }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * Enables back button support. Simply navigates one element up on the stack.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun openLoginRegisterActivity(register: Boolean) {

    }
}