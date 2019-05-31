import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte04
 */

public class Block {
    public final String previousHash; // Hash anterior
    
    private long timeStamp; //data atual 
    public String hash = "incomplent block"; // Hash do atual
    
    private ArrayList<File> dados = new ArrayList(); //Dado a ser adicionado ao bloco
    private int nonce = 0;
    
    private final int amount_transactions;
    
    private volatile boolean aprovade = false;

    //Blocos seguintes *****
    public Block( String previousHash, int amount_transactions ) {
        this.amount_transactions = amount_transactions;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        
        System.out.println("Bloco seguinte a: " + this.previousHash);
    }
    
    //Bloco Genesis *****
    public Block( int amount_transactions ){
        this.amount_transactions = amount_transactions;
        this.previousHash = "Genesis Block";
        this.timeStamp = new Date().getTime();
    }
    
    //VERIFICA SE O BLOCO SE ENCONTRA COMPLETO.
    public boolean isFull(){
        System.out.println("Estou no is full, quantidade de transações: " + this.dados.size() );
        return this.dados.size() >= this.amount_transactions;
    }
    
    public boolean add_transation( File file ){
        JOptionPane.showConfirmDialog(null,"add_transaction");
        
        if( !this.isFull() ){
            this.dados.add(file);
            System.out.println("Tamanho: " + this.dados.size());
            return true;    
        }else
            return false;
        
    }
    
    public boolean calculate_hash(){
        
        if( this.isFull() ){
            this.mineBlock(5);
            //Enviar alerta para consenso
            return true;
        }else{
            System.out.println("Hash can't be created because it isn't full!!!");
            return false;
        }
    }
    
    //Método cria um hash, baseando no hash anterior, no timestamp, no 'nonce' e no dado
    //Verificar o hasH por OpenSSl
    private String calculateHash() {
        
        String calculatedhash = StringUtil.applySha256( previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + dados
        );
        return calculatedhash;
    }

    
    public void mineBlock( int difficulty ) {
        /*Prova de trabalho tentando diferentes valores de variáveis ​​no bloco até que seu hash comece 
        com um certo número de 0s.*/
        //Cria um array com tamanho da dificuldade 
        if( this.isFull() ){
            String target = new String( new char[difficulty] ).replace('\0', '0');
        
            while ( !hash.substring(0, difficulty).equals(target) ) { // Divide o hash da posicao 0, ate a qta 0
                nonce++;
                hash = calculateHash(); // o nonce serve para a quantdade de hash gerados...
            } // Gera varios hash's, ate que algum contenha a qtde desejadas de 0 no inicio
            System.out.println("Block Mined!!! : " + this.hash);
        }
        else{
            System.out.println("this block can't be mined because it isn't full!!!");
        }
        
    }
    
    
    public int getNonce(){
        return this.nonce;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getQtdeTransacoes() {
        return amount_transactions;
    }
    
    
    
    
}
