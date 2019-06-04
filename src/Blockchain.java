import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Suporte-04
 */
public class Blockchain {
    private final ArrayList <Block> blockchain = new ArrayList();
    private ArrayList<Block> pool = new ArrayList();//COMPARTILHADO
    
    //private int difficulty = 5;
    
    public Blockchain(){
        if( !this.addGenesis() )
            System.out.println("Ocorreu um erro ao inicializar a blockchain");
        
        JOptionPane.showMessageDialog(null,"Blockchain inicializada com sucesso\nOperando no bloco Genesis");

    }
    
    private boolean addGenesis(){
        if( this.blockchain.isEmpty() ){
           Block genesis = new Block(this);
           this.blockchain.add(genesis);
           return true;
        }
        return false;
    }


    private void newBlock(){
        if( this.blockchain.isEmpty() ){
            this.addGenesis();
        }else{
             Block block = new Block( this, this.getLast().getHash() );
             this.blockchain.add(block);
        }
    }
    
    public boolean addTransaction( File file ){
        if( this.getLast().add_transation(file) ){
            return true;
            //Adicionado com sucesso
        }else{
             this.newBlock();
             this.addTransaction(file);
        }
        return false;
    }
    
    
    public int getSizeBlocks(){
        return this.blockchain.size();
    }
    public Block getLast(){
        return this.blockchain.get( this.getSizeBlocks()-1 );
    }
    
    public Block getGenesis(){
        if(this.getSizeBlocks() > 0)
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
        for(int i = 1; i < this.getSizeBlocks(); i++) {
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
    
    public void printBlockChain(){
        
        for( Block each : blockchain ){
            JOptionPane.showMessageDialog( null, Block.printBlock(each) );
            System.out.println( Block.printBlock(each) );
        
        }
    }
    
    
    
    
}
