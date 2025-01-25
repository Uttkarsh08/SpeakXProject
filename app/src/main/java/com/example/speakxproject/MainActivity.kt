package com.example.speakxproject

import ListScreen
import ListViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speakxproject.presentation.viewModel.ListViewModelFactory
import com.example.speakxproject.ui.theme.SpeakXProjectTheme
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val repository = (application as SpeakXProjectApplication).repository
            val viewModel: ListViewModel = viewModel(factory = ListViewModelFactory(repository))
            SpeakXProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListScreen(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
