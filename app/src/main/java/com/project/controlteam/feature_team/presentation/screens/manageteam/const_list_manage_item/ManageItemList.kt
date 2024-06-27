package com.project.controlteam.feature_team.presentation.screens.manageteam.const_list_manage_item

import com.project.controlteam.feature_team.presentation.screens.manageteam.ManageItem

object ManageItemList {

    // Const of Title and Description
    private const val TITLE_TRAINING_PLAN = "Training plans"
    private const val DESCRIPTION_TRAINING_PLAN = "Create training sessions"

    private const val TITLE_FINANCE = "Finance"
    private const val DESCRIPTION_FINANCE = "Manage your finance"

    // Manage Item
    private val TRAINING_PLAN = ManageItem(title = TITLE_TRAINING_PLAN, description = DESCRIPTION_TRAINING_PLAN)
    private val FINANCE = ManageItem(title = TITLE_FINANCE, description = DESCRIPTION_FINANCE)

    // List of Manage items
    fun getListManageItem(): List<ManageItem> {
        return listOf(
            TRAINING_PLAN,
            FINANCE
        )
    }
    
}