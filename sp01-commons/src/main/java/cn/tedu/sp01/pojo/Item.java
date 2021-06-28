package cn.tedu.sp01.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private Integer id;
    private String name;//商品名称
    private Integer number;//商品数量

    public static void main(String[] args) {
        Item item = new Item(1, "name", 12);

    }
}
