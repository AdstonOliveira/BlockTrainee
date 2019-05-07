
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    
    public static int difficulty = 5;
    
    public static void main(String[] args) throws IOException {
        //Verificar qual a saida do terminal do eleitor
        
        Vote v1 = new Vote(55, "Jose");
            blockchain.add(new Block(v1, "0"));
            System.out.println("Mineirando bloco 1... ");
            blockchain.get(0).mineBlock(difficulty);

        Vote v2 = new Vote(98, "Joao");
            blockchain.add(new Block(v2, blockchain.get(blockchain.size() - 1).hash));
            System.out.println("Mineirando bloco 2... ");
            blockchain.get(1).mineBlock(difficulty);
            
            v2.setNumber(10);
            
        Vote v3 = new Vote(23, "Marcos");
            blockchain.add(new Block(v3, blockchain.get(blockchain.size() - 1).hash));
            System.out.println("Mineirando bloco 3... ");
            blockchain.get(2).mineBlock(difficulty);
            
        Vote v4 = new Vote(23, "Marcos");
            blockchain.add(new Block(v4, blockchain.get(blockchain.size() - 1).hash));
            System.out.println("Mineirando bloco 4... ");
            blockchain.get(3).mineBlock(difficulty);
            
        Vote v5 = new Vote(98, "Joao");
            blockchain.add(new Block(v5, blockchain.get(blockchain.size() - 1).hash));
            System.out.println("Mineirando bloco 5... ");
            blockchain.get(4).mineBlock(difficulty);
        
        Vote v6 = new Vote(98, "Joao");
            blockchain.add(new Block(v6, blockchain.get(blockchain.size() - 1).hash));
            System.out.println("Mineirando bloco 6... ");
            blockchain.get(5).mineBlock(difficulty);
            
            
        System.out.println("\nBlockchain is Valid: " + isChainValid());
        
        /*
        //Realizando alteração em um dado
        v3.setNumber(4);
        //Checando se a blockchain continua a mesma
        System.out.println("\nBlockchain is Valid: " + isChainValid());
        */
        
        int candidate1 = 0;
        int candidate2 = 0;
        int candidate3 = 0;
        
        
        for(Block each : blockchain){
            int number = each.getNumberCandidate();
            System.out.println("De Nonce is: " + each.getNonce());
            if(number == 55){
                candidate1++;
            }else if(number == 98){
                candidate2++;
            }else{
                candidate3++;
            }
        }
        
        ArrayList<Integer> candidatos = new ArrayList();
        
        candidatos.add(candidate1);
        candidatos.add(candidate2);
        candidatos.add(candidate3);
        
        
        
        Collections.sort(candidatos);
//        System.out.println(candidatos.toString());
        
        Collections.reverse(candidatos);
//        System.out.println(candidatos.toString());

        System.out.println("Maior numero de votos: " + candidatos.get(0));
        
        
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
