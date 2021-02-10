package org.sang;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.sang.ip138.IpLocation;

@Slf4j

public class IpUtil {

    /**
     * 获取国家名称
     * @param ip
     * @return
     */
    public static String getCountryName(String ip) {
        String[] adds = getRegion(ip);
        if(adds==null||adds.length==0){
            return null;
        }
        return adds[0];
    }
    /**
     * 获取省份名称
     * @param ip
     * @return
     */
    public static String getProvinceName(String ip) {
      String[] adds = getRegion(ip);
        if(adds==null||adds.length<=1){
            return null;
        }
        return adds[1];
    }

    /**
     * 获取城市名称
     * @param ip
     * @return
     */
    public static String getCityName(String ip) {
       String[] adds = getRegion(ip);
        if(adds==null||adds.length<=1){
            return null;
        }

        String cityName = adds[2];
        //eg:北京海淀，返回北京
        return  RegionUtil.getIspMap().containsKey(cityName)?cityName:adds[1];
    }

    public static Integer getCityId(String ip)  {
        String cityName = getCityName(ip);
        if(StringUtils.isEmpty(cityName)){
            return null;
        }

       return RegionUtil.getISPMapValue(cityName,"id");
    }

    /**
     * 获取区域数组,eg:[中国, 北京, 海淀]
     * @param ip
     * @return
     */
    public static String[] getRegion(String ip) {
        if(StringUtils.isEmpty(ip)){
            return null;
        }
        /*//目前业务传递的client_ip参数有出现多个的情况（多次反向代理后会有多个IP值，第一个是用户真实的IP地址）
        // 这里先处理一下，等业务解决这个问题后，去掉这个操作。
        if(ip.contains(",")){
            ip = ip.split(",")[0];
        }*/
        String address=IpLocation.getInstance().findLocation(ip);
        if(StringUtils.isEmpty(address)){
            return null;
        }
        //离线库的IP对应信息有所在城市和运营商相关信息，这两个信息用2个或2个以上的空字符进行分割。
        //正则表达式-使用2个或2个以上空格进行分割
        String[] adds = address.split("\\s{2}+");
        return adds[0].split("\\s");
    }

}
