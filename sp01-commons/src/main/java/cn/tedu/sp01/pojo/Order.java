package cn.tedu.sp01.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
//订单
public class Order {

    private String id;
    private User user;//哪一个用户的订单
    private List<Item> items;//订单中包含的商品列表
}
