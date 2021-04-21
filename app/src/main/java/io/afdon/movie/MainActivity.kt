package io.afdon.movie

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import io.afdon.movie.databinding.ActivityMainBinding
import io.afdon.movie.event.ToastEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var navHostFragment : NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Movie)
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)


        DataBindingUtil.setContentView<ActivityMainBinding>( this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
    }

    override fun onBackPressed() {
        if (navHostFragment != null
                && navHostFragment!!.parentFragmentManager.backStackEntryCount == 0
                && navHostFragment!!.childFragmentManager.backStackEntryCount == 0) {
            moveTaskToBack(true)
        } else {
            super.onBackPressed()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onToastEvent(toastEvent: ToastEvent) {
        toastEvent.message?.let {
            Toast.makeText(this.applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navHostFragment = null
        EventBus.getDefault().unregister(this)
    }
}