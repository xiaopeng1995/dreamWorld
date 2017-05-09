package online.xpgame.userpreOperations;

import online.xpgame.api.AbstractResource;
import online.xpgame.api.userApi.UserEntity;
import online.xpgame.mysql.DataMySqlStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by xiaopeng on 2017/5/9.
 */
public class UserInfo extends AbstractResource {
    // Logger
    private static final Logger logger = LoggerFactory.getLogger(UserInfo.class);

    public UserInfo(DataMySqlStorage mysql) {
        super(mysql);
    }

    /**
     * 登陆验证
     * @param name 用户名
     * @param pwe 密码
     * @return 信息
     */
    public void login(String name ,String pwe) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(pwe);
        userEntity.setUpdate_date(new Date());
        Boolean ac = mysql.insertUserAccount(userEntity);
        logger.info("退出");
    }

    /**
     * 注册验证
     * @param name 用户名
     * @param pwe 密码
     */
    public void registered(String name ,String pwe) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(pwe);
        userEntity.setUpdate_date(new Date());
        Boolean ac = mysql.insertUserAccount(userEntity);
        logger.info("用户:{},注册结果:{}",name,ac);
    }
}
