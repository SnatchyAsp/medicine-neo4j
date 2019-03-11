package com.zangmz.hit.medicineneo4j.bo.res;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class BaseRes<T> {

    /**
     * 返回码
     * 0成功
     * 1主语未找到
     * 2宾语未找到
     * 3关系不存在
     */
    private String returnCode;

    private List<T> data;

    private String returnMessage;

    public void getSucess(String returnMessage){
        this.returnMessage = returnMessage;
        this.returnCode = "0";
    }

    public void getFail(String returnMessage, String returnCode){
        this.returnMessage = returnMessage;
        this.returnCode = returnCode;
    }

    @Override
    public String toString() {
        return "BaseRes{" +
                "returnCode='" + returnCode + '\'' +
                ", data=" + data +
                ", returnMessage='" + returnMessage + '\'' +
                '}';
    }
}
