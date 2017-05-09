package online.xpgame;

import online.xpgame.mysql.DataMySqlStorage;
import online.xpgame.mysql.manager.ConnectionManager;

import online.xpgame.resource.AbstractResource;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaopeng on 2017/5/8.
 */
public class Gmain {
    private static final Logger logger = LoggerFactory.getLogger(Gmain.class);
    private static PropertiesConfiguration mysqlConfig;
    private static  AbstractResource abstractResource;
    public static void main(String[] args) throws ConfigurationException {

        if (args.length > 0) {
            mysqlConfig = new PropertiesConfiguration(args[0]);
        } else {
            mysqlConfig = new PropertiesConfiguration("config/mysql.properties");
        }
        ConnectionManager connectionManager = ConnectionManager.getInstance();
        DataMySqlStorage mySqlStorage = new DataMySqlStorage();
        //初始化mysql连接池
        //Mysql storage
        logger.debug("Initializing Mysql storage ...");
        try {
            connectionManager.init(mysqlConfig);
            mySqlStorage.init(connectionManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        abstractResource=new AbstractResource(mySqlStorage);
    }
}
