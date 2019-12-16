package com.unionpay.cloudatlas.avalon.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CartServiceTest {

    @Test
    public void getCartSkuList() {
    }

    @Test
    public void filterElectronicsSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 查找购物车中数码类产品
        List<Sku> result = CartService.filterElectronicsSkus(cartSkuList);
        System.out.println(JSON.toJSONString(result, true));
    }

    @Test
    public void filterSkusByCategory() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 查找购物车中数码类产品
        List<Sku> result = CartService.filterSkusByCategory(cartSkuList, SkuCategoryEnum.BOOKS);
        System.out.println(JSON.toJSONString(result, true));
    }

    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(cartSkuList, null, 2000.00, false);
        System.out.println(JSON.toJSONString(result, true));
    }

    @Test
    public void testFilterSkus1() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(cartSkuList, new SkuBooksCategoryPredicate());
        System.out.println(JSON.toJSONString(result, true));
    }

    @Test
    public void testFilterSkus2() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(cartSkuList, new SkuTotalPricePredicate());
        System.out.println(JSON.toJSONString(result, true));
    }
    @Test
    public void testFilterSkus3() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(cartSkuList, new SkuPredicate() {
            @Override
            public boolean test(Sku sku) {
              return sku.getSkuPrice() > 1000;
            }
        });
        System.out.println(JSON.toJSONString(result, true));
    }

    /**
     * <ol>
     * <li>将业务逻辑直接写死在代码里。</li>
     * <li>将单一维度的条件做为参数传入方法中。方法内根据参数进行业务逻辑实现。</li>
     * <li>将多个维度的条件做为参数传入方法中。业务实现需要根据不同的参数处理不同逻辑。</li>
     * <li>将业务逻辑封装为一个实体类，方法接受实体类为参数，方法内部调用实体类的处理逻辑。</li>
     * <li>调用方法时不在创建实体类，而是使用匿名函数的形式替代。</li>
     * <li>使用Lambda表达式替代匿名函数的形式，做为方法的参数。真正实现判断逻辑参数化传递。</li>
     * </ol>
     */
    @Test
    public void testFilterSkus4() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(cartSkuList, (Sku sku) -> sku.getSkuPrice() > 1000);
        System.out.println(JSON.toJSONString(result, true));
    }
    @Test
    public void testUrl() {
        String requestUrl = "http://172.17.237.9:38553/bdsc/console/routes/products/recommend";
        String moduleName = requestUrl.split("bdsc")[1].split("/")[1].trim();
        System.out.println(moduleName);
    }
}