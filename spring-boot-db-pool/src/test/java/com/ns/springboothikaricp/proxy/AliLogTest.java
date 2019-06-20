package com.ns.springboothikaricp.proxy;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.request.GetLogsRequest;
import com.aliyun.openservices.log.response.GetLogsResponse;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ns.springboothikaricp.ApplicationTests;
//import com.ns.springboothikaricp.bean.AliLog2;
import com.ns.springboothikaricp.client.AliLog;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author junlin.qi on 2018/8/13
 */
public class AliLogTest extends ApplicationTests {

     @Resource
    private AliLog aliLog;

   @Test
    public void testClient(){
        System.out.println("null::"+aliLog);
    }

    @Test
    public void testAliLog() throws LogException {

        String endpoint = "http://cn-hongkong.log.aliyuncs.com";

        String accessKeyId = "LTAIIcPwEt3o6IF6";

        String accessKeySecret = "cnc9yL2qPEkJKCZgakG3I06le0lEby";
        String project = "shop-hk";
        int offset = 0;
        int size = 1;
        String logstore = "slb-access-log";

        int from = (int) (new Date().getTime() / 1000 - 300);
        int to = (int) (new Date().getTime() / 1000);

        long start = System.currentTimeMillis() / 1000;

        System.out.println(from + "#" + start % 60 + "##" + (start - start % 60));
        Client client = new Client(endpoint, accessKeyId, accessKeySecret);
        //ListLogStoresRequest req1 = new ListLogStoresRequest(project, offset, size, logStoreSubName);

        System.out.println("client==null" + client);
        GetLogsRequest req4 = new GetLogsRequest(project, logstore, from, to, null, " * | select sum(request_length)/(max(__time__)-min(__time__)) as inflow , __time__-__time__%60  as window_time   group by window_time order by window_time limit 15");

        GetLogsResponse res4 = client.GetLogs(req4);

        Multimap multimap = ArrayListMultimap.create();

        for (QueriedLog log : res4.GetLogs()) {
            LogItem item = log.GetLogItem();


            for (LogContent content : item.GetLogContents()) {
                System.out.print(content.GetKey() + ":" + content.GetValue());
                multimap.put(content.GetKey(), content.GetValue());
            }
            System.out.println();
        }
        System.out.println("multimap::" + multimap);

    }


}
