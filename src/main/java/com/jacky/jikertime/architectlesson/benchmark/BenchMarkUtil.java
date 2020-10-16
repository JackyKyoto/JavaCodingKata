package com.jacky.jikertime.architectlesson.benchmark;

import com.google.common.collect.Lists;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.RandomUtils;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BenchMarkUtil {
  public static String testAb(int reqs, int concurrents, String testUrl) throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(concurrents);
    List<Callable<Long>> callableList = Lists.newArrayList();
    for (int i = 0; i < reqs; i++) {
      callableList.add(new ABTask(testUrl));
    }
    executor.execute(()->{

    });
    List<Future<Long>> futureList = executor.invokeAll(callableList);
    List<Long> executeTimeList = Lists.newArrayList();
    Long totalTime = 0L;
    for (Future<Long> longFuture : futureList) {
      Long time = longFuture.get();
      executeTimeList.add(time);
      totalTime += time;
    }
    executeTimeList.sort(Comparator.naturalOrder());

    long ninetyfiveindex = Math.round(reqs * 0.95);
    String inputInfo= String.format("输入参数URL:%s 请求总次数 %s 并发数%s \n",testUrl,reqs,concurrents);
    String ninetyfiveResult = "95% 响应时间: " + executeTimeList.get((int) ninetyfiveindex) + "毫秒\n";
    String averageTime = "平均响应时间" + totalTime / reqs+"毫秒";
    executor.shutdown();
    return inputInfo+ninetyfiveResult+averageTime;
  }

  public static class ABTask implements Callable<Long> {
    private String testUrl;

    public ABTask(String testUrl) {
      this.testUrl = testUrl;
    }

    @Override
    public Long call() throws Exception {
      OkHttpClient client = new OkHttpClient();
      Request request = new Request.Builder().url(testUrl).get()  //默认为GET请求，可以不写
                                             .build();
      long start = System.currentTimeMillis();
      final Call call = client.newCall(request);
      Response response = call.execute();
      if (response.isSuccessful()) {
        long end = System.currentTimeMillis();
        long usedTime = end - start;
        System.out.println("calling " + testUrl + " used:" + usedTime);
        return usedTime;
      } else {
        throw new RuntimeException("call failed");
      }
    }
  }


  public static void main(String[] args) {
    try {
      System.out.println(testAb(100, 10, "http://www.baidu.com"));
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
