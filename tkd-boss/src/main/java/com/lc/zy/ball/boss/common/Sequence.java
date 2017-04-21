package com.lc.zy.ball.boss.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lc.zy.common.util.SpringUtils;
import com.lc.zy.common.zoo.SEQGenerate;

public class Sequence {

	private static DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 
	 * <生成8位数字的id号>
	 *
	 * @create：2015年8月5日 下午2:55:45
	 * @author： sl
	 * @return
	 * @throws Exception
	 */
	public static String genQiuyouNumber() throws Exception {
		SEQGenerate seqGenerate = (SEQGenerate) SpringUtils.getBean("sEQGenerate");
		return seqGenerate.genQiuyouNumber();
	}
	
	/**
	 * 
	 * <生成8位数字的id号--道馆id>
	 *
	 * @create：2015年8月5日 下午2:55:45
	 * @author： sl
	 * @return
	 * @throws Exception
	 */
	public static String genDgNumber() throws Exception {
		SEQGenerate seqGenerate = (SEQGenerate) SpringUtils.getBean("sEQGenerate");
		return seqGenerate.genDgNumber();
	}

	/**
	 * 
	 * <利用时间戳生成订单ID或者流水ID等>
	 *
	 * @create：2015年8月5日 下午2:55:45
	 * @author： cl
	 * @return
	 * @throws Exception
	 */
	public static String getId() throws Exception {
		SEQGenerate seqGenerate = (SEQGenerate) SpringUtils.getBean("sEQGenerate");
		Long n = seqGenerate.getSequence("oss");
		return format.format(new Date()) + n; 
	}
}
