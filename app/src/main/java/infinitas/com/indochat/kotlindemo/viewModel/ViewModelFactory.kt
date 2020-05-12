package infinitas.com.indochat.kotlindemo.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import infinitas.com.indochat.kotlindemo.helper.ApiService
import infinitas.com.indochat.kotlindemo.repository.DataImpl
import infinitas.com.indochat.kotlindemo.views.HomeViewModel

class ViewModelFactory(
    private val apiService: ApiService,
    private val context: Context
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                DataImpl(apiService, context)
            ) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}