package com.maverick.deltafour.owner.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.maverick.deltafour.R
import com.maverick.deltafour.databinding.FragmentShinobuBinding
import com.maverick.deltafour.observer.FeedViewModel

class ShinobuFragment : Fragment() {

    private lateinit var binding: FragmentShinobuBinding
    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shinobu, container, false)
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getShinobuImage()
        shinobuImageObserver()

        binding.refreshButton.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            viewModel.getShinobuImage()
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            Log.e("loading shinobu", isLoading.toString())
            binding.refreshButton.isEnabled = !isLoading
            binding.refreshButton.alpha = if (isLoading) 0.5f else 1.0f
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            binding.progress.visibility = View.VISIBLE
            viewModel.getShinobuImage()
        }
    }

    private fun shinobuImageObserver() {
        viewModel.getShinobuObservable.observe(viewLifecycleOwner) { image ->
            Glide.with(requireContext()).load(image?.url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        Toast.makeText(
                            requireContext(),
                            "Image could not load. Please refresh.",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progress.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progress.visibility = View.GONE
                        return false
                    }

                }).into(binding.image)
            image?.url?.let { Log.e("image", it) }
        }
    }
}