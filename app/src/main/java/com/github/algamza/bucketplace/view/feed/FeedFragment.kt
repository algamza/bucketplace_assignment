package com.github.algamza.bucketplace.view.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.github.algamza.bucketplace.R
import com.github.algamza.bucketplace.databinding.FragmentFeedBinding
import com.github.algamza.bucketplace.view.card.CardFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private val viewModel: FeedViewModel by viewModels()
    @Inject lateinit var adapter: FeedAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        binding.lifecycleOwner = this
        binding.data = viewModel
        binding.recyclerView.adapter = adapter
        viewModel.feed.observe(viewLifecycleOwner) { adapter.setData(it) }
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if ( !recyclerView.canScrollVertically(1) ) {
                    viewModel.requestFeed(viewModel.feed.value!!.size/20+1, 20)
                }
            }
        })
        viewModel.cardCallbackObj.observe(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.nav_host, CardFragment.newInstance(it.id)).commit()
        }

        return binding.root
    }
}