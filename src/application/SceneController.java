package application;

import java.io.IOException;
import java.net.URL;
import java.sql.*;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class SceneController {

    DbContext _DbContext = new DbContext();
    PreparedStatement _ps;
    ResultSet _rs;

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField txtmailLogin;

    @FXML
    private PasswordField psfieldSifreLogin;

    @FXML
    private Button loginButton;

  
    public void ShowLoginScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    
    public void ShowKayitOlScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Reqister.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

   
    public void ShowAdminLoginScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void CloseForm(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    
    public void BackHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Form1.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("LustraShoes");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    
    public void ShowAdminPanel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminKulln.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    public void getProductsPage(ActionEvent event ) throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("falanfilan.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }



    public void UserLoginDb(ActionEvent event)
    {
        try {
            String Email = txtmailLogin.getText().trim();
            String Password = psfieldSifreLogin.getText().trim();

           
            if (Email.isEmpty() || Password.isEmpty()) 
            {
                ShowAlert(AlertType.WARNING, "Boş Alan!", "Lütfen tüm alanları doldurun.");
                return;
            }
          
           _DbContext.ConnectionOpen();
            String sql = "SELECT * FROM users WHERE Email = ? AND Password = ?";
            _ps = _DbContext._dbCon.prepareStatement(sql);
            _ps.setString(1, Email);
            _ps.setString(2, Password);

            _rs = _ps.executeQuery();
            if (_rs.next()) 
            {
                ShowAlert(AlertType.INFORMATION, "Giriş Başarılı!", "Hoşgeldiniz, " + Email);
                getProductsPage(event);
            } else {
                ShowAlert(AlertType.ERROR, "Giriş Hatası!", "Email veya şifre yanlış!");
            }

            _rs.close();
            _ps.close();
            _DbContext.CloseConnection();

        } 
        catch (Exception e) 
        {
            ShowAlert(AlertType.ERROR, "Bağlantı Hatası!", "Hata: " + e.getMessage());
        }
    }

    @FXML
    private TextField txtmailreqister;

    @FXML
    private TextField txtNameReqister;
 //   @FXML
   // private Hyperlink unuttumbttton;

    @FXML
    private PasswordField pFieldReqister;

    @FXML
    private Button btnKayitOl;


   public void UserReqisterDb(ActionEvent event) 
   {
	   try 
	   {
		_DbContext.ConnectionOpen();
		String isim = txtNameReqister.getText().trim();
		String mail =txtmailreqister.getText().trim();
		String şifre = pFieldReqister.getText().trim();
	   
		if (isim.isEmpty() || mail.isEmpty()||şifre.isEmpty()) 
		{
			ShowAlert(AlertType.WARNING,"Uyarı"," Lütfen Tüm Alanları Doldurun!");
		}
		
		String existingQueryString = "SELECT COUNT(*) FROM users WHERE email = ?";
		PreparedStatement _ps = _DbContext._dbCon.prepareStatement(existingQueryString);
		_ps.setString(1, mail);

		ResultSet rs = _ps.executeQuery();
		int count = 0;
		if (rs.next()) {
		    count = rs.getInt(1); // count(*) result
		}
		boolean isExists = count > 0;

		if(isExists) {
			ShowAlert( AlertType.INFORMATION,"", "This user exists");
		}
		else {
			String sql = "INSERT INTO users (Email,Name,Password) VALUES (?,?,?)";
			_ps= _DbContext._dbCon.prepareStatement(sql);
			_ps.setString(1, mail);
			_ps.setString(2, isim);
			_ps.setString(3, şifre);
			
			int res = _ps.executeUpdate();
			if (res>0) 
			{
				ShowAlert(AlertType.INFORMATION, "", "Kayıt İşlemi Başarılı");
				
			}
			else
			{
				ShowAlert(AlertType.ERROR, "", "Kayıt İşlemi Sırasında Hata Oluştu");
			}
			_ps.close();
			_DbContext.CloseConnection();	
		   } 
		   
				
			}
	   catch 
	   (Exception e) 
	   {
		// TODO: handle exception
	   }
	
		
   }
    
 
    
    
    private void ShowAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

@FXML
void unuttumbutton_click(ActionEvent event) throws IOException {
FXMLLoader(getClass().getResource("sampleunuttumclass"));
Parent root=Loader.load();
Stage stage=new Stage();
stage.setTitle("sample unuttum");
stage.setScene(new Scene(root));
stage.show();
Object unuttumbutton_click;
//((stage)unuttumbutton_click.getscene().getwindow()).Close();
}


private void FXMLLoader(URL resource) {
	// TODO Auto-generated method stub
	
}

}