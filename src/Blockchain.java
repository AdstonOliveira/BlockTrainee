import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author Suporte-04
 */
public class Blockchain {
    private final ArrayList <Block> blockchain = new ArrayList();
    //private int difficulty = 5;
    
    public Blockchain(){
        if( !this.addGenesis() )
            System.out.println("Ocorreu um erro ao inicializar a blockchain");
        
        System.out.println("Blockchain inicializada com sucesso\nOperando no bloco Genesis");
        
    }
    
    private boolean addGenesis(){
        if( this.blockchain.isEmpty() ){
           Block genesis = new Block(5);
           this.blockchain.add(genesis);
           return true;
        }
        return false;
    }


    private boolean addBlock( Block bloco ){
        if( this.blockchain.isEmpty() ){
            this.addGenesis();
        }else{
            if( this.getLast().isFull() ){
                Block block = new Block(this.getLast().getHash(), 5);
                this.blockchain.add(block);
                return true;
            }
            return false;
        }
        return true;
    }
    
    public boolean addTransaction( File file ){
        
        if( this.getLast().add_transation(file) ){
            System.out.println("Adicionado ao bloco com Sucesso!!!!!");
            return true;
        }else{
            if( this.getLast().isFull() ){
                this.getLast().calculate_hash();
                
                this.addBlock( new Block(getLast().getQtdeTransacoes()) );
                return this.addTransaction(file);
            }
            System.out.println("Falha ao adicionar, por favor verifique");
            return false;
        }
    }
    
    
    public int getSize(){
        return this.blockchain.size();
    }
    public Block getLast(){
        return this.blockchain.get( this.getSize()-1 );
    }
    
    public Block getGenesis(){
        if(this.getSize() > 0)
            return this.blockchain.get(0);
        
        return null;
    }
    //RMI URNA ELETRONICA
    
    public Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        int difficulty = 5;
        
        String hashTarget = new String( new char[difficulty] ).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i = 1; i < this.getSize(); i++) {
            currentBlock = this.getLast();
            previousBlock = this.blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculate_hash())) {
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
