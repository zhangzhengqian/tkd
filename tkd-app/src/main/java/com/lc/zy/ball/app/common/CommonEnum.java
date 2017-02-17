package com.lc.zy.ball.app.common;


public class CommonEnum {

    public enum Recommend {
        SALES_VOLUME("SALES_VOLUME", 1), // 销量最多
        COLLECT("COLLECT", 2), // 收藏列表
        COMMENT("COMMENT", 3), // 评价最好
        PRICE_LOWEST("PRICE_LOWEST", 4), // 价格最低
        PRICE_HIGHEST("PRICE_HIGHEST", 5)// 价格最高
        ;

        private Recommend() {
        }

        private Recommend(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        private String name;

        private Integer value;

        public static String getName(String value) {
            for (Recommend st : Recommend.values()) {
                if (value.equals(st.getName())) {
                    return st.getName();
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

    }
}
