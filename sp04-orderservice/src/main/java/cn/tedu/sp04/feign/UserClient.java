package cn.tedu.sp04.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {
    //获取用户
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable Integer userId);

    //增加用户积分
    @GetMapping("/{userId}/score")
    JsonResult<?> addScore(@PathVariable Integer userId,@RequestParam("score") Integer score);
}
