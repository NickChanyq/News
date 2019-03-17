package bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/17.
 */

public class NewTop {

    private String error;
    private ArrayList<New> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<New> getData() {
        return data;
    }

    public void setData(ArrayList<New> data) {
        this.data = data;
    }

    public class New {
        private String title;
        private String digest;
        private String content;
        private String top_image;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        private String source;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTop_image() {
            return top_image;
        }

        public void setTop_image(String top_image) {
            this.top_image = top_image;
        }

    }
}
