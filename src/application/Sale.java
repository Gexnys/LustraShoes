package application;
import javafx.beans.property.*;

public class Sale {
	private final SimpleStringProperty productName;
    private final SimpleIntegerProperty amount;
    private final SimpleDoubleProperty price;

    public Sale(String urunAdi, int adet, double fiyat) {
        this.productName = new SimpleStringProperty(urunAdi);
        this.amount = new SimpleIntegerProperty(adet);
        this.price = new SimpleDoubleProperty(fiyat);
    }

    public String getUrunAdi() { return productName.get(); }
    public int getAdet() { return amount.get(); }
    public double getFiyat() { return price.get(); }
    public double getToplam() { return amount.get() * price.get(); }


}
