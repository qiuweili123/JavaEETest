package org.sang.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.sang.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component("springAdd")
public  class AddFunction extends AbstractFunction {

  @Resource
  private UserService userService;
  @Override
  public AviatorObject call(final Map<String, Object> env, final AviatorObject arg1,final AviatorObject arg2) {
    Number left = FunctionUtils.getNumberValue(arg1, env);
    Number right = FunctionUtils.getNumberValue(arg2, env);
    Map<String,Object> map=new HashMap<>();
    map.put("name","zhangsan");
    map.put("age",10);


    //返回map测试  可通过
    return FunctionUtils.wrapReturn(map);
   // return FunctionUtils.wrapReturn("{\"name\":\"zhang\",\"age\":\"10\"}"); //不能取值
 //{"name":}eturn FunctionUtils.wrapReturn(userService.add(left.intValue(),right.intValue()));
   // return FunctionUtils.wrapReturn(left.doubleValue() + right.doubleValue());
  }


  @Override
  public String getName() {
    return "add";
  }

}
