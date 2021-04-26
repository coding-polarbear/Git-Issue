package tech.purplebeen.core

import tech.purplebeen.core.db.Issue

data class Item(val viewType: MainViewType, val issue: Issue?)