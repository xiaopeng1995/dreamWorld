package online.xpgame.mysql;




import online.xpgame.mysql.manager.ConnectionManager;
import online.xpgame.api.userApi.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

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
     * @param name 用户名
     * @return
     */
    public boolean userinfoExists(String name) {
        UserEntity Userinfo = null;
        Connection conn = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Integer count = 0;
        String sql = "select * from xpgame_user where name = '" + name + "'";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            logger.error("查询用户名为 {} 的用户账户信息是否存在时出错 {}", name, e);
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

    /**
     * 注册一条账户信息
     *
     * @param userAccount
     * @return
     */
    public boolean insertUserAccount(UserEntity userAccount) {
        Connection conn = pool.getConnection();
        PreparedStatement statement = null;
        int count = 0;
        String sql = "insert into xpgame_user(name,password,update_date) " +
                "values(?,?,?)";
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, userAccount.getName());
            statement.setString(2, userAccount.getPassword());
            statement.setDate(3, new java.sql.Date(userAccount.getUpdate_date().getTime()));

            count = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("保存用户ID为 {} 的用户账户信息时出错 {}", userAccount.getName(), e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.closeConnection(conn);
        }
        return count > 0;
    }
}
