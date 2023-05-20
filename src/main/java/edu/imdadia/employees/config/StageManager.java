package edu.imdadia.employees.config;

import edu.imdadia.employees.enumuration.FxmlView;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Data;
import org.slf4j.Logger;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Manages switching Scenes on the Primary Stage
 */
@Data
public class StageManager {

  private static final Logger LOG = getLogger(StageManager.class);
  private final Stage stage;
  private final SpringFXMLLoader springFXMLLoader;

  public StageManager(final SpringFXMLLoader springFXMLLoader, final Stage stage) {
    this.springFXMLLoader = springFXMLLoader;
    this.stage = stage;
  }

  public void switchScene(final FxmlView view) {
    final Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
    show(viewRootNodeHierarchy, view.getTitle());
  }

  private void show(final Parent rootNode, final String title) {
    final Scene scene = prepareScene(rootNode);
    stage.setTitle(title);
    stage.setScene(scene);
    stage.sizeToScene();
    stage.centerOnScreen();

    try {
      stage.show();
    } catch (final Exception exception) {
      logAndExit("Unable to show scene for title" + title, exception);
    }
  }

  private Scene prepareScene(final Parent rootNode) {
    Scene scene = stage.getScene();

    if (scene == null) {
      scene = new Scene(rootNode);
    }
    scene.setRoot(rootNode);
    return scene;
  }

  /**
   * Loads the object hierarchy from a FXML document and returns to root node of that hierarchy.
   *
   * @return Parent root node of the FXML document hierarchy
   */
  private Parent loadViewNodeHierarchy(final String fxmlFilePath) {
    Parent rootNode = null;
    try {
      rootNode = springFXMLLoader.load(fxmlFilePath);
      Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
    } catch (final Exception exception) {
      logAndExit("Unable to load FXML view" + fxmlFilePath, exception);
    }
    return rootNode;
  }


  private static void logAndExit(final String errorMsg, final Exception exception) {
    LOG.error(errorMsg, exception, exception.getCause());
    Platform.exit();
  }

}
