package com.github.algamza.bucketplace.view.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.algamza.bucketplace.R
import com.github.algamza.bucketplace.databinding.FragmentCardBinding
import com.github.algamza.bucketplace.view.user.UserFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private val viewModel: CardViewModel by viewModels()
    @Inject lateinit var adapter: RecommendAdapter

    companion object {
        private const val ID = "id"
        fun newInstance(id: Int) = CardFragment().apply {
            arguments = Bundle(1).apply { putInt(ID, id) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { viewModel.updateCard(it.getInt(ID)) }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card, container, false)
        binding.lifecycleOwner = this
        binding.data = viewModel
        binding.view = this
        binding.recyclerView.adapter = adapter
        viewModel.recommends.observe(viewLifecycleOwner) { adapter.setData(it)}
        viewModel.cardCallbackObj.observe(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
                .add(R.id.nav_host, newInstance(it.id)).commit()
        }
        return binding.root
    }

    fun onClickUser(id: Int) {
        requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
            .add(R.id.nav_host, UserFragment.newInstance(id)).commit()
    }
}
