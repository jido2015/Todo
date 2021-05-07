package com.a.todo.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.a.todo.R
import com.a.todo.data.helper.HelperClass
import com.a.todo.data.model.TodoData
import com.a.todo.data.viewmodel.TodoViewModel
import com.a.todo.databinding.FragmentUpdateBinding
import com.a.todo.fragments.SharedViewModel
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private val mShareViewModel : SharedViewModel by viewModels()
    private val todoViewModel: TodoViewModel by viewModels()

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

        binding.currentPhoneTxt.setText( args.currentItem.title)
        binding.currentNameTxt.setText(args.currentItem.description)
        binding.currentPrioritySpinner.setSelection(mShareViewModel.parsePriorityToInt(
            args.currentItem.priority))

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save){
            updateItem()
        }

        return super.onOptionsItemSelected(item)
    }

     private fun updateItem() {
        val phoneTxt =  HelperClass.phoneNumberFormat(current_phone_txt.text.toString())
        val name = currentNameTxt.text.toString()
        val getPriority = current_priority_spinner.selectedItem.toString()

         val validation = mShareViewModel.verifyDataFromUser(phoneTxt, name)
         if (validation){
             val updateItem = TodoData(
                 args.currentItem.id,
                 phoneTxt,
                 mShareViewModel.parsPriority(getPriority),
                 name
             )
             todoViewModel.updateData(updateItem)
             Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT ).show()
             //Navigate back
             findNavController().navigate(R.id.action_updateFragment_to_listFragment)
         }else{
             Toast.makeText(context, "Please fill out all fields!", Toast.LENGTH_SHORT ).show()

         }
    }
}