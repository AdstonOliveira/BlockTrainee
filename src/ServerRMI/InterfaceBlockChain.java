package ServerRMI;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Suporte-04
 */
public interface InterfaceBlockChain extends Remote{
    /**
     *
     * @return
     * @throws RemoteException
     */
    abstract boolean addGenesis() throws RemoteException;
    abstract void newBlock() throws RemoteException;
    
    public boolean addTransaction(File file) throws RemoteException;
    public boolean isChainValid() throws RemoteException;
    public void printBlockChain() throws RemoteException; 
    
    
    
    
    
    
    
    
}
