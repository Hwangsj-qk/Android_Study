package com.busanit.ch10_fragment.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busanit.ch10_fragment.R
import com.busanit.ch10_fragment.databinding.FragmentOneBinding
import com.busanit.ch10_fragment.databinding.FragmentThreeBinding

class FragmentThree : Fragment() {

    lateinit var binding: FragmentThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

}