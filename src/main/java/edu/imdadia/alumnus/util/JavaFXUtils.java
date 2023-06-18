package edu.imdadia.alumnus.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class JavaFXUtils {

  public static Optional<ButtonType> showAlert(final AlertType alertType, final String title,
                                               final String errorMessage) {
    final Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setContentText(errorMessage);
    return alert.showAndWait();
  }

  public static void showSuccessMessage(final String successMessage) {
    final Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setContentText(successMessage);
    alert.showAndWait();
  }

  public static void showWarningMessage(final String errorMessage) {
    final Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setContentText(errorMessage);
    alert.showAndWait();
  }

  public static void showError(final String errorMessage) {
    final Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setContentText(errorMessage);
    alert.showAndWait();
  }
}
