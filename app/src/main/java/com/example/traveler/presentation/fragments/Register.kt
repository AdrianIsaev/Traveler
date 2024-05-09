package com.example.traveler.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.traveler.R
import com.example.traveler.databinding.FragmentAuthBinding
import com.example.traveler.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class Register : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
        var prefs = requireContext().getSharedPreferences("NAME", Context.MODE_PRIVATE).edit()
        val button = binding.button
        val loginButton = binding.buttonSmallLogin
        val userName = binding.firstEdit
        val userEmail = binding.secondEdit
        val userPassword = binding.thirdEdit

        button.setOnClickListener {

            if (userName.text.isNullOrEmpty() || userEmail.text.isNullOrEmpty() || userPassword.text.isNullOrEmpty()){
                Toast.makeText(requireContext(), "НЕВЕРНО!!!", Toast.LENGTH_LONG).show()
            }
            else {
                var db = Firebase.firestore

                val user = hashMapOf(
                    "name" to userName.text.toString(),
                    "email" to userEmail.text.toString(),
                    "password" to userPassword.text.toString()
                )
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        prefs.putString("nameGlobal", userName.text.toString()).commit()
                        prefs.putString("userName", "Success").commit()
                        findNavController().navigate(R.id.action_register_to_auth)
                    }
                    .addOnFailureListener{
                        Toast.makeText(requireContext(), "failed", Toast.LENGTH_LONG).show()
                    }
                //findNavController().navigate(R.id.action_register_to_auth)
            }
        }
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_auth)
        }
    }


}