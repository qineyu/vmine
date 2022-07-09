package com.qinyue.vcommon.http;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @类名 PageInfoBean
 * @作者 bodia
 * @时间 11/29/21 2:52 PM
 * @描述 列表数据的infobean
 */
public class HttpPageInfoBean implements Parcelable {
    private int page;
    private int pageNum;
    private int totalPage;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.pageNum);
        dest.writeInt(this.totalPage);
    }

    public void readFromParcel(Parcel source) {
        this.page = source.readInt();
        this.pageNum = source.readInt();
        this.totalPage = source.readInt();
    }

    public HttpPageInfoBean() {
    }

    protected HttpPageInfoBean(Parcel in) {
        this.page = in.readInt();
        this.pageNum = in.readInt();
        this.totalPage = in.readInt();
    }

    public static final Creator<HttpPageInfoBean> CREATOR = new Creator<HttpPageInfoBean>() {
        @Override
        public HttpPageInfoBean createFromParcel(Parcel source) {
            return new HttpPageInfoBean(source);
        }

        @Override
        public HttpPageInfoBean[] newArray(int size) {
            return new HttpPageInfoBean[size];
        }
    };
}
