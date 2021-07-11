package com.github.algamza.bucketplace.view.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.algamza.bucketplace.R
import com.github.algamza.bucketplace.databinding.FragmentSigninBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSigninBinding
    private val viewModel: SignInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)
        binding.lifecycleOwner = this
        binding.data = viewModel
        binding.editTextNickName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setNickName(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setPassword(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        viewModel.login.observe(viewLifecycleOwner, {
            if ( it ) requireActivity().onBackPressed()
        })
        return binding.root
    }
}