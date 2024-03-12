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
import com.example.genshinhandbook.App
import com.example.genshinhandbook.data.model.Character
import com.example.genshinhandbook.databinding.FragmentItemInfoBinding
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
        initView()
    }

    private fun initView() {
        viewModel.getOneCharacter(args.charId).observe(viewLifecycleOwner) {
            setUpCard(it)
        }
    }

    private fun setUpCard(character: Character) {
        binding.tvCharacterName.text = character.name
        binding.ivInfo.load("https://genshin.jmp.blue/characters/${args.charId.lowercase()}/portrait")
        binding.tvCharacterTitle.text = character.title
    }

}