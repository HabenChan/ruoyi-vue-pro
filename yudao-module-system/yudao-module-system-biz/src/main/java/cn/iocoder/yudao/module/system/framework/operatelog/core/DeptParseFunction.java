package cn.iocoder.yudao.module.system.framework.operatelog.core;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import com.mzt.logapi.service.IParseFunction;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static cn.iocoder.yudao.module.system.enums.operatelog.SysParseFunctionNameConstants.GET_DEPT_BY_ID;

/**
 * 管理员名字的 {@link IParseFunction} 实现类
 *
 * @author HUIHUI
 */
@Slf4j
@Component
public class DeptParseFunction implements IParseFunction {

    @Resource
    private DeptApi deptApi;

    @Override
    public String functionName() {
        return GET_DEPT_BY_ID;
    }

    @Override
    public String apply(Object value) {
        if (StrUtil.isEmptyIfStr(value)) {
            return "";
        }

        // 获取部门信息
        DeptRespDTO dept = deptApi.getDept(Long.parseLong(value.toString()));
        if (dept == null) {
            log.warn("[apply][获取部门{{}}为空", value);
            return "";
        }
        return dept.getName();
    }

}