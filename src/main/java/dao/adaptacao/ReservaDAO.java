package dao.adaptacao;

import dao.base.DAOBase;
import model.reservas.Reserva;

public class ReservaDAO extends DAOBase<Reserva> {
    public ReservaDAO() {
        super(Reserva.class);
    }
}
