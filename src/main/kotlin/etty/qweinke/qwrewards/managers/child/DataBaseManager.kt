package etty.qweinke.qwrewards.managers.child

import etty.qweinke.qwrewards.managers.Manager
import etty.qweinke.qwrewards.repository.DBMSRepository
import java.util.UUID

class DataBaseManager : Manager {
    val repository: DBMSRepository

    override fun init(): Boolean {
        TODO("Not yet implemented")
    }

    override fun shutdown(): Boolean {
        TODO("Not yet implemented")
    }

    fun updatePlayerRewards(playerRewards)

    fun getPlayerRewards(uuid: UUID)
}