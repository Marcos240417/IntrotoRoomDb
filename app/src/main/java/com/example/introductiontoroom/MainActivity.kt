package com.example.introductiontoroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.introductiontoroom.data.Person
import com.example.introductiontoroom.databinding.ActivityMainBinding
import com.example.introductiontoroom.viewadapter.PersonDetailsAdapter
import com.example.introductiontoroom.viewadapter.PersonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), AddEditPersonFragment.AddEditPersonListener,
    PersonDetailsAdapter.PersonDetailsClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonDetailsAdapter
    private val personViewModel: PersonViewModel by viewModel() //  Koin injeta automaticamente


    private lateinit var searchQueryLiveData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        attachUiListener()
        subscribeDataStreams()
    }

    private fun initVars() {
        //personViewModel = PersonViewModelFactory(PersonRepository(applicationContext)).create(PersonViewModel::class.java)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonDetailsAdapter(this)
        binding.recyclerView.adapter = adapter

        searchQueryLiveData = MutableLiveData("")
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
        searchQueryLiveData.postValue(query)
        personViewModel.getSearchedData(query)
    }

    private fun showBottomSheet(person: Person? = null) {
        val bottomSheet = AddEditPersonFragment(this, person)
        bottomSheet.show(supportFragmentManager, AddEditPersonFragment.TAG)
    }

    override fun onSavedBtnClicked(isUpdate: Boolean, person: Person) {
        if (isUpdate) {
            personViewModel.updatePerson(person)
        } else {
            personViewModel.addPerson(person)
        }
    }

    override fun onEditPersonClick(person: Person) {
        showBottomSheet(person)
    }

    override fun onDeletePersonClick(person: Person) {
        personViewModel.deletePerson(person)
        personViewModel.deletePersonById(person)
    }

    private fun subscribeDataStreams() {
        personViewModel.allPersons.observe(this, Observer { personList ->
            adapter.submitList(personList)
        })

        searchQueryLiveData.observe(this, Observer { query ->
            personViewModel.getSearchedData(query)
            personViewModel.searchedPersons.observe(this, Observer { personList ->
                adapter.submitList(personList)
            })
        })
    }
}

