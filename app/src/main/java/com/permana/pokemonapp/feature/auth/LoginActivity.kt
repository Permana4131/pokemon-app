package com.permana.pokemonapp.feature.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.permana.pokemonapp.data.local.UserPreferences
import com.permana.pokemonapp.databinding.ActivityLoginBinding
import com.permana.pokemonapp.feature.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassowrd.text.toString()
            viewModel.login(username, password)
        }

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            viewModel.authResult.collectLatest { result ->
                result?.let { (status, username) ->
                    when (status) {
                        "login_success" -> {
                            UserPreferences(this@LoginActivity).saveLoginStatus(username)
                            showToast("Login successful")

                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        }
                        "invalid_credentials" -> showToast("Invalid credentials")
                    }
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}