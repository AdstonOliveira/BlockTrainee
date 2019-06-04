
import Blockchain.Blockchain;

import Util.SelectXML;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {
    //public static ArrayList<Block> blockchain = new ArrayList<Block>();
    //DESiGN PATTERN ADAPTER

    public static void main(String[] args) throws IOException {
        
        JOptionPane.showMessageDialog(null,"Iniciando BC");
        Blockchain b = new Blockchain();
        File a = SelectXML.selected();
        
        if( a != null){
            b.addTransaction(a);
            b.addTransaction(a);
            b.addTransaction(a);
            b.addTransaction(a);
            b.addTransaction(a);
            
            b.addTransaction(a);
            
            
            
            b.printBlockChain();
        }
        
        
        
        
    }
    
    
    

    

}
