package com.belousovas.kspapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.android.belousovas.R
import com.belousovas.kspapp.data.Repository

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val repository = Repository()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginField = view.findViewById<EditText>(R.id.edt_user_login)
        val passwordField = view.findViewById<EditText>(R.id.edt_user_password)
        val signInBtn = view.findViewById<Button>(R.id.btn_sign_in)

//        signInBtn.setOnClickListener {
//            repository.login()
//        }
    }
}

