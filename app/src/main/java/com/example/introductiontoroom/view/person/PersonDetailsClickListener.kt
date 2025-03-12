package com.example.introductiontoroom.view.person

import com.example.introductiontoroom.data.model.PersonEntity

interface PersonDetailsClickListener {
    fun onEditPersonClick(personEntity:PersonEntity)
    fun onDeletePersonClick(personEntity:PersonEntity)

}