package com.example.traveler.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.traveler.R
import com.example.traveler.databinding.FragmentAuthBinding
import com.example.traveler.presentation.viewmodel.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
class Auth : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var navController: NavController
    private val viewModel: AuthViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var prefs = requireContext().getSharedPreferences("NAME", Context.MODE_PRIVATE)
        /*if (prefs.getString("userName", "Denied") == "Success") {
            findNavController().navigate(R.id.action_auth_to_default2)
        }*/
        navController = findNavController()
        val button = binding.button
        val registerButton = binding.buttonSmallRegister
        val userEmail = binding.firstEdit
        val userPassword = binding.secondEdit
        val databaseF = Firebase.firestore
        button.setOnClickListener {
            if (userEmail.text.isNullOrEmpty() || userPassword.text.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "НЕВЕРНО!!!", Toast.LENGTH_LONG).show()
            } else {
                databaseF.collection("users")
                    .get()
                    .addOnSuccessListener {
                        for (document in it) {
                            if (document.getString("email") == userEmail.text.toString()) {
                                if (document.getString("password") == userPassword.text.toString()) {
                                    navController.navigate(R.id.action_auth_to_default2)
                                }
                            }
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "failed", Toast.LENGTH_LONG).show()
                    }
            }
        }
        registerButton.setOnClickListener {
            navController.navigate(R.id.action_auth_to_register)
        }
    }
}