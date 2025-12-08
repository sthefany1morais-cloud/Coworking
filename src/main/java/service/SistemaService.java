package service;

import dao.base.DAOBase;

public class SistemaService {

    public void persistirDados() {
        DAOBase.persistirDadosGlobais();
    }
}