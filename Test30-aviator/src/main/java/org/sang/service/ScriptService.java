package org.sang.service;

import com.googlecode.aviator.AviatorEvaluator;
import lombok.extern.slf4j.Slf4j;
import org.sang.util.IDCardUtil;
import org.sang.util.IpUtil;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScriptService {

    static {
        try {
            AviatorEvaluator.addStaticFunctions("IDCardUtil", IDCardUtil.class);
            AviatorEvaluator.addStaticFunctions("IpUtil", IpUtil.class);
        } catch (Exception e) {
            log.error("load function error:", e);

        }
    }

    /**
     * 动态脚本填充数据
     * @param script
     * @param args
     */
    public void fillData(String script, Object... args) {
        AviatorEvaluator.execute(script, AviatorEvaluator.newEnv(args), true);
    }
}
