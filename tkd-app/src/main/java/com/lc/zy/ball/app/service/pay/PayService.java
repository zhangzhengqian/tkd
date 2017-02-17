package com.lc.zy.ball.app.service.pay;

import com.lc.zy.ball.domain.oa.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PayService {
	
	private PayRepository payRepository;
	
	@Autowired
	public PayService(PayRepository payRepository){
		this.payRepository = payRepository;
	}
	
	public void payNotify(String tradeNo, String number, Integer payType,String price,Order order) throws Exception {
		payRepository.payNotify(tradeNo, number, payType,price,order);
    }
	
    /**
     * 
     * <根据流水号查询订单><功能具体实现>
     *
     * @create：2015年9月22日 上午11:19:37
     * @author： sl
     * @param tradeNo
     * @return
     * @throws Exception
     */
    public Order orderByTradeNo(String tradeNo,String price,String number, Integer payType) throws Exception{
    	return payRepository.orderByTradeNo(tradeNo,price,number,payType);
    }

}
