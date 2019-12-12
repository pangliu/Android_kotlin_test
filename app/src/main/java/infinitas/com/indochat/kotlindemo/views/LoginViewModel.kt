package infinitas.com.indochat.kotlindemo.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infinitas.com.indochat.kotlindemo.data.UserData
import kotlinx.coroutines.*

class LoginViewModel: ViewModel() {

    val coroutineScope = CoroutineScope(Dispatchers.Main + Job() + errorHandler)

    companion object {
        val errorHandler = CoroutineExceptionHandler { _, error ->
            error.printStackTrace()
//            TrackingCrash.logException(error)
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    private val user: MutableLiveData<UserData> by lazy {
        MutableLiveData<UserData>().also {
            loadUsers()
        }
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
        Log.d("msg", "loadUsers")
    }

    fun setUser(userNew:UserData) {
        user.value = userNew
    }

    fun getUsers(): LiveData<UserData> {
        return user
    }
}