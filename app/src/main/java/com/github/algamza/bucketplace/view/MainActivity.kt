package com.github.algamza.bucketplace.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.algamza.bucketplace.R
import com.github.algamza.bucketplace.databinding.ActivityMainBinding
import com.github.algamza.bucketplace.view.home.HomeViewModel
import com.github.algamza.bucketplace.view.signin.SignInFragment
import com.github.algamza.bucketplace.view.signup.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.view = this
        binding.viewModel = viewModel
        binding.navControllerView.setupWithNavController(findNavController(R.id.nav_host))
    }

    override fun onStop() {
        super.onStop()
        viewModel.logout()
    }

    fun onClickSignUp() {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.nav_host, SignUpFragment()).commit()
    }

    fun onClickSignIn() {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.nav_host, SignInFragment()).commit()
    }

    fun onClickLogOut() { viewModel.logout() }

}