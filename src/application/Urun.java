package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Urun {
    private final SimpleStringProperty urunAdi;
    private final SimpleDoubleProperty fiyat;
    private final SimpleStringProperty aciklama;

    public Urun(String urunAdi, double fiyat, String aciklama) {
        this.urunAdi = new SimpleStringProperty(urunAdi);
        this.fiyat = new SimpleDoubleProperty(fiyat);
        this.aciklama = new SimpleStringProperty(aciklama);
    }

    public String getUrunAdi() {
        return urunAdi.get();
    }

    public double getFiyat() {
        return fiyat.get();
    }

    public String getAciklama() {
        return aciklama.get();
    }

    // Property erişimi istersen (opsiyonel)
    public SimpleStringProperty urunAdiProperty() {
        return urunAdi;
    }

    public SimpleDoubleProperty fiyatProperty() {
        return fiyat;
    }

    public SimpleStringProperty aciklamaProperty() {
        return aciklama;
    }
}
