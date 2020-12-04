package com.pm.imain.bean;

/**
 * @author pm
 * @date 2019/11/15
 * @email puming@zdsoft.cn
 */
public class Token {
    /**
     * token : {"ctime":1573811102,"expire":7200,"info":"a1748b816069d4d6dbe185ad2ca5f2b6597b77b1"}
     */

    private TokenBean token;

    public TokenBean getToken() {
        return token;
    }

    public void setToken(TokenBean token) {
        this.token = token;
    }

    public static class TokenBean {
        /**
         * ctime : 1573811102
         * expire : 7200
         * info : a1748b816069d4d6dbe185ad2ca5f2b6597b77b1
         */

        private int ctime;
        private int expire;
        private String info;

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
