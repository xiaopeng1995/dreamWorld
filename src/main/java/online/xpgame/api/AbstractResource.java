package online.xpgame.api;

import online.xpgame.mysql.DataMySqlStorage;

/**
 * Created by xiaopeng on 2017/5/9.
 */
public class AbstractResource {

    protected final DataMySqlStorage mysql;

    public AbstractResource(DataMySqlStorage mysql) {
        this.mysql = mysql;
    }
}
