package cn.tedu.sp02.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;
    /*
    JsonResult用来封装返回给客户端的数据
    1）-code 状态码  200
    2) -msg 提示消息 null
    3）-data 数据对象 List<Item>
    4) {"code":0,"msg":null,"data":[{},{},{}]}
     */
    //获取商品订单的商品列表
    @GetMapping("/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws InterruptedException {
        List<Item> items = itemService.getItem(orderId);
        JsonResult result = JsonResult.ok().data(items);

        //随机的延迟代码
        //90%概率会执行延迟代码
        if (Math.random()<0.9){
            //随机延迟时长0到5秒
            int t = new Random().nextInt(5000);
            log.info("随机延迟："+t+"秒");
            Thread.sleep(t);
        }

        return result;
    }

    //减少商品库存
    /*
    @RequestBody
    接收客户端提交的参数
     */
    @PostMapping("/decreaseNumber")
    public  JsonResult<?> decreaseNumber(@RequestBody List<Item> items){
        itemService.decreaseNumber(items);
        return JsonResult.ok().msg("减少商品库存成功");
    }
}
