package com.iamle.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
 
public class SocketWindowWordCount {
 
    public static void main(String[] args) throws Exception {
 
        // 输入tcp流
        final int port;
        final String host;
        port = 9008; // nc监听的tcp端口
        host = "localhost"; // docker宿主机ip
 
        //1.创建执行程序的上下文环境
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    //2.创建数据流，可以有多个数据流
        // get input data by connecting to the socket
        DataStream<String> text = env.socketTextStream(host, port, "\n");

 //3.拉平数据
        // parse the data, group it, window it, and aggregate the counts
        DataStream<WordWithCount> windowCounts = text
                .flatMap(new FlatMapFunction<String, WordWithCount>() {
                    @Override
                    public void flatMap(String value, Collector<WordWithCount> out) {
                        for (String word : value.split("\\s")) {
                            out.collect(new WordWithCount(word, 1L));
                        }
                    }
                })
                .keyBy("word")
                .timeWindow(Time.seconds(5), Time.seconds(1))
                .reduce(new ReduceFunction<WordWithCount>() {
                    @Override
                    public WordWithCount reduce(WordWithCount a, WordWithCount b) {
                        return new WordWithCount(a.word, a.count + b.count);
                    }
                });
        //4.sink输出结果必须调用
 
        // print the results with a single thread, rather than in parallel
        windowCounts.print().setParallelism(4);
 
        env.execute("Socket Window WordCount");
    }
 
    // Data type for words with count
    public static class WordWithCount {
 
        public String word;
        public long count;
 
        public WordWithCount() {}
 
        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }
 
        @Override
        public String toString() {
            return word + " : " + count;
        }
    }
}
 