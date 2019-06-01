
import MetodosXML.SelectXML;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Main {
    //public static ArrayList<Block> blockchain = new ArrayList<Block>();
    //DESiGN PATTERN ADAPTER

    public static void main(String[] args) throws IOException {
        Blockchain b = new Blockchain();
        //Verificar qual a saida do terminal do eleitor
        File xml = SelectXML.selected();
        if( xml != null ){
           /*//blockchain.add( new Block(xml) );
           blockchain.get(0).mineBlock(difficulty);
           System.out.println(blockchain.get(0).hash);
            //blockchain.add( new Block(xml, blockchain.get(blockchain.size() - 1).hash) );
            blockchain.get(1).mineBlock(difficulty);
            System.out.println(blockchain.get(1).hash);
            */
            
            
           JOptionPane.showMessageDialog(null, b.addTransaction(xml));
           
           JOptionPane.showMessageDialog(null, b.addTransaction(xml));
           JOptionPane.showMessageDialog(null, b.addTransaction(xml));
           JOptionPane.showMessageDialog(null, b.addTransaction(xml));
           JOptionPane.showMessageDialog(null, b.addTransaction(xml));
           
           JOptionPane.showMessageDialog(null,b.addTransaction(xml));
           JOptionPane.showMessageDialog(null,b.addTransaction(xml));
           JOptionPane.showMessageDialog(null,b.addTransaction(xml));
           
           
           
            System.out.println("NUmero de Blocos atuais: " + b.getSize());
           
           
           
            
           
           
            
            
            
            
            System.out.println("\nBlockchain is Valid: " + b.isChainValid());
        
        
        }
        /*
        //Realizando alteração em um dado
        v3.setNumber(4);
        //Checando se a blockchain continua a mesma
        System.out.println("\nBlockchain is Valid: " + isChainValid());
        */
        
        //Salvando em Json para compartilhamento P2P
        /*
        // erro aqui
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
            System.out.println("\nThe block chain: ");
        
        FileWriter writeFile = new FileWriter("./result/saida.json");
            writeFile.write(blockchainJson);
            writeFile.close();
        
        System.out.println(blockchainJson);
            
        */
        
        
    }
    
    
    

    

}
