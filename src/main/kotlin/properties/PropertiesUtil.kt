package properties

import java.util.Properties

object PropertiesUtil {
    fun loadProperty(propertiesURL: String?): Properties?{
        return try {
            val properties = Properties()
            val inputStream = PropertiesUtil::class.java
                .classLoader.getResourceAsStream(propertiesURL)
            properties.load(inputStream)
            properties
        } catch (e: Exception){
            //e.printStacktrace()
            null
        }
    }
}