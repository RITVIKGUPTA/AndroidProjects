package com.practice.app.android4_navigate

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.practice.app.android4_navigate.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_second.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var bindings : FragmentSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.fragment_second, container, false)
        Log.i("Second Fragment", "inside onCreateView")
         bindings  = DataBindingUtil.inflate<FragmentSecondBinding>(inflater,
           R.layout.fragment_second,container,false)

        bindings.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
//                R.id.gotoSecond -> {
//                   // Toast.makeText(this, "clicked on go to second", Toast.LENGTH_SHORT).show()
//                    //myNavHostFragment.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
//                    //Log.i("MainActivity", "${count_text.value} $count")
//                    true}

                R.id.gotoThird -> {
                    myNavHostFragment.findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
                    //Toast.makeText(this, "clicked on go to second", Toast.LENGTH_SHORT).show()
                    true
                }



                else -> false
            }

        }

        return bindings.root
    }

    /*companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}