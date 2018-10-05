package travel.health.contoller;
import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import travel.health.dao.HealthDao;

/**
 * @brief service health check
 * @create 2014.06.01
 * @author nocode
 */
@Controller
public class HealthController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Resource(name="HealthDao")
    private HealthDao healthDao;

    /* ##################################################### */
    /* ################## DATABASE (below) ################# */
    /* ##################################################### */

    /**
     * mysql master health
     * @return
     */
    @RequestMapping(value="/health/MysqlMasterDS")
    public @ResponseBody ResponseEntity<String> mysqlMasterDS() {
    	ResponseEntity<String> ret = new ResponseEntity<String>(HttpStatus.OK);
    	
        try {
        	healthDao.selectMysqlMasterDs();
        } catch (Exception e) {
        	log.error(ExceptionUtils.getStackTrace(e));
        	ret = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ret;
    }

    /**
     * mysql slave health
     * @return
     */
    @RequestMapping(value="/health/MysqlSlaveDS")
    public @ResponseBody ResponseEntity<String> mysqlSlaveDS() {
    	ResponseEntity<String> ret = new ResponseEntity<String>(HttpStatus.OK);

        try {
            healthDao.selectMysqlSlaveDs();
        } catch (Exception e) {
        	log.error(ExceptionUtils.getStackTrace(e));
        	ret = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ret;
    }


    /* ##################################################### */
    /* ################### WAS (below) ##################### */
    /* ##################################################### */

    /**
     * was health
     * @return
     */
    @RequestMapping(value="/health/was")
    public @ResponseBody ResponseEntity<String> batchWas() {
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
