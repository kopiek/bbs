package com.top.sample.infrastructrue.web;

import com.top.sample.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhikang
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SnowFlake snowFlake;

    @GetMapping("/generate/id")
    public long generateId() {
        return snowFlake.nextId();
    }
}
