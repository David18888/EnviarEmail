package dad.javafx.email.mvc;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;

import org.apache.commons.mail.SimpleEmail;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EnviarEmailController implements Initializable{

	
	
	
	
	@FXML
	private BorderPane view;
	
	@FXML
	private GridPane emailPane;
	
	@FXML
	private Button enviarButton,vaciarButton,cancelarButton;
	
	@FXML
	private CheckBox sslCheck;
	@FXML
	private TextField smtpField,puertoField,rmtField,destinoField,asuntoField;
	
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextArea mensajeArea;
	
	public EnviarEmailController() throws IOException {
		FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/EnviarEmailView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		
		
		enviarButton.setOnAction(e->onEnviarButtonAction());
		vaciarButton.setOnAction(e->onVaciarButtonAction());
		cancelarButton.setOnAction(e->onCancelarButtonAction());
		
	
		
	}
	
	private void onCancelarButtonAction() {
		Platform.exit();
	}

	private void onVaciarButtonAction() {
		smtpField.setText(null);
		puertoField.setText(null);
		rmtField.setText(null);
		passwordField.setText(null);
		asuntoField.setText(null);
		destinoField.setText(null);
		mensajeArea.setText(null);
		sslCheck.selectedProperty().set(false);
	}

	private void onEnviarButtonAction()  {
		
		
		try {
		Email email= new SimpleEmail();
		email.setHostName(smtpField.getText());
		email.setSmtpPort(Integer.parseInt(puertoField.getText()));
		email.setAuthenticator(new DefaultAuthenticator(rmtField.getText(),passwordField.getText()));
		if (sslCheck.isSelected()) {
		email.setSSLOnConnect(true);
		}else {
			email.setSSLOnConnect(false);
		}
		email.setFrom(rmtField.getText());
		email.setSubject(asuntoField.getText());
		email.setMsg(mensajeArea.getText());
		email.addTo(destinoField.getText());
		email.send();
			
			
			
			
			
			
		
		Alert alert= new Alert(AlertType.INFORMATION);
		alert.setTitle("Enviado");
		alert.setContentText("Se ha enviado el correo correctamente");
		alert.showAndWait();
		
		}catch(Exception e) {
			
			Alert alert= new Alert(AlertType.ERROR);
			alert.setTitle("No Enviado");
			alert.setContentText("No se ha podido  enviar  el correo correctamente");
			alert.showAndWait();
		}
	
		
		
	}

	public BorderPane getView() {
		return view;
	}

}
