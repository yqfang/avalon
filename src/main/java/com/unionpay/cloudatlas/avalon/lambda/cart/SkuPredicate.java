package com.unionpay.cloudatlas.avalon.lambda.cart;

@FunctionalInterface
public interface SkuPredicate {
    /**
     * 选择判断标准
     * @param sku
     * @return
     */
    boolean test(Sku sku);
    default void other() {
        System.out.println("ok");
    }
}
