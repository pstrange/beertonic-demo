package app.fintonic.demo.presentation.views.activities

import android.os.Bundle
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import app.fintonic.demo.R
import app.fintonic.demo.databinding.ActivityMainBinding
import app.fintonic.demo.presentation.views.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.navController = findNavController(R.id.main_fragment)
        binding.navController?.let {
            setupActionBarWithNavController(it)
            supportActionBar?.hide()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        binding.navController?.let {
            binding.bottomBar.setupWithNavController(menu, it)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        binding.navController?.navigateUp()
        return true
    }
}