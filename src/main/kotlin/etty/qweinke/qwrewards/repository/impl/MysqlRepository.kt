package etty.qweinke.qwrewards.repository.impl

import etty.qweinke.qwrewards.managers.child.ConnectionManager
import etty.qweinke.qwrewards.repository.DBMSRepository
import etty.qweinke.qwrewards.repository.model.PlayerRewards
import idfc.RewardType
import java.sql.PreparedStatement
import java.sql.SQLException
import java.util.*

class MysqlRepository : DBMSRepository {
    val connectionManager = ConnectionManager.getInstance()

    override fun initTables(): Boolean {
        val query = """CREATE TABLE IF NOT EXISTS `rewards` (
            `uuid` VARCHAR(36),
            `reward` VARCHAR(128),
            `amount` INTEGER DEFAULT 0,
            PRIMARY KEY (`uuid`, `reward`)
        )"""

        return createStatement(query).execute()
    }

    override fun setPlayerData(uuid: UUID, reward: String, amount: Int): Int {
        val query = """
            UPDATE `user_rewards` 
            SET `amount` = ? 
            WHERE `uuid` = ? AND `reward` = ?;
        """
        val statement = createStatement(query)

        statement.setInt(1, amount)
        statement.setString(2, uuid.toString())
        statement.setString(3, reward)

        return statement.executeUpdate()
    }
    // TODO CHECK WORK
    override fun updateReward(uuid: UUID, reward: String, amount: Int): Int {
        val query = """
            UPDATE `user_rewards` 
            SET `amount` = `amount` + amount 
            WHERE `uuid` = ? AND `reward` = ?;
        """
        val statement = createStatement(query)

        statement.setInt(1, amount)
        statement.setString(2, uuid.toString())
        statement.setString(3, reward)

        return statement.executeUpdate()
    }

    override fun getPlayerData(uuid: UUID): PlayerRewards? {
        TODO("Not yet implemented")
    }

    override fun getRewardAmount(uuid: UUID, reward: String): Int {
        val query = """
           SELECT `amount` FROM user_rewards
           WHERE `uuid` = ? AND `reward` = ? 
        """
        val statement = createStatement(query)

        statement.setString(1, uuid.toString())
        statement.setString(2, reward)

        val result = statement.executeQuery()

        return result.getInt("amount")
    }

    private fun createStatement(query: String): PreparedStatement {
        val connection = connectionManager.get()
        val statement = try {
            connection.prepareStatement(query)
        } catch (e: SQLException) { throw e }

        return statement
    }
}