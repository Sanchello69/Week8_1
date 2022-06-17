package com.vas.feature_main_screen.presentation

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
import com.vas.core.utils.Result
import com.vas.feature_main_screen.databinding.FragmentMainBinding
import com.vas.feature_main_screen.di.MainComponentViewModel
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var mainNavCommandProvider: MainNavCommandProvider

    private var binding: FragmentMainBinding? = null
    private var viewModel: MainViewModel? = null
    private var heroesAdapter: HeroesAdapter? = null

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<MainComponentViewModel>()
            .newMainComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupObservers() {
        viewModel?.heroesList?.observe(viewLifecycleOwner, Observer { result ->
            when (result.status){
                Result.Status.SUCCESS -> {

                    Log.d("status", "SUCCESS")

                    result.data?.let { if (it.isNotEmpty()){
                        heroesAdapter?.data = it
                    } }
                }
                Result.Status.LOADING -> Log.d("status", "LOADING")
                Result.Status.ERROR -> Log.d("status", "ERROR")
            }
        })
    }

    private fun setupUI() {
        initHeroesRecyclerView()
        initInfoButton()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    private fun initInfoButton() {
        binding?.infoImageView?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                mainNavCommandProvider.toAboutApp.container,
                mainNavCommandProvider.toAboutApp.fragment
            )?.addToBackStack(null)?.commit()
        }
    }

    private fun initHeroesRecyclerView() {
        heroesAdapter = HeroesAdapter()
        binding?.heroesRecyclerView?.adapter = heroesAdapter
        heroesAdapter?.onClickListener = object : HeroesAdapter.OnHeroClickListener{
            override fun onHeroClick(name: String) {
                Log.d("click", "$id")
                val bundle = Bundle()
                bundle.putString("name", name)

                mainNavCommandProvider.toDetails.fragment.arguments = bundle

                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    mainNavCommandProvider.toDetails.container,
                    mainNavCommandProvider.toDetails.fragment
                )?.addToBackStack(null)?.commit()
            }
        }
    }
}