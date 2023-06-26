package bank;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO
 *
 * @author gaofeng
 * @date 2023/6/26 19:51
 */
public class BankQueue {

  /**
   * 现在几号窗口
   **/
  private int nowWindow ;

  /**
   * 保存多个窗口
   **/
  private List<Queue<Customer>> windows ;

  /**
   * 总等待时间
   **/
  private int totalWaitTime ;

  /**
   * 总处理时间
   **/
  private int totalHandleTime ;


  public BankQueue(int nowWindow) {
    this.nowWindow = nowWindow;
    this.windows = new ArrayList<>();
    for (int i = 0; i < nowWindow; i++) {
      this.windows.add(new LinkedList<>()) ;
    }
  }

  /**
   * 顾客到一个窗口排队办理业务
   **/
  public void inQueue(Customer customer) {
    int windowIndex = RandomUtil.randomInt(nowWindow) ;
    windows.get(windowIndex).add(customer) ;
  }

  /**
   * 每个窗口的办理
   **/
  public void handlerQueue() {
    while (windows.stream().anyMatch(window -> !window.isEmpty())) {
      for (Queue<Customer> window : windows) {
        // 窗口队列还有排队的客户
        if (!window.isEmpty()) {
          // 办理，并出队
          Customer customer = window.poll();
          // 办理时间
          int handleTime = RandomUtil.randomInt(10) + 1;
          totalHandleTime += handleTime ;
          // 等待时间
          int waitTime = totalHandleTime - customer.getArriveTime() ;
          totalWaitTime += waitTime ;

          System.out.println("Customer：" + customer.getNumber() + "，等待时间：" + totalWaitTime + "；办理时间：" + totalHandleTime);
        }
      }
    }
  }

  public int getTotalWaitTime() {
    return totalWaitTime ;
  }

  public int getTotalHandleTime() {
    return totalHandleTime ;
  }

}
