package bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户
 *
 * @author gaofeng
 * @date 2023/6/26 20:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  /**
   * 客户号
   **/
  private int number;

  /**
   * 客户到达时间
   **/
  private int arriveTime;
}
