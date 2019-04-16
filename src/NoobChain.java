
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        Vote v1 = new Vote(55, "Jose");
            blockchain.add(new Block(v1, "0"));
            System.out.println("Trying to Mine block 1... ");
            blockchain.get(0).mineBlock(difficulty);

        
        
        Vote v2 = new Vote(98, "Joao");
            blockchain.add(new Block(v2, blockchain.get(blockchain.size() - 1).hash));
            System.out.println("Trying to Mine block 2... ");
            blockchain.get(1).mineBlock(difficulty);

        
        Vote v3 = new Vote(23, "Marcos");
            blockchain.add(new Block(v3, blockchain.get(blockchain.size() - 1).hash));
            System.out.println("Trying to Mine block 3... ");
            blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
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
