package com.example.genshinhandbook.presentation.iteminfofragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.example.genshinhandbook.App
import com.example.genshinhandbook.databinding.FragmentItemInfoBinding
import com.example.genshinhandbook.presentation.entity.Character
import com.example.genshinhandbook.utils.Const.ICON_BIG
import com.example.genshinhandbook.utils.Const.PORTRAIT
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemInfoFragment : Fragment() {

    @Inject
    lateinit var itemInfoViewModelFactory: ItemInfoViewModelFactory

    private lateinit var viewModel: ItemInfoViewModel
    private lateinit var binding: FragmentItemInfoBinding
    private val args: ItemInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as App).getAppComponent().injectItemInfoFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, itemInfoViewModelFactory)[ItemInfoViewModel::class.java]

        binding.toolbarCancel.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        showShimmer()
        viewModel.getOneCharacter(args.charId)
        observeFlow()
    }

    private fun observeFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characterData.collect { data ->
                    if (data != null) setUpCard(data)
                }
            }
        }
    }

    private fun setUpCard(character: Character) {
        Log.d("MyLogCard", character.toString())
        binding.tvCharacterName.text = character.name
        binding.tvCharacterTitle.text = character.title
        val request = ImageRequest.Builder(requireContext())
            .data(character.url + character.id + PORTRAIT)
            .target(
                onStart = {
                    showShimmer()
                },
                onSuccess = { result ->
                    binding.ivInfo.load(result)
                    stopShimmer()
                },
                onError = {
                    binding.ivInfo.load(character.url + character.id + ICON_BIG)
                    stopShimmer()
                }
            )
            .build()
        context?.imageLoader?.enqueue(request)
    }

    private fun showShimmer() {
        binding.characterLayout.isVisible = false
        binding.shimmerLayout.isVisible = true
        binding.shimmerLayout.startShimmerAnimation()
    }

    private fun stopShimmer() {
        binding.characterLayout.isVisible = true
        binding.shimmerLayout.stopShimmerAnimation()
        binding.shimmerLayout.isVisible = false
    }
}