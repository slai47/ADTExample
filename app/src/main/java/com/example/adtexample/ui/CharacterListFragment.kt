package com.example.adtexample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adtexample.R
import com.example.adtexample.adapters.CharacterAdapter
import com.example.adtexample.databinding.FragmentCharacterListBinding
import com.example.adtexample.model.RMCharacter
import com.example.adtexample.ui.viewmodels.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : Fragment() {

    val viewModel : CharacterListViewModel by lazy {
        ViewModelProvider(this).get(CharacterListViewModel::class.java)
    }

    var adapter : CharacterAdapter? = null

    var manager : GridLayoutManager? = null

    lateinit var binding: FragmentCharacterListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentCharacterListBinding.bind(
            inflater.inflate(R.layout.fragment_character_list, container, false))
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.characters.observe(this, characterObserver)
    }

    override fun onResume() {
        super.onResume()
        setupAdapter()
        if(viewModel.isCharactersEmpty()) {
            viewModel.getCharacters()
        }
    }

    private fun setupAdapter() {
        if(adapter == null) {
            adapter = CharacterAdapter(listCallback)
            manager = GridLayoutManager(fragment_characters_list.context, resources.getInteger(R.integer.spanCount))
            fragment_characters_list.layoutManager = manager
            fragment_characters_list.adapter = adapter
        }
        adapter?.let {
            if(it.getCharacters().isEmpty()) {
                it.setCharacters(viewModel.characters.value ?: listOf())
            }
        }
        // Pagination libraries would be awesome to use but require more work. In the real job, pagination setup would be the best solution. This works
        fragment_characters_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount: Int = manager?.childCount ?: 0
                val totalItemCount: Int = manager?.itemCount ?: 0
                val firstVisibleItemPosition: Int = manager?.findFirstVisibleItemPosition() ?: 0

                val isLastPage = viewModel.currentPage >= viewModel.meta?.pages ?: 0
                val isLoading = viewModel.progress.get() == View.VISIBLE
                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        viewModel.getCharacters(viewModel.currentPage + 1)
                    }
                }
            }
        })
    }

    val listCallback : (RMCharacter)-> Unit = {
        val fragment = CharacterFragment()
        fragment.arguments = bundleOf(Pair(CharacterFragment.EXTRA_CHARACTER, it))
        fragmentManager?.beginTransaction()?.add(R.id.container, fragment)?.addToBackStack(it.name)?.commit()
    }

    val characterObserver : Observer<List<RMCharacter>> = Observer {
        adapter?.addCharacters(it)
        viewModel.progress.set(View.GONE)
    }

    override fun onPause() {
        super.onPause()
        viewModel.storeCharacters(adapter?.getCharacters())
    }
}