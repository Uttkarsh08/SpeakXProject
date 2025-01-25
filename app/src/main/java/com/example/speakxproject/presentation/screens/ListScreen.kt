import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.speakxproject.R
import com.example.speakxproject.data.model.Item
import com.example.speakxproject.util.ItemShimmerEffect
import com.example.speakxproject.util.ItemState

@Composable
fun ListScreen(modifier: Modifier = Modifier , viewModel: ListViewModel) {

    val itemState = viewModel.itemState.collectAsState()
    Scaffold(
        topBar = { TopBar() },
        containerColor = colorResource(R.color.Grey),
        contentColor = Color.White,

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when(itemState.value){
                is ItemState.Error -> {

                }
                is ItemState.Loading -> {

                    ShimmerEffect(modifier = Modifier.fillMaxWidth())
                }
                is ItemState.Success -> {
                    val items = (itemState.value as ItemState.Success).data.collectAsLazyPagingItems()
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ){

                        items(items.itemCount){index->
                            val item = items[index]

                            if(item != null){
                                Item(item)
                            }

                        }

                        items.apply {
                            when {
                                loadState.append is LoadState.Loading -> {
                                    items(10) {
                                        ShimmerEffect()
                                    }
                                }
                                loadState.refresh is LoadState.Loading -> {
                                    items(10) {
                                        ShimmerEffect()
                                    }
                                }
                            }
                        }

                    }
                }
            }



        }
    }


}

@Composable
fun Item(data: Item){
    Column(
        modifier = Modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ){

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 32.dp)
            ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = data.id.toString() , fontSize = 20.sp)
            Text(text = data.title , fontSize = 20.sp)
        }

        HorizontalDivider(color = colorResource(R.color.Divider), thickness = 2.dp)


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "SpeakX Project",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        colors = TopAppBarColors(
            containerColor = Color.Black,
            scrolledContainerColor = Color.Black,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.White,
            actionIconContentColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
    ) {
        repeat(10) {
            ItemShimmerEffect(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewShimmerEffect() {
    ShimmerEffect()
}




