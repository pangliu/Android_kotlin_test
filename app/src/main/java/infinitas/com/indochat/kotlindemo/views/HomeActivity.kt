package infinitas.com.indochat.kotlindemo.views

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import infinitas.com.indochat.kotlindemo.R

class HomeActivity : AppCompatActivity() {

    lateinit var btnApi: Button
    lateinit var btnHost: Button
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        logd("onCreate")
        initViews()
        initViewModel()
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
        btnApi = this.findViewById(R.id.btn_get_api)
        btnHost = this.findViewById(R.id.btn_get_host)
        btnApi.setOnClickListener {

        }
    }

    fun initViewModel() {
        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        homeViewModel.getStation().observe(this, Observer {
            // renew station
            logd("update listView")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}