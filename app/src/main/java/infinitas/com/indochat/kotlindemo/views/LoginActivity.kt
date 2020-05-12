package infinitas.com.indochat.kotlindemo.views

//import android.support.v7.app.AppCompatActivity
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import infinitas.com.indochat.kotlindemo.data.UserData
import android.os.Handler
import androidx.lifecycle.LifecycleOwner
import infinitas.com.indochat.kotlindemo.R
import infinitas.com.indochat.kotlindemo.widget.ProgressDialog
import kotlinx.coroutines.Job
import org.jetbrains.anko.find

class LoginActivity : AppCompatActivity(), LifecycleOwner {

    companion object {
        val TAG = "msg"
    }

    val tvMsg by lazy { find<TextView>(R.id.tv_message) }
    val etName by lazy { find<EditText>(R.id.et_name)}
    val etPassword by lazy { find<EditText>(R.id.et_password) }
    val btnLogin by lazy { find<Button>(R.id.btn_login) }
    val btnShow by lazy { find<Button>(R.id.btn_show_text) }
    lateinit var loginViewModel: LoginViewModel
    lateinit var progressDialog: ProgressDialog

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        logd("onCreate")
        initViewModel()
        initViews()

//        job = GlobalScope.launch(Dispatchers.IO) {
//            // Do something in the background
//        }
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
        btnShow.setOnClickListener {
            logd("show click")
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            logd("login Click")
            var user = UserData(etName.text.toString(), etPassword.text.toString())
            loginViewModel.setUser(user)
            etName.setText("")
            etPassword.setText("")
            progressDialog.setDialogMsg("loading....")
            progressDialog.showDialog()
            Handler().postDelayed({
                progressDialog.dismissDialog()
            }, 3000)
        }
        progressDialog = ProgressDialog(this)
    }

    fun initViewModel() {
//        loginViewModel = LoginViewModel()
        /**
         * 不再用 new 而是改成透過 ViewModelProviders 協助我們取得 ViewModel，
         * 其中 of() 的參數代表著 ViewModel 的生命範圍 (scope)，
         * 在 MainActivity 中用of(this)表示 ViewModel 的生命週期會持續到 MainActivity 不再活動 (destroy且沒有re-create) 為止，
         * 只要 MainActivity 還在活動中，ViewModel 就不會被清除，每次 create 都可以取得同一個 ViewModel。
         */
        loginViewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
        loginViewModel.getUsers().observe(this, Observer {user ->
            tvMsg.text = "$user"
        })
    }

    fun showAlertDialog() {
        // Lambda 表示式的匿名類別寫法
        AlertDialog.Builder(this)
            .setPositiveButton("Ok"){ dialogInterface, which ->
                logd("dialog")
            }
            .setMessage("Testing")
            .show()
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

}

fun Activity.logd(message: String) {
//    Log.d(this::class.java.simpleName, message)
    Log.d("msg", message)
}