package com.bipuldevashish.movierating.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bipuldevashish.movierating.R
import com.bipuldevashish.movierating.databinding.FragmentMovieListBinding
import com.bipuldevashish.movierating.models.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list),
    MovieListAdaptor.OnItemClickListner {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private var viewModel: MovieListViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMovieListBinding.bind(view)

        val movieListAdaptor = MovieListAdaptor(this)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdaptor
        }

        viewModel = ViewModelProvider(requireActivity()).get(MovieListViewModel::class.java)
        viewModel!!.movies.observe(viewLifecycleOwner){
            movieListAdaptor.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onItemClick(data: Movie) {
            val action = MovieListFragmentDirections.actionMovieListToMovieDetails(data)
            view?.findNavController()?.navigate(action)
    }
}
