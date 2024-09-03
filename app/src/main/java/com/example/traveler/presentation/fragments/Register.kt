package com.example.traveler.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.traveler.R
import com.example.traveler.databinding.FragmentRegisterBinding
import com.example.traveler.presentation.viewmodel.RegisterViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class Register : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prefs = viewModel.getMainSharedPreferencesEditor(requireContext())
        binding.button.setOnClickListener {

            if (binding.firstEdit.text.isNullOrEmpty() || binding.secondEdit.text.isNullOrEmpty() || binding.thirdEdit.text.isNullOrEmpty()){
                Toast.makeText(requireContext(), "НЕВЕРНО!!!", Toast.LENGTH_LONG).show()
            }
            else {
                val db = Firebase.firestore

                val user = hashMapOf("name" to binding.firstEdit.text.toString(),
                    "email" to binding.secondEdit.text.toString(), "password" to binding.thirdEdit.text.toString())
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        prefs.putString("nameGlobal", binding.firstEdit.text.toString()).commit()
                        prefs.putString("userName", "Success").commit()
                        findNavController().navigate(R.id.action_register_to_auth)
                    }
                    .addOnFailureListener{
                        Toast.makeText(requireContext(), "failed", Toast.LENGTH_LONG).show()
                    }
                //findNavController().navigate(R.id.action_register_to_auth)
            }
        }
        binding.buttonSmallLogin.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_auth)
        }
    }


}