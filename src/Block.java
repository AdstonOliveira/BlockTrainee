import java.util.Date;

/**
 *
 * @author Suporte04
 */

public class Block {
    public String hash; // Hash do atual
    public String previousHash; // Hash anterior
    private Vote dado; //Dado a ser adicionado ao bloco
    private long timeStamp; //data atual 
    private int nonce;

//Block Constructor.
    public Block( Vote dado, String previousHash ) {
        this.dado = dado;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //Método cria um hash, baseando no hash anterior, no timestamp, no 'nonce' e no dado
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
    
    
    public int getNumberCandidate(){
        return dado.getNumber();
    }
    
}
