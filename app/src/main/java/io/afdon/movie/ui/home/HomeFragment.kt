package io.afdon.movie.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.afdon.movie.MainViewModel
import io.afdon.movie.MovieApp
import io.afdon.movie.R
import io.afdon.movie.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by viewModels()
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as MovieApp).appComponent.inject(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            mvm = mainViewModel
            setHasOptionsMenu(true)
            with (requireActivity() as AppCompatActivity) {
                setSupportActionBar(toolbar)
            }
            val adapter = MovieListAdapter()
            rvMovie.adapter = adapter
            viewModel.movies.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTrendingMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reload -> {
                viewModel.getTrendingMovies()
                true
            }
            R.id.action_settings -> {
                findNavController().navigate(HomeFragmentDirections.movieListToSettings())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}