package com.unionpay.cloudatlas.avalon.lambda.cart;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车服务
 */
public class CartService {

    // 加入到购物车中的商品信息
    private static List<Sku> cartSkuList = new ArrayList<Sku>() {
        {
            add(new Sku(654032, "无人机",
                    4999.00, 1,
                    4999.00, SkuCategoryEnum.ELECTRONICS));

            add(new Sku(642934, "VR一体机",
                    2299.00, 1,
                    2299.00, SkuCategoryEnum.ELECTRONICS));

            add(new Sku(645321, "纯色衬衫",
                    409.00, 3,
                    1227.00, SkuCategoryEnum.CLOTHING));

            add(new Sku(654327, "牛仔裤",
                    528.00, 1,
                    528.00, SkuCategoryEnum.CLOTHING));

            add(new Sku(675489, "跑步机",
                    2699.00, 1,
                    2699.00, SkuCategoryEnum.SPORTS));

            add(new Sku(644564, "Java编程思想",
                    79.80, 1,
                    79.80, SkuCategoryEnum.BOOKS));

            add(new Sku(678678, "Java核心技术",
                    149.00, 1,
                    149.00, SkuCategoryEnum.BOOKS));

            add(new Sku(697894, "算法",
                    78.20, 1,
                    78.20, SkuCategoryEnum.BOOKS));

            add(new Sku(696968, "TensorFlow进阶指南",
                    85.10, 1,
                    85.10, SkuCategoryEnum.BOOKS));
        }
    };

    /**
     * 获取商品列表
     * @return
     */
    public static List<Sku> getCartSkuList() {
        return cartSkuList;
    }

    /**
     * Version 1.0.0
     * 找出购物车中所有电子产品
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterElectronicsSkus(List<Sku> cartSkuList) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku: cartSkuList) {
            if (SkuCategoryEnum.ELECTRONICS.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 2.0.0
     * 根据传入商品类型参数，找出购物车中同类商品类型的商品列表
     * @param cartSkuList
     * @param category
     * @return
     */
    public static List<Sku> filterSkusByCategory(List<Sku> cartSkuList, SkuCategoryEnum category) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku: cartSkuList) {
            if (category.equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    /**
     * Version 3.0.0
     * 根据传入商品类型参数，找出购物车中同类商品类型的商品列表
     * @param cartSkuList
     * @param category
     * @return
     */
    public static List<Sku> filterSkus(List<Sku> cartSkuList, SkuCategoryEnum category, Double totalPrice, Boolean categoryOrPrice) {
        List<Sku> result = new ArrayList<>();
        if(categoryOrPrice) {
            for (Sku sku: cartSkuList) {
                if (category.equals(sku.getSkuCategory())) {
                    result.add(sku);
                }
            }
        } else {
            for (Sku sku: cartSkuList) {
                if (sku.getSkuPrice() > totalPrice) {
                    result.add(sku);
                }
            }
        }
        return result;
    }

    /**
     * Version 4.0.0
     * 根据不同的 SKU 判断标准，对 Sku 列表进行过滤
     * @param cartSkuList
     * @param predicate - 不同的 Sku 判断标准策略
     * @return
     */
    public static List<Sku> filterSkus(List<Sku> cartSkuList, SkuPredicate predicate) {
        List<Sku> result = new ArrayList<>();
        for (Sku sku: cartSkuList) {
            // 根据不同的 Sku 判断标准策略，对 Sku 进行判断
            if (predicate.test(sku)) {
                result.add(sku);
            }
        }
        return result;
    }
}