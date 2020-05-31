package local.exeptions;

public class PaymentErrors extends Throwable {

  public PaymentErrors(String s) {
    super(s);
  }

  public void printMessage() {
    System.out.println("Проблемы при оплате:");
    System.out.println(this.getMessage());
  }

}
