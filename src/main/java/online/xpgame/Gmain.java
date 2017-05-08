package online.xpgame;

import online.xpgame.mysql.DataMySqlStorage;
import online.xpgame.mysql.manager.ConnectionManager;
import online.xpgame.userApi.UserEntity;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by xiaopeng on 2017/5/8.
 */
public class Gmain {
    private static final Logger logger = LoggerFactory.getLogger(Gmain.class);
    private static PropertiesConfiguration mysqlConfig;

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
        UserEntity userEntity=new UserEntity();
        userEntity.setName("qwe");
        userEntity.setPassword("sss");
        userEntity.setUpdate_date(new Date());
        Boolean ac=mySqlStorage.insertUserAccount(userEntity);
        System.out.println(ac);
        logger.info("退出");
    }
}
