import java.io.File;
import java.util.Date;

/**
 *
 * @author Suporte04
 */

public class Block {
    public String hash; // Hash do atual
    public String previousHash; // Hash anterior
    private File dado; //Dado a ser adicionado ao bloco
    private long timeStamp; //data atual 
    private int nonce;
    
    private final int qtdeTransacoes  = 10;

//Block Constructor.
    public Block( File dado, String previousHash ) {
        this.dado = dado;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    
    public Block(File dado){
        this.dado = dado;
        this.previousHash = "Genesis Block";
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    
    

    //Método cria um hash, baseando no hash anterior, no timestamp, no 'nonce' e no dado
    
    //Verificar o hasH por OpenSSl
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256( previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + dado
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        /*Prova de trabalho tentando diferentes valores de variáveis ​​no bloco até que seu hash comece 
        com um certo número de 0s.*/
        
        //Cria um array com tamanho da dificuldade 
        String target = new String( new char[difficulty] ).replace('\0', '0'); 
        
        while ( !hash.substring(0, difficulty).equals(target) ) { // Divide o hash da posicao 0, ate a qta 0
            nonce++;
            hash = calculateHash(); // o nonce serve para a quantdade de hash gerados...
        } // Gera varios hash's, ate que algum contenha a qtde desejadas de 0 no inicio
        
        System.out.println("Block Mined!!! : " + hash);
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

    public File getDado() {
        return dado;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getQtdeTransacoes() {
        return qtdeTransacoes;
    }
    
    
    
    
}
