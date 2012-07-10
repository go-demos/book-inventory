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
}
