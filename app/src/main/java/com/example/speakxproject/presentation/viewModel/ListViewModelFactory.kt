package com.example.speakxproject.presentation.viewModel

import ListViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.speakxproject.domain.repository.ItemRepository

class ListViewModelFactory(private val repo : ItemRepository) :  ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(repo) as T
    }
}