CREATE DATABASE backend CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

    use backend;

CREATE TABLE product (
                         product_id BIGSERIAL
    , description VARCHAR (100) NOT NULL
    , price FLOAT
    , CONSTRAINT  pkProduct PRIMARY KEY  (product_id)
);

/--- DBFactory
package factorymethod

import properties.PropertiesUtil.loadProperty


class DBFactory {
    private  val _DB_FACTORY_PROPERTY_URL = "properties/DBFactory"
    private  val _DEFAULT_DB_CLASS_PROP = "defaultDBClass"
    fun getDBadapter(dbType: DBType?): IDBAdapter {
        return when (dbType) {
            DBType.MYSQL -> MySqlDBAdapter()
            DBType.ORACLE -> OracleDBAdapter()
            else -> throw IllegalArgumentException("No soportado")
        }
    }

    val defaultDBAdapter: IDBAdapter?
        get() = try {
            val property = loadProperty(_DB_FACTORY_PROPERTY_URL)
            val defaultDBClass = property!!.getProperty(_DEFAULT_DB_CLASS_PROP)
            Class.forName(defaultDBClass) as IDBAdapter
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
}

//----------------------
//--- ProductDAO
//----------------------
package factorymethod

import java.sql.Connection
import java.sql.PreparedStatement


class ProductDAO() {
    private val dbAdapter: IDBAdapter? = DBFactory().defaultDBAdapter

    fun findAllProducts(): List<Product>? {
        val connection: Connection? = dbAdapter!!.getConnection()
        val productList: MutableList<Product> = ArrayList()
        try {
            val statement: PreparedStatement = connection!!.prepareStatement(
                    "SELECT product_id, description, price " +
                            "  FROM product"
                )
            val results = statement.executeQuery()
            while (results.next()) {
                productList.add(
                    Product(
                        results.getLong(1),
                        results.getString(2), results.getDouble(3)
                    )
                )
            }
            return productList
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            try {
                connection!!.close()
            } catch (e: Exception) {
            }
        }
    }

    fun saveProduct(product: Product): Boolean {
        val connection: Connection? = dbAdapter!!.getConnection()
        try {
            val statement: PreparedStatement = connection!!
                .prepareStatement(
                    ("INSERT INTO Productos(idProductos,"
                            + "productName, productPrice) VALUES (?,?,?)")
                )
            statement.setString(2, product.description)
            statement.setDouble(3, product.price)
            statement.executeUpdate()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            try {
                connection!!.close()
            } catch (e: Exception) {
            }
        }
    }
}

//----------------------------------------
//--- properties/DBFactory
//----------------------------------------
defaultDBClass factorymethod.MySqlDBAdapter
