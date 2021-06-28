package cn.tedu.sp03.service;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    //配置中如果找不到这个属性，注入失败，项目无法启动
    @Value("${sp.user-service.users}")
    private String userJson;




    @Override
    public User getUser(Integer id) {
        //userJson --->List<User>
        log.info("获取用户, id"+id);
        List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {});

        for (User user:list) {
            if (user.getId().equals(id)){
                return user;
            }
        }


        //返回写死的用户数据
        return new User(id,"用户名"+id,"密码"+id);

    }

    @Override
    public void addScore(Integer id, Integer score) {
    log.info("增加用户积分，id="+id+",score="+score);
    }
}
