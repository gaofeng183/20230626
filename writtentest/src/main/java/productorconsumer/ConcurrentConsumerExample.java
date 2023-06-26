package productorconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者消费者模型
 * @author gaofeng
 * @date 2023/6/26 21:32
 **/
public class ConcurrentConsumerExample {
    /**
     * 生产者生成1~10000数字
     **/
    private static final int MAX_NUMBERS = 10000;
    /**
     * 消费者数量
     **/
    private static final int NUM_CONSUMERS = 2;

    public static void main(String[] args) {
        /**
         * 存放生产者生成的数字
         **/
        BlockingQueue<Integer> numberQueue = new LinkedBlockingQueue<>();

        /**
         * 存放消费者输出的数字
         **/
        BlockingQueue<Integer> outputQueue = new LinkedBlockingQueue<>();

        /**
         * 消费者线程
         **/
        Thread[] consumerThreads = new Thread[NUM_CONSUMERS];

        /**
         * 生产者
         **/
        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= MAX_NUMBERS; i++) {
                try {
                    numberQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producerThread.start();

        /**
         * 消费者
         **/
        for (int i = 0; i < NUM_CONSUMERS; i++) {
            consumerThreads[i] = new Thread(() -> {
                while (true) {
                    try {
                        int number = numberQueue.take();
                        outputQueue.put(number);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
            consumerThreads[i].start();
        }

        try {
            // 等待生产者线程执行完毕
            producerThread.join();
            for (Thread consumerThread : consumerThreads) {
                Thread.sleep(1000);
                consumerThread.interrupt();
                consumerThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前队列总共有数字：" + outputQueue.size() + "个");
        while (!outputQueue.isEmpty()) {
            System.out.println(outputQueue.poll());
        }

        System.out.println("All tasks completed!");
    }
}