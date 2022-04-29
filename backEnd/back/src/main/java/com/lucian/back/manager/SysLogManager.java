package com.lucian.back.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lucian.back.bean.translate.SysLog2BO;
import com.lucian.back.bean.translate.SysLogBO2VO;
import com.lucian.back.bean.vo.SysLogIndexVO;
import com.lucian.back.query_wrapper.SysLogQuery;
import com.lucian.common.bean.bo.SysLogBO;
import com.lucian.common.bean.entity.SysLog;
import com.lucian.common.service.SysLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lingxiangdeng
 */
@Component
public class SysLogManager {
    SysLogService sysLogService;

    @Autowired
    public void setSysLogService(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    SysLog2BO sysLog2BO;

    @Autowired
    public void setSysLog2BO(SysLog2BO sysLog2BO) {
        this.sysLog2BO = sysLog2BO;
    }

    SysLogBO2VO sysLogBO2VO;

    @Autowired
    public void setSysLogBO2VO(SysLogBO2VO sysLogBO2VO) {
        this.sysLogBO2VO = sysLogBO2VO;
    }

    /**
     * 查询log list
     * @param sysLogQuery 查询条件
     * @return log list
     */
    public IPage<SysLogIndexVO> queryListByPage(SysLogQuery sysLogQuery){
        if (sysLogQuery == null) {
            sysLogQuery = new SysLogQuery();
        }
        Page<SysLog> page = new Page<>(sysLogQuery.getCurrent(), sysLogQuery.getSize());
        Page<SysLog> logPage = sysLogService.page(page, sysLogQuery.getQueryWrapper());
        List<SysLogBO> sysLogBOS = sysLog2BO.translateList(logPage.getRecords());
        Page<SysLogBO> logBOPage = new Page<>();
        BeanUtils.copyProperties(logPage, logBOPage);
        logBOPage.setRecords(sysLogBOS);
        return logBOPage.convert(sysLogBO -> sysLogBO2VO.translate(sysLogBO));
    }
}
