import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Tela {

	@FXML
	private TextField inputA;

	@FXML
	private TextField inputB;

	@FXML
	private TextField inputC;

	@FXML
	private Button buttonS;

	@FXML
	private TextArea txtResposta;

	@FXML
	void botao(ActionEvent event) {
		try {
			Integer.parseInt(inputA.getText());
			Integer.parseInt(inputB.getText());
			Integer.parseInt(inputC.getText());

			System.out.println("Teste");

			// Load from 'FCL' file
			String fileName = "fcl/temperatura.fcl";
			FIS fis = FIS.load(fileName, true);

			// Error while loading?
			if (fis == null) {
				System.err.println("Can't load file: '" + fileName + "'");
				return;
			}

			// Show
			JFuzzyChart.get().chart(fis);

			// Set inputs
			fis.setVariable("temperatura_1", Integer.valueOf(inputA.getText()));
			fis.setVariable("temperatura_2", Integer.valueOf(inputA.getText()));
			fis.setVariable("temperatura_3", Integer.valueOf(inputA.getText()));

			// Evaluate
			fis.evaluate();

			// Show output variable's chart
			Variable acao = fis.getVariable("acao");
			JFuzzyChart.get().chart(acao, acao.getDefuzzifier(), true);

			// Print ruleSet
			txtResposta.setText(fis.toString());
			System.out.println(acao);
			Alert alert = new Alert(AlertType.INFORMATION);
			((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("css/icone.png"));
			alert.setContentText(acao.toString());
			alert.show();

		} catch (NumberFormatException n) {
			n.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("GenericApp.png"));
			alert.setTitle("Valor inválido");
			alert.show();
		}
	}

}
