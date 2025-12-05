package execoes;

public class PagamentoInexistenteException extends Exception{
    public PagamentoInexistenteException(String message) {
        super(message);
    }
}
