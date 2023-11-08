package com.example.crime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.crime.databinding.ActivityCrimeDetailFragmentBinding
import java.util.Date
import java.util.UUID

class CrimeDetailFragment : Fragment() {
    // allows binding to be null
    private var _binding: ActivityCrimeDetailFragmentBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private lateinit var crime: Crime

    // activity and fragment lifecycle are a bit diff
    // onCreate and onStart differs
    // onCreateView -> only inflate the view
    // onViewCreated -> wiring up views/ setting up logic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // do NOT call setContentView for a fragment
        crime = Crime(UUID.randomUUID(), "", Date(), false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = ActivityCrimeDetailFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            // wire up edit text
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                crime = crime.copy(title = text.toString())
            }

            // wire up button
            crimeDate.apply {
                text = crime.date.toString()
                isEnabled = false
            }

            // wire up checkbox
            crimeSolved.setOnCheckedChangeListener { _, isChecked ->
                crime = crime.copy(isSolved = isChecked)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // de allocate memory when not using fragment
        _binding = null
    }
}
