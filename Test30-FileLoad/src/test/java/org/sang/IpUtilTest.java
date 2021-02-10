package org.sang;

import org.junit.Test;
import org.sang.ip138.IpLocation;

import java.util.Arrays;

public class IpUtilTest extends ApplicationTests {

    @Test
    public void testGetAddr() {
        String address = IpLocation.getInstance().findLocation("106.38.3.106");
        String[] adds = address.split("\\s{2}+");

        System.out.println("address;" + Arrays.toString(adds) + "##" + Arrays.toString(adds[0].split("\\s")));

    }

    @Test
    public void testGetCityId() {
        String name = IpUtil.getCityName("106.38.3.106");
        Integer cityId = IpUtil.getCityId("106.38.3.106");

        System.out.println(name + "###" + cityId);
    }
    @Test
    public void testGetProvinceName() {
        String name = IpUtil.getProvinceName("106.38.3.106");

        System.out.println(name + "###");
    }
}
