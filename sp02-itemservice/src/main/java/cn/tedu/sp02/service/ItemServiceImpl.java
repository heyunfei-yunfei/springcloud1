package cn.tedu.sp02.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    //获取订单的商品列表
    @Override
    public List<Item> getItem(String orderId) {
        log.info("获取商品列表，orderId="+orderId);
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1,"商品1",1));
        items.add(new Item(2,"商品2",2));
        items.add(new Item(3,"商品3",3));
        items.add(new Item(4,"商品4",4));
        items.add(new Item(5,"商品5",5));
        return items;
    }

    //减少商品库存
    @Override
    public void decreaseNumber(List<Item> items) {
        for (Item item:items) {
            log.info("减少库存：  "+item);
        }
    }
}
