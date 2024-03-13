package com.example.genshinhandbook.presentation.homefragment

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.genshinhandbook.App
import com.example.genshinhandbook.databinding.FragmentHomeBinding
import com.example.genshinhandbook.presentation.entity.CharacterDTO
import com.example.genshinhandbook.recyclerview.RecyclerViewAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).getAppComponent().injectHomeFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        initRecyclerView()
        viewModel.getAllCharacters()
        observeFlow()

    }

    private fun initRecyclerView() {

        recyclerViewAdapter = RecyclerViewAdapter(viewModel.charactersPhotoList.value,
            object : RecyclerViewAdapter.Callback {
                override fun onItemClicked(item: CharacterDTO) {
                    item.id?.let { viewModel.onCardClicked(it) }
                }
            })
        val recyclerView: RecyclerView = binding.recyclerViewHome

        var linearLayoutManager = GridLayoutManager(this.context, 2)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayoutManager = GridLayoutManager(this.context, 4)
        }

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun observeFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.charactersPhotoList.collect { data ->
                    recyclerViewAdapter.setData(data)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigateToItemInfo.collect() { id ->
                    if (id != null) navigateToCharactersInfoFragment(id)
                }
            }
        }
    }

    private fun navigateToCharactersInfoFragment(id: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToItemInfoFragment(id)
        findNavController().navigate(action)
        viewModel.doneNavigating()
    }

}
