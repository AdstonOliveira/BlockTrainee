
import MetodosXML.SelectXML;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    
    public static int difficulty = 5;
    
    public static void main(String[] args) throws IOException {
        //Verificar qual a saida do terminal do eleitor
            File xml = SelectXML.selected();
        if( xml != null ){
           blockchain.add( new Block(xml) );
           blockchain.get(0).mineBlock(difficulty);
           System.out.println(blockchain.get(0).hash);

            blockchain.add( new Block(xml, blockchain.get(blockchain.size() - 1).hash) );
            blockchain.get(1).mineBlock(difficulty);
            System.out.println(blockchain.get(1).hash);
            
            
            
            
            
            
            System.out.println("\nBlockchain is Valid: " + isChainValid());
        
        
        }
        /*
        //Realizando alteração em um dado
        v3.setNumber(4);
        //Checando se a blockchain continua a mesma
        System.out.println("\nBlockchain is Valid: " + isChainValid());
        */
        
        for(Block each : blockchain)
//            int number = each.getNumberCandidate();
            System.out.println("Nonce is: " + each.getNonce());
//            if(number == 55){
//                candidate1++;
//            }else if(number == 98){
//                candidate2++;
//            }else{
//                candidate3++;
//            }
        
        
     
        
     
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
    
    
    

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

}
