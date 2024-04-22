package etty.qweinke.qwrewards.repository

import etty.qweinke.qwrewards.repository.model.PlayerRewards
import java.util.UUID

interface DBMSRepository {
    fun initTables(): Boolean

    fun setPlayerData(uuid: UUID, reward: String, amount: Int): Int

    fun updateReward(uuid: UUID, reward: String, amount: Int): Boolean

    fun getPlayerData(uuid: UUID): PlayerRewards?

    fun getRewardAmount(uuid: UUID, reward: String): Int
}