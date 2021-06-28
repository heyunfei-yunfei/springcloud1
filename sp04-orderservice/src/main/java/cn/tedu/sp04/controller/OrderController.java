package cn.tedu.sp04.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId){
        Order order = orderService.getOrder(orderId);
        return JsonResult.ok().data(order);
    }


    @PostMapping("/addOrder")
    public JsonResult<?> addOrder(){
        //写死一个订单数据
        Order order = new Order();
        order.setId("19221he");
        order.setUser(new User(8,null,null));
        order.setItems(Arrays.asList(new Item[]{
                new Item(1,"商品1",1),
                new Item(2,"商品2",2),
                new Item(3,"商品3",3),
                new Item(4,"商品4",4),
                new Item(5,"商品5",5),
        }));
        orderService.addOrder(order);
        return JsonResult.ok().msg("添加订单成功");
    }
}
