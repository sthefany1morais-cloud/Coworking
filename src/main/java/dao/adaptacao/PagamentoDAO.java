package dao.adaptacao;

import dao.base.DAOBase;
import model.pagamentos.Pagamento;

public class PagamentoDAO extends DAOBase<Pagamento> {
    public PagamentoDAO() {
        super(Pagamento.class);
    }
}
