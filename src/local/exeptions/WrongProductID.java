package local.exeptions;

public class WrongProductID extends Throwable {

    public int wrongID;

    public WrongProductID(int id) {
        wrongID = id;
    }

    public WrongProductID(String message) {
        super(message);
    }

    public void printMessage() {
        if (wrongID == 0) {
            System.out.println(this.getMessage() + " товара не существует");
        }
        else {
            System.out.println(wrongID + " товара не существует");
        }
    }

}
