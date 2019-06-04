package ServerRMI;

import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Suporte-04
 */
public class Blockchain extends UnicastRemoteObject implements InterfaceBlockChain{
    private static final long serialVersionUID = 1L;
    
    private final ArrayList <Block> blockchain = new ArrayList();
    
    private final ArrayList<Block> pool = new ArrayList();//COMPARTILHADO
    
    //private int difficulty = 5;
    
    public Blockchain() throws RemoteException{
       super();
        if( !this.addGenesis() )
            System.out.println("Ocorreu um erro ao inicializar a blockchain");
        
            
        JOptionPane.showMessageDialog(null,"Blockchain inicializada com sucesso\nOperando no bloco Genesis");
        
    }
    
    @Override
    public boolean addGenesis(){
        if( this.blockchain.isEmpty() ){
           Block genesis = new Block(this);
           this.pool.add(genesis); //Adiciona ao pool
           
           return true;
        }
        return false;
    }


    @Override
    public void newBlock(){
        if( this.blockchain.isEmpty() ){
            this.addGenesis();
        }else{
             Block block = new Block( this, this.getLast().getHash() );
             this.pool.add(block);
        }
    }
    
    @Override
    public boolean addTransaction( File file ){ // CANDIDADTO INTERFACE REMOTA
        if( this.getOnPool().add_transation(file) ){
            return true;
            //Adicionado com sucesso
        }else{
             this.newBlock();
             this.addTransaction(file);
        }
        return false;
    }
    
    public Block getOnPool(){
        return this.pool.get( this.getSizePool()-1 );
    }
    
    public int getSizePool(){
        return this.pool.size();
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
    
    @Override
    public boolean isChainValid(){ // CANDIDATO A SER REMOTO
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
    
    public void printBlockChain(){ // CANDIDATO A SER REMOTO
        
        for( Block each : blockchain ){
            JOptionPane.showMessageDialog( null, Block.printBlock(each) );
            System.out.println( Block.printBlock(each) );
        
        }
    }
    
    
    
    
}
