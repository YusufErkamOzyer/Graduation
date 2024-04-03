package com.yusuferkamozyer.graduation.preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.yusuferkamozyer.graduation.R
import com.yusuferkamozyer.graduation.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreferencesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityPreferencesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.preferencesActivityToolbar)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_top_backward,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_back->{
                onBackPressed() // Geri tuşuyla aynı işlemi yapar
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }
}