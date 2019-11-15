package com.pm.amass.bean;

/**
 * @author pm
 * @date 2019/11/15
 * @email puming@zdsoft.cn
 */
public class Token {


    /**
     * result : 200
     * msg : 请求成功
     * data : {"token":{"ctime":1573811102,"expire":7200,"info":"a1748b816069d4d6dbe185ad2ca5f2b6597b77b1"}}
     */

    private int result;
    private String msg;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
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
}
