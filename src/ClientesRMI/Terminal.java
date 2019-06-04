package ClientesRMI;

import ServerRMI.Block;
import Util.SelectXML;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Suporte-04
 */
public class Terminal extends UnicastRemoteObject implements InterfaceTerminal{
    private static final long serialVersionUID = 1L;
    
    private File arquivoXML;
    
    public Terminal() throws RemoteException{
        super();
    }
    @Override
    public void selecionarArquivo() throws RemoteException {
        this.arquivoXML = SelectXML.selected();
    }

    @Override
    public boolean adicionarTransacao(File file) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validar(Block bloco) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean printBlock() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
