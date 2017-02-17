package com.lc.zy.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sport on 16/1/12.
 */
public class MapperOnCacheExclude {

    private static Logger logger = LoggerFactory.getLogger(MapperOnCacheSupport.class);

    private static Map<String,Boolean> excludeMap = new HashMap<String,Boolean>();

    static {
        excludeMap.put("Order", true);
        excludeMap.put("OrderAlarm", true);
        excludeMap.put("OrderBill", true);
        excludeMap.put("OrderBillItem", true);
        excludeMap.put("OrderItem", true);
        excludeMap.put("OrderLog", true);
        excludeMap.put("OrderSms", true);
        logger.info("exclude_cache_mapper ==> "+excludeMap.toString());
    }

    protected boolean isExclude(Class<?> clazz){
        String k = clazz.getSimpleName();
        Boolean b = excludeMap.get(k);
        if(b!=null) {
            logger.debug("mapper_on_cache_exclude : "+k+" = "+b.booleanValue());
            return b;
        }
        return false;
    }


}
