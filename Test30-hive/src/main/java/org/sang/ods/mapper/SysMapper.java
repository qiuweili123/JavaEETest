package org.sang.ods.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

public interface SysMapper<T>  extends BaseMapper<T> {
    default LambdaQueryWrapper<T> getLambdaQuery(){
      return Wrappers.lambdaQuery();
    }
}
