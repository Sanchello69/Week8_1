package com.vas.feature_about_app_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vas.feature_about_app_screen.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {

    private var binding: FragmentAboutAppBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBackImageView()
    }

    private fun initBackImageView() {
        binding?.imageViewBack?.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}