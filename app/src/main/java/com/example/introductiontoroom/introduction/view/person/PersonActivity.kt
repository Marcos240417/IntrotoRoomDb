package com.example.introductiontoroom.introduction.view.person

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.introductiontoroom.introduction.data.model.PersonEntity
import com.example.introductiontoroom.databinding.ActivityMainBinding
import com.example.introductiontoroom.introduction.viewmodel.PersonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonActivity : AppCompatActivity(), AddEditPersonFragment.AddEditPersonListener,
    PersonDetailsClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonDetailsAdapter
    private val personViewModel: PersonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        attachUiListener()
        subscribeDataStreams()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
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
                newText?.let { onQueryChanged(it) }
                return true
            }
        })
    }

    private fun onQueryChanged(query: String) {
        personViewModel.getSearchedData(query)
    }

    private fun showBottomSheet(personEntity: PersonEntity? = null) {
        val bottomSheet = AddEditPersonFragment(this, personEntity)
        bottomSheet.show(supportFragmentManager, AddEditPersonFragment.TAG)
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
    }

    private fun subscribeDataStreams() {
        personViewModel.searchedPersons.observe(this) { personList ->
            adapter.submitList(personList)
        }
    }
}
