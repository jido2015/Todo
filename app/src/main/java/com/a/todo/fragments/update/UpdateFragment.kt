package com.a.todo.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.a.todo.R
import com.a.todo.data.model.Priority
import com.a.todo.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private var _binding: FragmentUpdateBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.currentTitleEt.setText( args.currentItem.title)
        binding.currentDescriptionEt.setText(args.currentItem.description)
        binding.currentPrioritySpinner.setSelection(parsePriority(args.currentItem.priority))

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun parsePriority(priority: Priority): Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}