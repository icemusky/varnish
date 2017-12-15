package eric.cn.com.varnish.bean;

/**
 * Created by Administrator on 2017/12/15.
 */

public class MessagePartBean {

    /**
     * data : {"title":"测试数据1","time":"1513306658","content":"测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1"}
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
         * title : 测试数据1
         * time : 1513306658
         * content : 测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1测试数据1
         */

        private String title;
        private String time;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
