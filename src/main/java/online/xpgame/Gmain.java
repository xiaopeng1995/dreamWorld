package online.xpgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xiaopeng on 2017/5/8.
 */
public class Gmain {
    private static final Logger logger = LoggerFactory.getLogger(Gmain.class);
    public static void main(String[] args) {

        System.out.println("hello word !");
        logger.info("hello word !.");
        try {
            Thread.sleep(90000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("退出");
    }
}
