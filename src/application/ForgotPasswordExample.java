package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ForgotPasswordExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button forgotPasswordBtn = new Button("Şifremi Unuttum");

        forgotPasswordBtn.setOnAction(e -> {
            // Burada şifre sıfırlama işlemi yapılır (örn: e-posta gönderme)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Şifre Sıfırlama");
            alert.setHeaderText(null);
            alert.setContentText("Şifre sıfırlama talimatları e-posta adresinize gönderildi.");
            alert.showAndWait();
        });

        VBox root = new VBox(10, forgotPasswordBtn);
        root.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(root, 300, 150);

        primaryStage.setTitle("Giriş Ekranı");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
