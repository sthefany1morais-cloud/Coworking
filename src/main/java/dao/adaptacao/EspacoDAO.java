package dao.adaptacao;

import dao.base.DAOBase;
import model.espacos.Espaco;

public class EspacoDAO extends DAOBase<Espaco> {
    public EspacoDAO() {
        super(Espaco.class);
    }
}
