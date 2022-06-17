package com.vas.feature_details_screen.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import coil.load
import com.vas.core.utils.Constants
import com.vas.core.utils.Result
import com.vas.feature_details_screen.R
import com.vas.feature_details_screen.databinding.FragmentDetailsBinding
import com.vas.feature_details_screen.di.DetailsComponentViewModel
import com.vas.feature_details_screen.domain.model.Hero
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory

    private var binding: FragmentDetailsBinding? = null
    private var viewModel: DetailsViewModel? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<DetailsComponentViewModel>()
            .newDetailsComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupObservers() {
        var name = arguments?.getString("name")?:"Lina"

        viewModel?.getDetails(name)?.observe(viewLifecycleOwner, Observer { result ->
            when (result.status){
                Result.Status.SUCCESS -> {

                    Log.d("status", "SUCCESS")

                    result.data?.let { setupUI(it) }
                }
                Result.Status.LOADING -> Log.d("status", "LOADING")
                Result.Status.ERROR -> Log.d("status", "ERROR")
            }
        })
    }

    private fun setupUI(hero: Hero) {
        binding?.apply {
            strTextView.text = "${hero.str} + ${hero.strGain}"
            intTextView.text = "${hero.int} + ${hero.intGain}"
            agiTextView.text = "${hero.agi} + ${hero.agiGain}"
            attackTypeTextView.text = "Attack type: ${hero.attackType}"
            healthTextView.text = "Health: ${hero.health} + ${hero.healthRegen}"
            manaTextView.text = "Mana: ${hero.mana} + ${hero.manaRegen}"
            armorTextView.text = "Armor: ${hero.armor}"
            attackTextView.text = "Attack: ${hero.attackMin} - ${hero.attackMax}"
            nameTextView.text = "${hero.name}"
            rolesTextView.text = "Roles: " + hero.roles.joinToString()
            heroImageView.load(Constants.BASE_URL + hero.urlImg){
                crossfade(true)
                placeholder(R.drawable.antimage)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DetailsViewModel::class.java)
    }

}