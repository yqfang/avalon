package com.unionpay.cloudatlas.avalon.lambda.cart;

/**
 * 对 Sku 的商品类型为图书类的判断标准
 */
public class SkuBooksCategoryPredicate implements SkuPredicate {
    @Override
    public boolean test(Sku sku) {
        return sku.getSkuCategory().equals(SkuCategoryEnum.BOOKS);
    }
}
