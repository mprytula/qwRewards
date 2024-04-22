package etty.qweinke.qwrewards.repository.impl

import idfc.RewardType
import etty.qweinke.qwrewards.repository.DBMSRepository
import etty.qweinke.qwrewards.repository.model.PlayerRewards
import java.util.*

class SqliteRepository : DBMSRepository {
    override fun initTables(): Boolean {
        TODO("Not yet implemented")
    }

    override fun updatePlayerData(uuid: UUID): PlayerRewards? {
        TODO("Not yet implemented")
    }

    override fun addReward(uuid: UUID, rewardType: RewardType): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeReward(uuid: UUID, rewardType: RewardType): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPlayerData(uuid: UUID): PlayerRewards? {
        TODO("Not yet implemented")
    }

    override fun getRewardAmount(uuid: UUID, rewardType: RewardType): Int {
        TODO("Not yet implemented")
    }
}