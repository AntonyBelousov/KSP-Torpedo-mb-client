package com.belousovas.kspapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.belousovas.R

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel =
            ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        val userName = view.findViewById<EditText>(R.id.edt_user_login)
        val userPassword = view.findViewById<EditText>(R.id.edt_user_password)
        val signInBtn = view.findViewById<Button>(R.id.btn_sign_in)


        val reader : List<String>
        resources.openRawResource(R.raw.myconf).bufferedReader().use { reader = it.readLines() }
        userName.setText(reader[0])
        userPassword.setText(reader[1])

        loginViewModel.isLoginSuccess.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            } else {
                Toast.makeText(context, "Ошибка при авторизации", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        signInBtn.setOnClickListener {
            if (userName.text.isNotEmpty() && userPassword.text.isNotEmpty()) {
                loginViewModel.login(
                    user = userName.text.toString(),
                    password = userPassword.text.toString()
                )
            } else {
                // Todo: Show message: "Некорректный логин или пароль"
                Toast.makeText(context, "Некорректный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }

    }


}

