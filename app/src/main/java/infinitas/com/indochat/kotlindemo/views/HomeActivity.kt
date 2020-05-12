package infinitas.com.indochat.kotlindemo.views

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import infinitas.com.indochat.kotlindemo.R
import infinitas.com.indochat.kotlindemo.adapter.StationAdapter
import infinitas.com.indochat.kotlindemo.helper.ApiService
import infinitas.com.indochat.kotlindemo.repository.DataImpl
import infinitas.com.indochat.kotlindemo.viewModel.ViewModelFactory
import infinitas.com.indochat.kotlindemo.widget.TestObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.find

class HomeActivity : AppCompatActivity(), LifecycleOwner {

    val btnApi by lazy { find<Button>(R.id.btn_get_api) }
    val btnHost by lazy { find<Button>(R.id.btn_get_host) }
    val listViewStation by lazy { find<RecyclerView>(R.id.list_view_station)}
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        logd("onCreate")
        initViews()
        initViewModel()
//        testCallbackfunc()
    }

    override fun onStart() {
        super.onStart()
        logd("onStart")
    }

    override fun onResume() {
        super.onResume()
        logd("onResume")
    }

    fun initViews() {
        btnApi.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                homeViewModel.testStation(true, "android").observe(
                    this@HomeActivity,
                    Observer {
                        Log.d("msg", "btnApi")
                        listViewStation.adapter = StationAdapter(it, this@HomeActivity)
                        listViewStation.layoutManager = LinearLayoutManager(this@HomeActivity)
                })
            }
        }
        btnHost.setOnClickListener {

        }
    }

    fun initViewModel() {
        homeViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiService.invoke(), this@HomeActivity)
        ).get(HomeViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun testCallbackfunc() {
        var obj = TestObj(3)
        Log.d("msg", "testObj: " + obj)

        var testObj = TestObj()
        var test = "9999999"
        var x = testObj.sun(6){
            test = test + it
            when (it) {
                "oo" -> it + "dd"
                "pp" -> it + "ss"
                else -> test
            }
        }
        Log.d("msg", "x: $x")
    }
}