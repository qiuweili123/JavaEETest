package org.sang.util;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RegionUtil {

    private static String regionDataPath = "classpath:data/region_code.dat";
    /**
     * 区域全部数据
     */
    private static Map<String, Map> regionCodeMap = new HashMap();
    /**
     * 运营商名称数据
     */
    private static Map<String, Map> ispMap = new HashMap();

    static {
        try {
            File data = ResourceUtils.getFile(regionDataPath);
            String jsonStr = FileUtils.readFileToString(data, Charset.defaultCharset());
            List<Map<String, String>> list = JSONArray.parseObject(jsonStr, List.class);
            regionCodeMap = list.stream().collect(Collectors.toMap(map -> map.get("code"), Function.identity()));
            ispMap = list.stream().filter(map -> StringUtils.isNotEmpty(map.get("ispName"))).collect(Collectors.toMap(map -> map.get("ispName"), Function.identity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getRegionDataPath() {
        return regionDataPath;
    }

    public static Map<String, Map> getIspMap() {
        return ispMap;
    }

    public static <T> T getRegionMapValue(String key, String fieldKey) {
        Map<String, T> map = regionCodeMap.get(key);
        return map.get(fieldKey);
    }

    public static <T> T getISPMapValue(String key, String fieldKey) {
        Map<String, T> map = ispMap.get(key);
        return map.get(fieldKey);
    }


}
