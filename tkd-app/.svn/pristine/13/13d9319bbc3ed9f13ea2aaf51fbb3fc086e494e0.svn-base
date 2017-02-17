package com.lc.zy.ball.app.common;

public class Utils {

    /**
     * 
     * <根据平均分获取星级><功能具体实现>
     *
     * @create：2015年9月2日 上午10:16:40
     * @author： CYY
     * @param score
     * @return
     */
    public static Integer getGrade(Double score) {
        if (score.compareTo(0D) >= 0 && score.compareTo(0.5D) <= 0) {
            return 1;
        } else if (score.compareTo(0.5D) > 0 && score.compareTo(1D) <= 0) {
            return 2;
        } else if (score.compareTo(1D) > 0 && score.compareTo(1.5D) <= 0) {
            return 3;
        } else if (score.compareTo(1.5D) > 0 && score.compareTo(2D) <= 0) {
            return 4;
        } else if (score.compareTo(2D) > 0 && score.compareTo(2.5D) <= 0) {
            return 5;
        } else if (score.compareTo(2.5D) > 0 && score.compareTo(3D) <= 0) {
            return 6;
        } else if (score.compareTo(3D) > 0 && score.compareTo(3.5D) <= 0) {
            return 7;
        } else if (score.compareTo(3.5D) > 0 && score.compareTo(4D) <= 0) {
            return 8;
        } else if (score.compareTo(4D) > 0 && score.compareTo(4.5D) <= 0) {
            return 9;
        } else {
            return 10;
        }
    }

    
    public static String threeOpenMsg(Integer type) {
        String msg = "";
        switch (type) {
        case 0:
            msg = "微信";
            break;
        case 1:
            msg = "新浪微博";
            break;
        case 2:
            msg = "QQ";
            break;
        case 3:
            msg = "百度";
            break;
        }
        return msg;
    }

    public static void main(String[] args) {
        System.out.println(getGrade(0.6D));
    }
}
