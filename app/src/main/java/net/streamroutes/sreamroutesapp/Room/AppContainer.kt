package net.streamroutes.sreamroutesapp.Room

import android.content.Context

interface AppContainer {
    val itemsRepository: ItemsRepository
}


class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}