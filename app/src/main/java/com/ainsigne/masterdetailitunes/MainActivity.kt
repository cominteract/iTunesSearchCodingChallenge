package com.ainsigne.masterdetailitunes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.ainsigne.masterdetailitunes.databinding.ActivityMainBinding
import com.ainsigne.masterdetailitunes.di.ActivityComponent
import com.ainsigne.masterdetailitunes.di.ActivityModule
import com.ainsigne.masterdetailitunes.di.DaggerActivityComponent
import com.ainsigne.masterdetailitunes.di.DatabaseModule
import com.ainsigne.masterdetailitunes.utils.ItunesConfig
import com.google.android.material.snackbar.Snackbar

/**
 * [AppCompatActivity] the main entry point for the fragments
 */
class MainActivity : AppCompatActivity() {

    /**
     * [ActivityComponent] to be used in dependency injection
     */
    lateinit var activityComponent: ActivityComponent
    /**
     * [AppBarConfiguration] to be used in navbarcontroller
     */
    private lateinit var appBarConfiguration: AppBarConfiguration

    /**
     * [NavController] to be used in navigation architecture component
     */
    private lateinit var navController: NavController

    /**
     * Handle necessary dagger and navigation components
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this)).databaseModule(
            DatabaseModule(applicationContext)
        ).build()

        var binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.itunes_nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, null)
        ItunesConfig.context = this

        ItunesConfig.getTimestamp()?.let {
            binding.toolbar.let {frame ->
                Snackbar.make(frame, String.format("%s %s",
                    getString(R.string.timestamp_last), it), Snackbar.LENGTH_LONG).show()
            }

        }
        // Set up ActionBar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    /**
     * Save id when pressing up
     */
    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {

                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                ItunesConfig.saveId(null)?.let {saved ->
                    if(saved){
                        Log.d(" Id is saved "," Id is saved ")
                    }
                }

                super.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    /**
     * Save id when pressing back
     */
    override fun onBackPressed() {
        ItunesConfig.saveId(null)?.let {saved ->
            if(saved){
                Log.d(" Id is saved "," Id is saved ")
            }
        }
        super.onBackPressed()
    }
}