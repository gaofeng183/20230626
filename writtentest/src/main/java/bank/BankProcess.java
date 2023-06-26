package bank;

/**
 * TODO
 *
 * @author gaofeng
 * @date 2023/6/26 20:22
 */
public class BankProcess {

  public static void main(String[] args) {
    // 3个窗口，10位客户
    process(10, 3);
  }

  /**
   * 银行排队
   **/
  public static void process(int numCustomers, int numWindow) {
    BankQueue bankQueue = new BankQueue(numWindow);
    for (int i = 0; i < numCustomers; i++) {
      Customer customer = new Customer(i + 1, bankQueue.getTotalHandleTime()) ;
      bankQueue.inQueue(customer);
    }
    bankQueue.handlerQueue();
  }
}
