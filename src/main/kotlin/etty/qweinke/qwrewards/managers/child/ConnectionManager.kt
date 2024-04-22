package etty.qweinke.qwrewards.managers.child

import etty.qweinke.qwrewards.managers.Manager
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class ConnectionManager private constructor() : Manager {
    private val ip: String
    private val db: String
    private val username: String
    private val password: String
    private val port: Int
    private val poolSize: Int

    private val pool: BlockingQueue<Connection>
    private val sourceConnections: MutableList<Connection>

    init {
        val config = ConfigManager.getInstance("config.yml").config!!

        db = config.getString("database.name") ?: "rewards"
        username = config.getString("database.username") ?: "root"
        password = config.getString("database.password") ?: "password"
        ip = config.getString("database.port") ?: "localhost"
        port = config.getInt("database.port")
        poolSize = config.getInt("database.connections")

        pool = ArrayBlockingQueue(poolSize)
        sourceConnections = ArrayList(poolSize)
    }

    companion object {
        private var instance: ConnectionManager? = null

        fun getInstance(): ConnectionManager {
            if (instance == null) instance = ConnectionManager()
            return instance!!
        }
    }

    override fun init(): Boolean {
        for (i in 0..poolSize) {
            val connection: Connection = open()

            val proxyConnection = Proxy.newProxyInstance(
                ConnectionManager::class.java.getClassLoader(), arrayOf<Class<*>>(Connection::class.java)
            ) { proxy: Any?, method: Method, args: Array<Any?> ->
                if (method.name == "close") pool.add(
                    proxy as Connection
                ) else method.invoke(connection, *args)
            } as Connection

            sourceConnections.add(connection)
            pool.add(proxyConnection)
        }

        return true
    }

    override fun shutdown(): Boolean {
        return try {
            sourceConnections.forEach(Connection::close)
            return true
        } catch (e: SQLException) { false }
    }

    private fun open(): Connection {
        //TODO типы подключения
        return try {
            DriverManager.getConnection(
                "jdbc:mysql://${ip}:${port}/${db}",
                username,
                password
            )
        } catch (e: SQLException) {
            e.printStackTrace()
            throw e
        }
    }

    fun get(): Connection {
        return try { pool.take() } catch (e: InterruptedException) { throw e }
    }
}