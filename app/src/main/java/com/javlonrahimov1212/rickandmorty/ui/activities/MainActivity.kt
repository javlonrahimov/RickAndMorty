package com.javlonrahimov1212.rickandmorty.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.javlonrahimov1212.rickandmorty.R
import com.javlonrahimov1212.rickandmorty.utils.NetworkStatus
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()

        NetworkStatus.init(application)
        NetworkStatus.observe(this, { isConnected ->
            if (isConnected) {
                findViewById<FrameLayout>(R.id.connection_status_view).visibility = View.GONE
                viewModel.refresh()
            } else
                findViewById<FrameLayout>(R.id.connection_status_view).visibility = View.VISIBLE
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clear) {
            viewModel.deleteAll()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpNavigation() {
        supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
    }
}