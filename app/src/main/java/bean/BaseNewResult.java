package bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/19.
 */

public class BaseNewResult<T> {
    private String count;
    private String error;
    private String status;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    private ArrayList<T> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
