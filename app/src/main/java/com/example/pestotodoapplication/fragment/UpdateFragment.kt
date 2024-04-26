package com.example.pestotodoapplication.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pestotodoapplication.R
import com.example.pestotodoapplication.data.models.ToDoData
import com.example.pestotodoapplication.data.viewmodel.ToDoViewModel
import com.example.pestotodoapplication.databinding.FragmentUpdateBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.args = args
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_update_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmItemRemoval() {
        val alertDialog = AlertDialog.Builder(requireContext()).apply {
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                mToDoViewModel.deleteItem(args.currentItem)
                Toast.makeText(
                    requireContext(),
                    "${getString(R.string.removed)} '${args.currentItem.title}'",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            setNegativeButton(getString(R.string.no)) { _, _ ->  }
            setTitle("${getString(R.string.delete)} '${args.currentItem.title}'")
            setMessage("${getString(R.string.wanna_remove)} '${args.currentItem.title}'?")
            create()
        }
        alertDialog.show()
    }

    private fun updateItem() {
        val title = binding.currentTitleEt.text.toString()
        val desc = binding.currentDescriptionEt.text.toString()
        val priority = mSharedViewModel.parsePriorityString(binding.currentPrioritiesSpinner.selectedItem.toString())
        if(mSharedViewModel.verifyDataFromUser(title, desc)) {
            val updatedItem = ToDoData(
                id = args.currentItem.id,
                title = title,
                priority = priority,
                description = desc
            )
            mToDoViewModel.updateData(updatedItem)
            Toast.makeText(
                requireContext(),
                R.string.successful_updated,
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(
                requireContext(),
                R.string.please_fill_all_details,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}