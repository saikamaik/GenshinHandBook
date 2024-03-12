package com.example.genshinhandbook.presentation.iteminfofragment

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
import androidx.navigation.fragment.navArgs
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.example.genshinhandbook.App
import com.example.genshinhandbook.data.model.Character
import com.example.genshinhandbook.databinding.FragmentItemInfoBinding
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
        binding.tvCharacterName.text = character.name
        binding.tvCharacterTitle.text = character.title
        val request = ImageRequest.Builder(requireContext())
            .data("https://genshin.jmp.blue/characters/${args.charId.lowercase()}/portrait")
            .target(
                onStart = { placeholder ->
                    binding.shimmerLayout.startShimmerAnimation()
                },
                onSuccess = { result ->
                    binding.ivInfo.load(result)
                    binding.shimmerLayout.stopShimmerAnimation()
                    binding.shimmerLayout.visibility = View.GONE
                },
                onError = {
                    binding.ivInfo.load("https://genshin.jmp.blue/characters/${args.charId.lowercase()}/icon-big")
                    binding.shimmerLayout.stopShimmerAnimation()
                    binding.shimmerLayout.visibility = View.GONE
                }
            )
            .build()
        context?.imageLoader?.enqueue(request)
    }
}