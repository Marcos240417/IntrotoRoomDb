package com.example.introductiontoroom.introduction.view.person

import com.example.introductiontoroom.introduction.data.model.PersonEntity

interface PersonDetailsClickListener {
    fun onEditPersonClick(personEntity: PersonEntity)
    fun onDeletePersonClick(personEntity: PersonEntity)

}