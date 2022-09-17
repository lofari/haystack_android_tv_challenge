package com.example.haystackchallenge

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.example.haystackchallenge.domain.model.Character
import com.example.haystackchallenge.viewmodel.CardPresenter
import com.example.haystackchallenge.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BrowseSupportFragment() {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = getString(R.string.search)
        setPicturesObserver()

    }

    private fun setPicturesObserver() {
        viewModel.characterList.observe(viewLifecycleOwner) {
            it.data?.let { characterResponse -> initList(characterResponse) }
        }
        viewModel.load()
    }

    private fun initList(imageList: List<Character>) {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val categoryTitle = getString(R.string.characters)
        val listRowAdapter = ArrayObjectAdapter(CardPresenter())
        listRowAdapter.addAll(0, imageList)

        val header = HeaderItem(0, categoryTitle)
        rowsAdapter.add(ListRow(header, listRowAdapter))
        adapter = rowsAdapter
    }
}
