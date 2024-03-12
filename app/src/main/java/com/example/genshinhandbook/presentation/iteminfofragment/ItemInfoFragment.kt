package com.example.genshinhandbook.presentation.iteminfofragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.genshinhandbook.data.Character
import com.example.genshinhandbook.databinding.FragmentItemInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemInfoFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val itemInfoViewModelFactory = ItemInfoViewModelFactory()
        viewModel = ViewModelProvider(this)[ItemInfoViewModel::class.java]

        binding.toolbarCancel.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getOneCharacter(args.charId)
        initView()
    }

    private fun initView() {
        var character: Character?
        viewModel.getOneCharacter(args.charId).observe(viewLifecycleOwner) {
            character = it
            setUpCard(it)
        }
    }

    private fun setUpCard(character: Character) {
        binding.tvCharacterName.text = character.name
        binding.ivInfo.load("https://genshin.jmp.blue/characters/${args.charId.lowercase()}/portrait")
        binding.tvCharacterTitle.text = character.title
    }

}