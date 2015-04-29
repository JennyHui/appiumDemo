package me.jenny.Public;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class LoggerControler {
//    private static String path = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
    private static Logger logger = null;
    private static LoggerControler logg = null;

    public static LoggerControler getLogger(Class<?> T) {
        if (logger == null) {
            Properties props = new Properties();

            try {
                String envPaht = "./src/me/jenny/Public/log4j.properties";
                InputStream is = new FileInputStream(envPaht);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }

            PropertyConfigurator.configure(props);
            logger = Logger.getLogger(T);
            logg = new LoggerControler();
        }
        return logg;
    }

    /**
     * 重写logger方法
     */
    public void info(String msg) {
        logger.info(msg);
//        Reporter.log("Reporter:" + getTime() + "===>" + msg);
    }

    public void debug(String msg) {
        logger.debug(msg);
//        Reporter.log("Reporter:" + getTime() + "===>" + msg);
    }

    public void warn(String msg) {
        logger.warn(msg);
//        Reporter.log("Reporter:" + getTime() + "===>" + msg);
    }

    public void error(String msg) {
        logger.error(msg);
//        Reporter.log("Reporter:" + getTime() + "===>" + msg);
    }
}
