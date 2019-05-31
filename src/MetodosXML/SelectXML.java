package MetodosXML;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Suporte-04
 */
public abstract class SelectXML {
    
    
    public static File selected(){
        JFileChooser select = new JFileChooser("C:\\");
        select.setDialogTitle("Informe um arquivo de tipo XML");
        select.setFileSelectionMode(JFileChooser.FILES_ONLY);
        select.setAcceptAllFileFilterUsed(false);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Seleção exclusiva de XML","xml");
        select.setFileFilter(filter);
        int action = select.showOpenDialog(null);
        
            if(action == 0){
                JOptionPane.showMessageDialog(null, "Selecionado com sucesso!!!");
                return select.getSelectedFile();
            }else
                JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado\nEncerrando processo", "Encerrando sem selecionar", 2);
            return null;
    }
    
    
}
