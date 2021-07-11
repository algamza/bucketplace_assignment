package com.github.algamza.bucketplace.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.algamza.bucketplace.R
import com.github.algamza.bucketplace.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private val viewModel: UserViewModel by viewModels()


    companion object {
        private const val ID = "id"
        fun newInstance(id: Int) = UserFragment().apply {
            arguments = Bundle(1).apply { putInt(ID, id) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { viewModel.updateUser(it.getInt(ID)) }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        binding.lifecycleOwner = this
        binding.data = viewModel
        return binding.root
    }
}
