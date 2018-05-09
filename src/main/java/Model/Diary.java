package Model;

/**
 * Created by hchok on 28-01-2018.
 */

public class Diary {
    private int id;
    private String name;
    private String address;
    private String product;
    private String quantity;
    private String price;
    private String paid;
    private String balance;
    private String warranty;
    private String installment;
    private String dateAdded;





    public Diary(){
    }

    public Diary(String name, String address, String product, String quantity, String price, String paid, String balance, String warranty,String installment, int id, String dateAdded) {
        this.name = name;
        this.address = address;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.paid = paid;
        this.balance = balance;
        this.warranty=warranty;
        this.installment = installment;
        this.id=id;
        this.dateAdded=dateAdded;
    }
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getDateAdded(){ return dateAdded; }

    public void setDateAdded(String dateAdded){ this.dateAdded = dateAdded; }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}
