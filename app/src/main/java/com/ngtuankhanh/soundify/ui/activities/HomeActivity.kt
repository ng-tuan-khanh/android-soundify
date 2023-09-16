package com.ngtuankhanh.soundify.ui.activities

import android.net.Uri
import android.os.Bundle
import androidx.navigation.findNavController
import android.view.View
import androidx.navigation.ui.setupWithNavController
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var currentTrackId: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.streamingButton.setOnClickListener {
            val isSelected = it.isSelected
            it.isSelected = !isSelected
            // Thực hiện logic play/pause tại đây
        }

        binding.streamingBar.setOnClickListener {
            val trackIdUri = Uri.parse("soundify://musicplayer/$currentTrackId")
            findNavController(R.id.nav_host_fragment).navigate(trackIdUri)
        }



        hideNavigationBar()

        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        hideNavigationBar()
    }

    private fun hideNavigationBar() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    // Function to show the streaming bar
    fun showStreamingBar() {
        binding.streamingBar.visibility = View.VISIBLE
    }

    // Function to hide the streaming bar
    fun hideStreamingBar() {
        binding.streamingBar.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
        return super.onSupportNavigateUp()
    }
}
