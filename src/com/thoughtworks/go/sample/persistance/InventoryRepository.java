package com.thoughtworks.go.sample.persistance;

import com.thoughtworks.go.sample.models.StockItem;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class InventoryRepository extends HibernateDaoSupport {

    public InventoryRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    public void save(StockItem stockItem) {
        getHibernateTemplate().save(stockItem);
    }

    public List<StockItem> currentLedger() {
        return getHibernateTemplate().find("from StockItem");
    }

    public StockItem stockFor(String isbn) {
        List list = getHibernateTemplate().find("FROM StockItem WHERE isbn = ?", isbn);
        if (list.isEmpty()) return null;
        return (StockItem) list.get(0);
    }
}
