import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.speakxproject.domain.repository.ItemRepository
import com.example.speakxproject.util.ItemState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    private val _itemsState = MutableStateFlow<ItemState>(ItemState.Loading())
    val itemState = _itemsState.asStateFlow()

    init{

        fetchData()
    }

    private fun fetchData(){
        viewModelScope.launch {
            val data = repository.getItemsPager().flow.cachedIn(viewModelScope)
            _itemsState.value = ItemState.Success(data)
        }
    }
}
