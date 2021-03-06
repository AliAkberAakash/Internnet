package com.aliakberaakash.internnet.ui.features.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.databinding.MenuFragmentBinding
import com.aliakberaakash.internnet.ui.features.login.LoginActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.menu_fragment.*


class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        val binding = MenuFragmentBinding.inflate(inflater,container,false)
                .apply {
                    lifecycleOwner = viewLifecycleOwner
                    viewModel = this@MenuFragment.viewModel
                }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner, {
            Glide.with(this)
                    .load(it.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .into(profile_image)
        })

        appliedJobsCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuFragment_to_appliedJobsFragment)
        }

        myJobsCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_menuFragment_to_myJobsFragment)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        log_out_button.setOnClickListener {
            Firebase.auth.signOut()
            googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                requireActivity().finish()
            }

        }
    }

}