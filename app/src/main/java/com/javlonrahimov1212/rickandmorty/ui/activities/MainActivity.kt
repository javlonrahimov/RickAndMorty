package com.javlonrahimov1212.rickandmorty.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.javlonrahimov1212.rickandmorty.R
import com.javlonrahimov1212.rickandmorty.database.AppDatabase
import com.javlonrahimov1212.rickandmorty.network.ApiHelper
import com.javlonrahimov1212.rickandmorty.network.RetrofitBuilder
import com.javlonrahimov1212.rickandmorty.utils.NetworkStatus


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
        setupViewModel()

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

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService),
                AppDatabase.getDatabase(applicationContext).appDao()
            )
        ).get(MainActivityViewModel::class.java)
    }
}