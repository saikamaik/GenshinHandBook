package com.example.genshinhandbook.presentation.homefragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.genshinhandbook.data.Character
import com.example.genshinhandbook.data.CharacterCard
import com.example.genshinhandbook.databinding.FragmentHomeBinding
import com.example.genshinhandbook.recyclerview.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val compositeDisposable = CompositeDisposable()
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private var charactersList: List<Character> = listOf()
    private var charactersNameList: List<String> = listOf()
    private var charactersPhotoList: List<CharacterCard> = listOf()
    private var recyclerViewAdapter: RecyclerViewAdapter =
        RecyclerViewAdapter(charactersPhotoList, object : RecyclerViewAdapter.Callback {
            override fun onItemClicked(item: CharacterCard) {
//                presenter.navigateToPhotoInfoFragment(item)
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        try {
            binding = FragmentHomeBinding.inflate(layoutInflater)
            return binding.root
        } catch (e: Exception) {
            Log.e(TAG, "onCreateView", e)
            throw e
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getAllCharactersNames()
        getCharacters()
    }

    private fun initViewModel() {


//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private fun initRecyclerView() {

        val recyclerView: RecyclerView = binding.recyclerViewHome
        val progressBar: ProgressBar = binding.progressbar
        progressBar.isVisible = false

        var linearLayoutManager = GridLayoutManager(this.context, 2)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayoutManager = GridLayoutManager(this.context, 4)
        }

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = recyclerViewAdapter
    }

    @SuppressLint("CheckResult")
    private fun getAllCharactersNames() {

        viewModel.getAllCharactersNames().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                charactersNameList = it
            }, {

            }

            )

    }

    @SuppressLint("CheckResult")
    private fun getCharacters() {

        viewModel.getAllCharacters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {

            }.subscribe({
                charactersList = it
                charactersPhotoList = charactersList.map { character ->
                    CharacterCard(
                        nameConverter(character.name),
                        character.name,
                        "https://genshin.jmp.blue/characters/${nameConverter(character.name).toLowerCase()}/icon-big",
                        character.rarity
                    )
                }
                recyclerViewAdapter.setData(charactersPhotoList)
            }, {
                it.printStackTrace()
            })
    }

    // Странная апи, я не знаю как лучше реализовать

    private fun nameConverter(name: String): String {
        if (name == "Kamisato Ayaka" || name == "Kamisato Ayato" || name == "Kaedehara Kazuha"
            || name == "Sangonomiya Kokomi" || name == "Kujou Sara") {
            return name.substringAfter(" ")
        } else if (name == "Raiden Shogun") return name.substringBefore(" ")
        else if (name.contains(" ")) {
            return name.replace(" ", "-")
        }
        return name
    }

    private fun checkIfContains(firstName: String, secondName: String): Boolean {
        return secondName.contains(firstName, ignoreCase = true)
    }

}