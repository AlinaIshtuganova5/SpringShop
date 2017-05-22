package com.itis.shop.business.dao.impl;

import com.itis.shop.business.dao.ItemDao;
import com.itis.shop.model.Item;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

/**
 * Created on 19.05.17.
 */
@Repository
@Transactional
public class ItemDaoImpl implements ItemDao {

    @PersistenceContext/*(unitName = "entityManagerFactory")*/
    private EntityManager entityManager;

    @Override
    public List<Item> search(String seach) throws InterruptedException{
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Item.class).get();
        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onFields("name", "description")
                .matching(seach)
                .createQuery();

        javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Item.class);
        List<Item> result = persistenceQuery.getResultList();

        return result;
    }
}
