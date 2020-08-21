package com.anushka.navdemo5


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.anushka.navdemo5.databinding.FragmentEmailBinding
import com.anushka.navdemo5.databinding.FragmentNameBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)

        Log.i("EmailFragment", binding.emailEditText.text.toString())
        binding.submitButton.setOnClickListener{
            MainActivity.bundle.putString("email", binding.emailEditText.text.toString())
            if(binding.emailEditText.text != null){
            myNavHostFragment.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment, MainActivity.bundle)
        }
        else{
            Toast.makeText(this.context, "Please enter a valid email", Toast.LENGTH_SHORT).show()
        }}
        return binding.root
    }
}
