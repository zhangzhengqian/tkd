package com.lc.zy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lc.zy.ball.domain.oa.mapper.OrderMapper;
import com.lc.zy.ball.domain.oa.po.Order;
import com.lc.zy.ball.domain.oa.po.OrderCriteria;

public class checkBill {
	private static BufferedReader br;
	private static String paths[] = { "classpath:spring/applicationContext.xml" };
	public static ApplicationContext ctx = null;
	public static void main(String[] args) throws Exception {
		ctx = new ClassPathXmlApplicationContext(paths);
		OrderMapper mapper = ctx.getBean(OrderMapper.class);
		File file = new File("E:/bill.txt");
		br = new BufferedReader(new FileReader(file));		
		String temp=null;
        temp=br.readLine();
        BigDecimal total = new BigDecimal(0);
        while(StringUtils.isNotEmpty(temp)){
        	String orderNos[] = temp.split("_",-1);
        	String orderNo = orderNos[0];
        	if(StringUtils.isEmpty(orderNos[1])){
        		temp=br.readLine();
        		continue;
        	}
        	new BigDecimal(orderNos[1]);
        	OrderCriteria criteria = new OrderCriteria();
            OrderCriteria.Criteria cri = criteria.createCriteria();
            cri.andTradeNoEqualTo(orderNo);
            List<Order> orders = mapper.selectByExample(criteria);
            if(orders.size()==0){
            	System.out.println(orderNo+"_"+orderNos[1]);
            }else if(orders.size()>1){
            	System.out.println(orderNo+"=");
            }else{
            	 Order order = orders.get(0);
                 total = total.add(new BigDecimal(order.getFinalFee()));
                 if(new BigDecimal(orderNos[1]).compareTo(new BigDecimal(order.getFinalFee()).divide(new BigDecimal(100)))!=0){
                	 System.out.println(orderNo+"_"+orderNos[1]+"_"+new BigDecimal(order.getFinalFee()).divide(new BigDecimal(100)));
                 }
            }
            temp=br.readLine();
        }
        System.out.println(total.toString());
        br.close();
	}
}
