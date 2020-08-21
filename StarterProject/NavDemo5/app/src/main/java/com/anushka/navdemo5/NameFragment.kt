package com.anushka.navdemo5


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.anushka.navdemo5.databinding.FragmentNameBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class NameFragment : Fragment() {
    private lateinit var binding: FragmentNameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)
        binding.nextButton.setOnClickListener{
            MainActivity.bundle.putString("name", binding.nameEditText.text.toString())
            if (MainActivity.bundle != null){
            Log.i("NameFragment", "if condition ${MainActivity.bundle.get("name")}")
            it.findNavController().navigate(R.id.action_nameFragment_to_emailFragment, MainActivity.bundle)
        }
        else{
            Log.i("NameFragment", "else condition ${MainActivity.bundle.get("name")}")
            Toast.makeText(this.context, "Please enter your name", Toast.LENGTH_LONG).show()
        }}
        return binding.root
    }
}
