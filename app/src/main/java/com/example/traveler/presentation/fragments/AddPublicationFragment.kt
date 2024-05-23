package com.example.traveler.presentation.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.health.connect.datatypes.units.Length
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.traveler.R
import com.example.traveler.data.room.storage.entity.Publication
import com.example.traveler.databinding.FragmentAddPublication2Binding
import com.example.traveler.presentation.viewmodel.AddPublicationViewModel
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.net.URI
import java.util.UUID
import kotlin.random.Random

class AddPublicationFragment : Fragment() {
    private lateinit var binding: FragmentAddPublication2Binding
    private val viewModel: AddPublicationViewModel by viewModels()
    private lateinit var author: String
    private lateinit var imageUri: Uri
    private lateinit var factUri: Uri
    private lateinit var myStorageImageRefs: StorageReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPublication2Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navA).visibility = View.GONE
        myStorageImageRefs = FirebaseStorage.getInstance().getReference("Images")
        val prefs = requireContext().getSharedPreferences("NAME", android.content.Context.MODE_PRIVATE)
        author = prefs.getString("nameGlobal", "noName")?: "noName"
        binding.buttonSaveAdd.setOnClickListener {

            val title = binding.editTextAdd.text.toString()
            val description = binding.editTextAdd2.text.toString()
            val address = binding.editTextAdd3.text.toString()
            val numberOfLikes = 0
            val numberOfComments = 0
            val uniqueId = UUID.randomUUID().toString()
            val publication = Publication(uniqueId, title, author, description, numberOfLikes, numberOfComments, address, factUri.toString())
            val db = FirebaseFirestore.getInstance()
            db.collection("list_items")
                .add(publication)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "lolipop", LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addPublicationFragment_to_mapFragment)
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "((((((())))", LENGTH_SHORT).show()
                }




        }
        binding.buttonAdd.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.MANAGE_EXTERNAL_STORAGE)

        }
    }
    private fun uploadImage(){
        val bitmap = (binding.imageViewAdd.drawable.toBitmap())
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val currentRefs = myStorageImageRefs.child((Math.random() * 100 + 10000).toString())
        val uploadTask = currentRefs.putBytes(byteArray)

        uploadTask.addOnSuccessListener { taskSnapshot ->
            currentRefs.downloadUrl.addOnSuccessListener { uri ->
                factUri = uri
                // Здесь вы можете использовать factUri после успешной загрузки
            }.addOnFailureListener { exception ->
                // Обработка ошибки при получении downloadUrl
            }
        }.addOnFailureListener { exception ->
            // Обработка ошибки при загрузке изображения
        }
    }
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openGallery()
        } else {
            openGallery()
        }
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        selectImageLauncher.launch(intent)
    }
    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            imageUri = data?.data!!
            binding.imageViewAdd.setImageURI(imageUri)


            uploadImage()
        }
    }

}