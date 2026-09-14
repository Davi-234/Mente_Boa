package davi.ifmg.mente_boa.dao;

import davi.ifmg.mente_boa.bd.BancoDeDados;
import davi.ifmg.mente_boa.model.Ranque;

public class RanqueDAO {

    public RanqueDAO() {

    }

    public Ranque[] getAll() {
        return BancoDeDados.getInstance().getRanque();
    }

    public void limparDados() {
        Ranque[] ranque = getAll();

        for (int i = 0; i < ranque.length; i++) {
            ranque[i] = null;
        }

    }

    public boolean inserir(Ranque ra) {
        Ranque[] ranque = getAll();

        for (int i = 0; i < ranque.length; i++) {
            if (ranque[i] == null) {
                ranque[i] = ra;
                return true;
            }
        }
        
        return false;
    }

}
