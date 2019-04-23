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
    public Block( Vote data, String previousHash ) {
        this.dado = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //Método cria um hash, baseando no hash anterior, no timestamp, no 'nonce' e no dado
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(previousHash
                + Long.toString(timeStamp)
                + Integer.toString(nonce)
                + dado
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
        
        while (!hash.substring(0, difficulty).equals(target)) { // 
            nonce++;
            hash = calculateHash();
        }
        
        System.out.println("Block Mined!!! : " + hash);
    }
}
