package eric.cn.com.varnish.bean;

/**
 * Created by Eric on 2017/12/16.
 */

public class InfoBean {


    /**
     * data : {"name":"ceshi","group_id":"13","number":"001","email":"","phone":"15904006000","site_id":"0","avatar":"","can_sign":0,"message":"0"}
     * error : 0
     * returl :
     * msg :
     */

    private DataBean data;
    private int error;
    private String returl;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getReturl() {
        return returl;
    }

    public void setReturl(String returl) {
        this.returl = returl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * name : ceshi
         * group_id : 13
         * number : 001
         * email :
         * phone : 15904006000
         * site_id : 0
         * avatar :
         * can_sign : 0
         * message : 0
         */

        private String name;
        private String group_id;
        private String number;
        private String email;
        private String phone;
        private String site_id;
        private String avatar;
        private int can_sign;
        private String message;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSite_id() {
            return site_id;
        }

        public void setSite_id(String site_id) {
            this.site_id = site_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCan_sign() {
            return can_sign;
        }

        public void setCan_sign(int can_sign) {
            this.can_sign = can_sign;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
