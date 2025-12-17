package application;

import java.io.File;
import java.net.URL;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class adminKullnController {

	private Stage stage;
	private Scene scene;
	DbContext _DbContext = new DbContext();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button FotoSec;

	@FXML
	private Button UrunEkle;

	@FXML
	private Button UrunSil;

	@FXML
	private TextField txtUrunAdi;

	@FXML
	private TextField txtFiyat;

	@FXML
	private TextField txtAciklama;

	@FXML
	private ImageView imageView;

	private String selectedImagePath = null;
	
	@FXML
	private DatePicker DateBaslangic;

	@FXML
	private DatePicker DateBitis; 

	@FXML
	void fotografSec(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Resim Seç");

		// 1. Başlangıç klasörü olarak projenin içindeki "Images" klasörünü ayarla
		File initialDir = new File("Images");
		if (initialDir.exists()) {
			fileChooser.setInitialDirectory(initialDir);
		}

		// 2. Sadece resim dosyaları
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.jpeg"));

		// 3. Dosya seçim işlemi
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			selectedImagePath = selectedFile.getAbsolutePath();
			Image image = new Image(selectedFile.toURI().toString());
			imageView.setImage(image);
		}
	}

	@FXML
	void btnEkleClicked(ActionEvent event) {
		String urunAdi = txtUrunAdi.getText();
		String fiyatStr = txtFiyat.getText();
		String aciklama = txtAciklama.getText();

		if (urunAdi.isEmpty() || fiyatStr.isEmpty() || aciklama.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz.");
			return;
		}

		try {
			double fiyat = Double.parseDouble(fiyatStr);

			String resimDosyaAdi = "";
			if (selectedImagePath != null) {
				File file = new File(selectedImagePath);
				resimDosyaAdi = file.getName();
			}

			Connection con = _DbContext.ConnectionOpen();
			String sql = "INSERT INTO urunler (UrunAdi, Fiyat, Aciklama, Resim) VALUES (?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, urunAdi);
			pst.setDouble(2, fiyat);
			pst.setString(3, aciklama);
			pst.setString(4, resimDosyaAdi);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ürün başarıyla eklendi!");

			_DbContext.CloseConnection();
			selectedImagePath = null;

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Fiyat sayısal olmalıdır!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hata: " + e.getMessage());
		}
	}

	@FXML
	private TableView<Urun> tableUrunler;

	@FXML
	private TableColumn<Urun, String> colUrunAdi;

	@FXML
	private TableColumn<Urun, Double> colFiyat;

	@FXML
	private TableColumn<Urun, String> colAciklama;

	private void urunleriListele() {
		ObservableList<Urun> urunListesi = FXCollections.observableArrayList();

		try {
			System.out.println("1");
			Connection con = _DbContext.ConnectionOpen();
			String sql = "SELECT UrunAdi, Fiyat, Aciklama FROM urunler";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("2");

			while (rs.next()) {
				String urunAdi = rs.getString("UrunAdi");
				double fiyat = rs.getDouble("Fiyat");
				String aciklama = rs.getString("Aciklama");

				System.out.println("→ " + urunAdi + " / " + fiyat + " / " + aciklama);

				urunListesi.add(new Urun(urunAdi, fiyat, aciklama));
			}
			System.out.println("3");
			_DbContext.CloseConnection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Listeleme hatası: " + e.getMessage());
		}

		colUrunAdi.setCellValueFactory(new PropertyValueFactory<>("urunAdi"));
		colFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
		colAciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
		tableUrunler.setItems(urunListesi);
	}

	public void BackToAdminLogin(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
		scene = new Scene(root);
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("LustraShoes");
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	@FXML
	void CloseForm(ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	void initialize() {
		assert FotoSec != null : "fx:id=\"FotoSec\" was not injected: check your FXML file 'AdminKullanıcı.fxml'.";
		assert UrunEkle != null : "fx:id=\"UrunEkle\" was not injected: check your FXML file 'AdminKullanıcı.fxml'.";
		assert UrunSil != null : "fx:id=\"UrunSil\" was not injected: check your FXML file 'AdminKullanıcı.fxml'.";
		
		
		urunleriListele();
		
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

	}
	@FXML
    void reportButton(ActionEvent event) {

    }
	
	@FXML
    void cleanButton(ActionEvent event) {

    }
	
	@FXML
	void applyButton(ActionEvent event) {
	    LocalDate startDate = DateBaslangic.getValue();
	    LocalDate endDate = DateBitis.getValue();
	    ArrayList<SaleData> saleDatas = new ArrayList<>();

	    if (startDate == null || endDate == null) {
	        System.out.println("Lütfen tarihleri seçin.");
	        return;
	    }

	    String startDateStr = startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	    String endDateStr = endDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

	    String sql = "SELECT * FROM sales WHERE sale_date BETWEEN STR_TO_DATE(?, '%Y%m%d') AND STR_TO_DATE(?, '%Y%m%d')";

	    try (Connection con = _DbContext.ConnectionOpen();
	         PreparedStatement pst = con.prepareStatement(sql)) {

	        pst.setString(1, startDateStr);
	        pst.setString(2, endDateStr);

	        try (ResultSet rs = pst.executeQuery()) {
	            while (rs.next()) {
	                SaleData sData = new SaleData(
	                    rs.getInt("id"),
	                    rs.getDouble("price"),
	                    rs.getInt("amount"),
	                    rs.getString("product_name"),
	                    rs.getString("sale_date")
	                );
	                saleDatas.add(sData);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    listSaleData(saleDatas);  // <-- important!
	}

	
	@FXML
	private TableView<SaleData> LblToplamTutar;

	@FXML
	private TableColumn<SaleData, String> productNameCol;

	@FXML
	private TableColumn<SaleData, Integer> productAmountCol;

	@FXML
	private TableColumn<SaleData, Double> productPriceCol;
	
	@FXML
	private TextField totalPriceField;

	
	void listSaleData(ArrayList<SaleData> saleDatas) {
		double totalPrice=0.0;
		for (Object obj : saleDatas) {
		    if (obj instanceof SaleData) {
		        SaleData sale = (SaleData) obj;  // cast to SaleData
		        System.out.println("Product: " + sale.getProductName() + ", Price: " + sale.getPrice());
		        totalPrice+=sale.getPrice();
		    } else {
		        System.out.println("Unknown object type");
		    }
		}
		 ObservableList<SaleData> observableList = FXCollections.observableArrayList(saleDatas);
		 LblToplamTutar.setItems(observableList);
		 totalPriceField.setText(String.format("%.2f", totalPrice)); 
	}

	
	
	
	


}