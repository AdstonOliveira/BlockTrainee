package ClientesRMI;

import ServerRMI.Block;
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Suporte-04
 */
public interface InterfaceTerminal extends Remote{

    public void selecionarArquivo() throws RemoteException;
    
    public boolean adicionarTransacao(File file) throws RemoteException;
    
    public boolean validar(Block bloco) throws RemoteException;
    
    public boolean printBlock() throws RemoteException;
    
        
    


    
}
