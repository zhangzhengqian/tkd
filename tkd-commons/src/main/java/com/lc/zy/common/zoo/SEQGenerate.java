package com.lc.zy.common.zoo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liangc
 * 一个机遇序列的id生成器，数字递增
 * 每秒可以生成1万个
 * 可用于全局分布式系统
 */
@Component("sEQGenerate")
public class SEQGenerate extends ConnectionWatcher {

	private static Logger logger = LoggerFactory.getLogger(SEQGenerate.class);

	@Autowired
	private ConnectionWatcher connectionWatcher = null;
	
	public ConnectionWatcher getConnectionWatcher() {
		return connectionWatcher;
	}
	public void setConnectionWatcher(ConnectionWatcher connectionWatcher) {
		this.connectionWatcher = connectionWatcher;
	}
	
	/**
	 * 生成球友号，8位数字
	 * @return
	 * @throws Exception
	 */
	public String genQiuyouNumber() throws Exception{
		return (10000000L+getSequence("qiuyounum"))+"";
	}
	
	/**
	 * 生成 订单id
	 * @return
	 * @throws Exception
	 */
	public String genOrderId() throws Exception{
		return (1000000000L+getSequence("orderid"))+"";
	}

	/**
	 * 生成 订单id
	 * @return
	 * @throws Exception
	 */
	public String genLiveOrderId() throws Exception{
		return (2000000000L+getSequence("liveorderid"))+"";
	}
	
	/**
	 * 支付流水号
	 * @return
	 * @throws Exception
	 */
	public String genTradeNo() throws Exception{
		return (30000000000L+getSequence("tradeno"))+"";
	}
	
	/**
     * 生成约球号
     * @return
     * @throws Exception
     */
	public String genBallNumber() throws Exception{
		return (10000000L+getSequence("ballNum"))+"";
	}

	/**
	 * 生成群号
	 * @return
	 * @throws Exception
	 */
    public String genGroupId() throws Exception{
        return (1000000L+getSequence("groupid"))+"";
    }
    
    /**
	 * 生成道馆号
	 * @return
	 * @throws Exception
	 */
    public String genDgNumber() throws Exception{
        return (1000000L+getSequence("dgnum"))+"";
    }
	
	/**
	 * 获取球友号，一个8位开始的自增序列;
	 * @param seed 种子目录，只能由26个英文字母组成
	 * @return
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 */
	public Long getSequence(String seed) throws Exception{
		if(connectionWatcher.STARTUP){
			String s = "/seq_seed_"+seed;
			try {
				String path = connectionWatcher.zk().create(s+"/qn:", null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
				String seqStr = path.split(":")[1];
				Long seq = Long.parseLong(seqStr);
				//logger.debug("seq={}",seq);
				return seq;
			}catch(NoNodeException e1){
				logger.info("zookeeper 种子节点不存在，创建种子节点:/seed, loop");
				String path = connectionWatcher.zk().create(s, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				logger.info("种子节点创建成功:{}",path);
				getSequence(seed);
			}catch(Exception e){
				logger.error("zoo_generate_seq_error",e);
				throw e;
			}
		}else{
			throw new Exception("zoo 服务没有启动，请确认正确配置了 configs 中的 [zoo.server]");
		}
		return null;
	}

}
