package chap5;

/**
 * Created by hjy on 18-1-31.
 */
public final class Product {    //确保无子类
    private final String no;    //私有属性，不会被其他对象获取
    private final String name;  //final保证属性不会被2次赋值
    private final double price;

    public Product(String no, String name, double price) {  //在创建对象时，必须指定数据
        super();        //因为创建之后无法进行修改
        this.no = no;
        this.name = name;
        this.price = price;
    }


    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
