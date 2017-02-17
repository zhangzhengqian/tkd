package com.lc.zy.ball.app.service.example;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.lc.zy.ball.app.controller.HubController;
import com.lc.zy.common.bean.Auth;
import com.lc.zy.common.bean.ClientRequest;
import com.lc.zy.common.bean.KeyValueEntity;
import com.lc.zy.common.bean.Reason;
import com.lc.zy.common.bean.Success;
import com.lc.zy.common.util.AppRequestUtil;
import com.lc.zy.common.util.MyGson;

/**
 * 例子程序
 * 
 * @author liangc
 */
// 指定 service 的名字，即 { "service":"foo" ... }
@Service("foo")
// 此注解标明，这个service的方法，默认是需要验证 token 有效性的
// 如果在这个类中某些方法需要跳过验证，则在方法上增加此注解并指定值为 false，见 bar 方法
@Auth
public class FooService {

    private static Logger logger = LoggerFactory.getLogger(HubController.class);

    Gson gson = MyGson.getInstance();

    /**
     * 所有的接口方法，都用统一的输入参数 ClientRequest 其中的 ClientRequest.params 为具体的业务参数
     * 本例中，我们假设 bar 方法需要参数 name 和 age 成功时返回 hello world
     * 
     * @param body
     * @return "hello world"
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    // 被调用的 method ，即 { "service":"foo","method":"bar" ... }
    @Auth(false)
    public Success bar(ClientRequest request) throws IllegalAccessException, InstantiationException,
            InvocationTargetException, IntrospectionException {
        String sn = request.getSn();
        String name = AppRequestUtil.getParameter(request, "name");
        // Integer age = AppRequestUtil.getParameterInteger(request, "age");

        // Object obj = AppRequestUtil.getParameterObject(request, "obj");
        // logger.debug("from foo obj ==> {}",obj.getClass());
        // List<FooBean> list = AppRequestUtil.getParameterList(request, "obj",
        // FooBean.class);
        // List<FooBean> list = gson.fromJson(gson.toJson(obj), new
        // TypeToken<List<FooBean>>(){}.getType());
        // for( FooBean o : list){
        // logger.debug("from foo =====> {}", o );
        // }

        // 假设 name 必填
        if (StringUtils.isEmpty(name)) {
            // 异常时，success 对象的第二个参数，必须为 Reason 对象
            // Reason 第一个参数为异常编码（自己定义），第二个参数为异常描述，可显示给前端
            return new Success(sn, false, new Reason("foo_1001", "name属性必填"));
        }
        logger.debug("request={}", request);
        // TODO Sth ...
        return new Success(sn, true, new KeyValueEntity("bar", "hello world"));
    }

}