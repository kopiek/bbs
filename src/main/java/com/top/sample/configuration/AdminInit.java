package com.top.sample.configuration;

import com.top.sample.infrastructrue.po.UserPo;
import com.top.sample.infrastructrue.repository.UserRepository;
import com.top.sample.utils.DateUtils;
import com.top.sample.utils.EncodingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @author wangzhikang
 */
@Service
public class AdminInit {

    @Value("${admin.init.password}")
    private String password;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void insertSuperAdmin() {
        UserPo exsitAdmin = userRepository.getByUserName("superadmin666");
        if (Objects.isNull(exsitAdmin)) {
            exsitAdmin = new UserPo();
            exsitAdmin.setId(0L);
            exsitAdmin.setUserName("superadmin666");
            exsitAdmin.setCreateTime(DateUtils.now());
            exsitAdmin.setModifyTime(DateUtils.now());
            exsitAdmin.setPassword(EncodingUtils.md5Encoding(password));
            exsitAdmin.setStatus(1);
            userRepository.save(exsitAdmin);
        }
    }
}
