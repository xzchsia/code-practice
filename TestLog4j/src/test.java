import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

public class test {
	
	// log4j version 1
	//static Logger logger = Logger.getLogger(test.class);
	
	// log4j version 2
	private static Logger logger = LogManager.getLogger(test.class.getName());

	public static void main(String[] args) {
		//PropertyConfigurator.configure("e:\\eclipse-workspace\\TestLog4j\\src\\log4j.properties");
		// TODO Auto-generated method stub

		// log4j version 1
		//BasicConfigurator.configure();
		//logger.setLevel(Level.DEBUG);
		
		
		//for (int i = 0; i < 5000; i++) {
            logger.trace("跟踪信息");
            logger.debug("调试信息");
            logger.info("输出信息");
            logger.warn("警告信息");
            logger.error("错误信息");
            logger.fatal("致命信息");
       // }


	}

}
