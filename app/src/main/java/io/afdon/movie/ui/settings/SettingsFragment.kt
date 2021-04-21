package io.afdon.movie.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import io.afdon.movie.MovieApp
import io.afdon.movie.databinding.FragmentSettingsBinding
import io.afdon.movie.event.ApiKeySetEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SettingsFragment : Fragment() {

    private val viewModel : SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MovieApp).appComponent.inject(viewModel)
        viewModel.setSavedApiKey()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentSettingsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            toolbar.setupWithNavController(findNavController())
            bSaveApiKey.setOnClickListener {
                if (viewModel.saveApiKey()) findNavController().navigateUp()
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onApiKeySet(apiKeySetEvent: ApiKeySetEvent) {
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }
}