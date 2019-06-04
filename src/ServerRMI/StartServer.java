package ServerRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Suporte-04
 */
public class StartServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            
            InterfaceBlockChain sistema = new Blockchain();
            registry.rebind("blockchain", sistema);
            
            System.out.println("Blockchain registrada ao rmiregistry");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
