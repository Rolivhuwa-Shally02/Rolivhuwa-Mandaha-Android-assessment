package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData

class EngineersFragment : Fragment() {
    private lateinit var binding: FragmentEngineersBinding
    private lateinit var engineersAdapter: EngineersRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpEngineersList(MockData.engineers)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }
// replaced te existing override function with this so that the list can be sorted based on their category
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_years -> {
                engineersAdapter.sortByYears()
                Toast.makeText(requireContext(), "sorted by year", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.action_coffees -> {
                engineersAdapter.sortByCoffee()
                Toast.makeText(requireContext(), "sorted by coffees", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.action_bugs -> {
                engineersAdapter.sortByBugs()
                Toast.makeText(requireContext(), "sorted by Bugs", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    return true
    }

    private fun setUpEngineersList(engineers: List<Engineer>) {
        engineersAdapter = EngineersRecyclerViewAdapter(engineers) {
            goToAbout(it)
        }
        binding.list.adapter = engineersAdapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    private fun goToAbout(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)
        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}