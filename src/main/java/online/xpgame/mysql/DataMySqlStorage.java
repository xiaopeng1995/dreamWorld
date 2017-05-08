package online.xpgame.mysql;




import online.xpgame.mysql.manager.ConnectionManager;
import online.xpgame.userApi.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Mysql数据操作
 * Data MySql Storage
 */
public class DataMySqlStorage {

    private static final Logger logger = LoggerFactory.getLogger(DataMySqlStorage.class);

    private ConnectionManager pool;

    public void init(ConnectionManager pool) {
        this.pool = pool;
    }


    /*************************************************** UserAccount  ****************************/

    /**
     * 根据平台用户的ID,判断用户账户信息是否存在
     *
     * @param userId
     * @return
     */
    public boolean userinfoExists(String userId) {
        UserEntity Userinfo = null;
        Connection conn = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Integer count = 0;
        String sql = "select count(*) from xpgame_user where user_id = '" + userId + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(0);
            }
        } catch (SQLException e) {
            logger.error("查询用户ID为 {} 的用户账户信息是否存在时出错 {}", userId, e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.closeConnection(conn);
        }
        return count > 0;
    }
}
