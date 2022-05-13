package com.lucian.front.controller;

import com.lucian.common.response.CommonResult;
import com.lucian.front.bean.vo.BlogIndexVO;
import com.lucian.front.manager.SliderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author denglingxiang
 * @date 2022/05/13 16:39
 **/
@RestController
public class SliderController {
    SliderManager sliderManager;

    @Autowired
    public void setSliderManager(SliderManager sliderManager) {
        this.sliderManager = sliderManager;
    }

    @GetMapping("sliders")
    public CommonResult<List<BlogIndexVO>> index(){
        return CommonResult.successNoMessage(sliderManager.list());
    }
}
