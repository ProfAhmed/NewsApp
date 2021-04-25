package ahmedpro.com.link_development.ui.home

import ahmedpro.com.link_development.ApiService
import ahmedpro.com.link_development.DataRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

class HomeViewModel(apiService: ApiService) : ViewModel() {
    private val repo = DataRepository.getInstance(apiService)
    fun getNewsApi(data: HashMap<String, String>) = repo.getNewsApi(data)
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}