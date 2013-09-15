
package com.bbisercic.ort1.database.dao;

import com.bbisercic.ort1.database.dao.impl.SqliteDaoImpl;

/**
 * Class for retrieving an instance of a DAO implementation
 */
public class DaoFactory {

    private static DaoInterface sInstance = null;

    // This class can not be instantiated.
    private DaoFactory() {

    }

    /**
     * Retrieves singleton instance of the data access object.
     * 
     * @return
     */
    public static DaoInterface getInstance() {
        if (sInstance == null) {
            sInstance = new SqliteDaoImpl();
        }
        return sInstance;
    }
}
