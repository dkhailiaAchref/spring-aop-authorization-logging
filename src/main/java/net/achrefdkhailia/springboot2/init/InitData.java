package net.achrefdkhailia.springboot2.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *refer to :
 * https://www.danvega.dev/blog/2017/04/07/spring-boot-command-line-runner/
 * Nb: its possible  to create Command Line Runner , right in the main application class
 */
@Component
public class InitData implements CommandLineRunner {

    private final Logger logger =  LoggerFactory.getLogger(InitData.class);

    @Autowired
    DataInitializer initializer;

    @Override
    public void run(String... arg0) throws Exception {
        logger.info(
                "\n ******** Initializing Data ***********");
        initializer.initData();
    }

}
