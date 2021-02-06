package com.a.todo.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.a.todo.R
import com.a.todo.data.model.Priority
import com.a.todo.data.model.TodoData
import com.a.todo.data.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private val mTodoViewModel : TodoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val  view = inflater.inflate(R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()
        val mPriority = priority_spinner.selectedItem.toString()
        val mDescription = description_et.text.toString()

        val validation = verifyDataFromUser(mTitle, mDescription)

        if (validation){
            val newData = TodoData(
                0,
                mTitle,
                parsPriority(mPriority),
                mDescription
            )
            mTodoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }

    private fun verifyDataFromUser(mTitle: String, mDescription: String): Boolean {
        return if (TextUtils.isEmpty(mTitle) || TextUtils.isEmpty(mDescription)){
            false
        } else !(mTitle.isEmpty() || mDescription.isEmpty())
    }

    private fun parsPriority(priority: String): Priority{
        return when (priority) {
                "High Priority" -> {Priority.HIGH}
                "Medium Priority" -> {Priority.MEDIUM}
                "Low Priority" -> {Priority.LOW}
                else -> Priority.LOW
            }
    }
}