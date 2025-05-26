package com.openclassrooms.arista.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openclassrooms.arista.databinding.FragmentUserDataBinding
import com.openclassrooms.arista.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

/**
 * Fragment responsible for displaying and managing user data.
 *
 * Uses [UserDataViewModel] to observe the current user information and updates the UI accordingly.
 * The fragment is annotated with [AndroidEntryPoint] to support Hilt dependency injection.
 *
 * Lifecycle:
 * - Inflates the layout via view binding in [onCreateView].
 * - Observes the user data flow from the ViewModel in [onViewCreated], updating the UI fields when data changes.
 *
 * UI Elements:
 * - EditText for user's name (`etName`)
 * - EditText for user's email (`etEmail`)
 *
 * Logs user data loading and existence checks for debugging purposes.
 */
@AndroidEntryPoint
class UserDataFragment : Fragment() {
    private lateinit var binding: FragmentUserDataBinding
    private val viewModel: UserDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userFlow.collect { user: User? ->
                Log.d("OM:UserDataFragment", "Check user existence")
                user?.let {
                    binding.etName.setText(it.name)
                    binding.etEmail.setText(it.email)
                    Log.d("OM:UserDataFragment", "User data loaded: $it")
                }
            }
        }
    }
}
