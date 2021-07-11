package com.github.algamza.bucketplace.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.algamza.bucketplace.R
import com.github.algamza.bucketplace.databinding.FragmentHomeBinding
import com.github.algamza.bucketplace.view.card.CardFragment
import com.github.algamza.bucketplace.view.signup.SignUpFragment
import com.github.algamza.bucketplace.view.user.UserFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    @Inject lateinit var adapterUser : UserAdapter
    @Inject lateinit var adapterCard : CardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.data = viewModel
        binding.recyclerViewCard.adapter = adapterCard
        binding.recyclerViewUser.adapter = adapterUser
        viewModel.cards.observe(viewLifecycleOwner) { adapterCard.setData(it) }
        viewModel.users.observe(viewLifecycleOwner) { adapterUser.setData(it) }
        viewModel.userCallbackObj.observe(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.nav_host, UserFragment.newInstance(it.id)).commit()
        }
        viewModel.cardCallbackObj.observe(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.nav_host, CardFragment.newInstance(it.id)).commit()
        }
        return binding.root
    }
}