package com.unionpay.cloudatlas.avalon.lambda.cart;

/**
 * 对 Sku 总价是否超出2000作为判断标准
 */
public class SkuTotalPricePredicate implements SkuPredicate{
    @Override
    public boolean test(Sku sku) {
        return sku.getTotalPrice() > 2000;
    }
}
