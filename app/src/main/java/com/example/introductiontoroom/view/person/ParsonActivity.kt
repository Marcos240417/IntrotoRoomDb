package com.example.introductiontoroom.view.person

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.introductiontoroom.data.model.PersonEntity
import com.example.introductiontoroom.databinding.ActivityMainBinding
import com.example.introductiontoroom.viewmodel.PersonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ParsonActivity : AppCompatActivity(), AddEditPersonFragment.AddEditPersonListener,
    PersonDetailsAdapter.PersonDetailsClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonDetailsAdapter
    private val personViewModel: PersonViewModel by viewModel() //  Koin injeta automaticamente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        attachUiListener()
        subscribeDataStreams()
    }

    private fun initVars() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonDetailsAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    private fun attachUiListener() {
        binding.floatingActionButton.setOnClickListener {
            showBottomSheet()
        }

        binding.searchcView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null)
                    onQueryChanged(newText)
                return true
            }
        })
    }

    private fun onQueryChanged(query: String) {
        personViewModel.getSearchedData(query)
    }

    private fun showBottomSheet(personEntity: PersonEntity? = null) {
        val bottomSheet = AddEditPersonFragment(this, personEntity)
        bottomSheet.show(supportFragmentManager, AddEditPersonFragment.Companion.TAG)
    }

    override fun onSavedBtnClicked(isUpdate: Boolean, personEntity: PersonEntity) {
        if (isUpdate) {
            personViewModel.updatePerson(personEntity)
        } else {
            personViewModel.addPerson(personEntity)
        }
    }

    override fun onEditPersonClick(personEntity: PersonEntity) {
        showBottomSheet(personEntity)
    }

    override fun onDeletePersonClick(personEntity: PersonEntity) {
        personViewModel.deletePerson(personEntity)
        personViewModel.deletePersonById(personEntity)
    }

    private fun subscribeDataStreams() {
        /*personViewModel.allPersons.observe(this, Observer { personList ->
            adapter.submitList(personList)
        })*/

        personViewModel.searchedPersons.observe(this, Observer { personList ->
            adapter.submitList(personList)
        })
    }
}

