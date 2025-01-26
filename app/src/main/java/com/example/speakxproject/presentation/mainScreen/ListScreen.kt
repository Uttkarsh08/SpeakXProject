import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.speakxproject.presentation.mainScreen.util.Item
import com.example.speakxproject.presentation.mainScreen.util.SearchBar
import com.example.speakxproject.presentation.mainScreen.util.ShimmerEffect
import com.example.speakxproject.presentation.mainScreen.util.TopBar
import com.example.speakxproject.util.ItemState

@Composable
fun ListScreen(viewModel: ListViewModel, modifier: Modifier) {

    val itemState = viewModel.itemState.collectAsState()
    val hasMore by viewModel.hasMore.collectAsState()
    val searchHasMore by viewModel.searchHasMore.collectAsState()

    Scaffold(
        topBar = { TopBar(text = "SpeakX Project") },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground

    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                onSearchChanged = { query ->
                    viewModel.updateSearchQuery(query)
                }
            )
            if (!searchHasMore) {
                Text(
                    text = "Please Search Between 1-2000",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                        .wrapContentHeight(Alignment.Top)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }

            when (itemState.value) {
                is ItemState.Error -> {
                    Text(
                        text = "Error loading items",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                is ItemState.Loading -> {
                    ShimmerEffect(modifier = Modifier.fillMaxWidth())
                }

                is ItemState.Success -> {
                    val items =
                        (itemState.value as ItemState.Success).data.collectAsLazyPagingItems()
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {

                        items(items.itemCount) { index ->
                            val item = items[index]
                            if (item != null) {
                                Item(item)
                            }
                        }

                        items.apply {
                            when {
                                loadState.append is LoadState.Loading -> {
                                    items(1) {
                                        ShimmerEffect()
                                    }
                                }

                                loadState.refresh is LoadState.Loading -> {
                                    items(1) {
                                        ShimmerEffect()
                                    }
                                }

                                loadState.refresh is LoadState.NotLoading && !hasMore -> {
                                    items(1) {
                                        Text(
                                            text = "No more items available",
                                            color = MaterialTheme.colorScheme.error,
                                            style = MaterialTheme.typography.titleLarge,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                ItemState.Searching -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
    }
}










