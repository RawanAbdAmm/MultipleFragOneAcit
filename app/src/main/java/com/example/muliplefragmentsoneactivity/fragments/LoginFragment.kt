package com.example.muliplefragmentsoneactivity.fragments

import com.example.muliplefragmentsoneactivity.helper.Repository
import com.example.muliplefragmentsoneactivity.helper.RetrofitClient
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.muliplefragmentsoneactivity.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import retrofit2.HttpException

class LoginFragment : Fragment() {

    private lateinit var loginRepository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authService = RetrofitClient.apiService
        loginRepository = Repository(authService)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<EditText>(R.id.etUsername)
        val passwordEditText = view.findViewById<EditText>(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username.isNotBlank() && password.isNotBlank()) {
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                            withContext(Dispatchers.IO) {
                            loginRepository.login(username, password)
                        }
                        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT)
                            .show()
                    } catch (e: HttpException) {
                        Toast.makeText(
                            requireContext(),
                            "Login failed: ${e.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(
                            requireContext(),
                            "An error occurred: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Username and password are required",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
