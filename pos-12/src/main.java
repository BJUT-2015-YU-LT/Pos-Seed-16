
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Administrator on 2016/1/5.
 */
public class main {
    public static void main(String[] args) {

        double sum = 0;
        double discount = 0;

        String[][] item1 = {{"ITEM000000", "可口可乐", "瓶", "3.00"},
                {"ITEM000000", "可口可乐", "瓶", "3.00"},
                {"ITEM000000", "可口可乐", "瓶", "3.00"},
                {"ITEM000000", "可口可乐", "瓶", "3.00"},
                {"ITEM000001", "雪碧", "瓶", "3.00","0.8"},
                {"ITEM000001", "雪碧", "瓶", "3.00"},
                {"ITEM000004", "电池", "个", "2.00"}};

        System.out.println("***商店购物清单***\n");
        System.out.println("************\n");
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("YYYY年MM月DD日 kk:mm:ss");
        System.out.println("打印时间:" + f.format(date));
        System.out.println();
        List<Item> Shoppingcart = new ArrayList<Item>();
        int i,j;

        if (Shoppingcart.size() == 0)
            Shoppingcart.add(new Item(item1[0]));
        for(i=1;i<7;i++) {
            for (j = 0; j < Shoppingcart.size(); j++) {
                if (Shoppingcart.get(j).getName().equals(item1[i][1])) {
                    Shoppingcart.get(j).setNum(Shoppingcart.get(j).getNum() + 1);
                    break;
                }
                if (j == Shoppingcart.size()-1) {
                    Shoppingcart.add(new Item(item1[i]));
                    break;
                }
            }
        }

        for (i = 0; i < Shoppingcart.size(); i++) {

            System.out.println("名称：" + Shoppingcart.get(i).getName() + "," + "数量：" + Shoppingcart.get(i).getNum() + Shoppingcart.get(i).getUnit() + "," + "单价：" +
                    Shoppingcart.get(i).getPrice() + "," + "(元)" + "," + "小计:"+ (float)+Shoppingcart.get(i).getTotal() + "(元)");

            sum += Shoppingcart.get(i).getTotal();
            discount+= Shoppingcart.get(i).getTotalDiscount();
        }
        System.out.println("************\n");
        System.out.println("总计：￥" + (float) sum);
        System.out.println("节省:" + "￥" + (float)discount);

    }
}
