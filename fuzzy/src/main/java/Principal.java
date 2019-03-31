import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Principal {


    public static void main(String[] args) throws Exception{
        System.out.println("Teste");

        // Load from 'FCL' file
        String fileName = "fcl/temperatura.fcl";
        FIS fis = FIS.load(fileName,true);

        // Error while loading?
        if( fis == null ) {
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }

        // Show
        JFuzzyChart.get().chart(fis);

        // Set inputs
        fis.setVariable("temperatura_1", 0);
        fis.setVariable("temperatura_2", 0);
        fis.setVariable("temperatura_3", 0);

        // Evaluate
        fis.evaluate();

        // Show output variable's chart
        Variable acao = fis.getVariable("acao");
        JFuzzyChart.get().chart(acao, acao.getDefuzzifier(), true);

        // Print ruleSet
        System.out.println(fis);
    }
}
