package com.umeng.soexample.ui.shop.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.umeng.soexample.model.data.GreendaoBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GREENDAO_BEAN".
*/
public class GreendaoBeanDao extends AbstractDao<GreendaoBean, Long> {

    public static final String TABLENAME = "GREENDAO_BEAN";

    /**
     * Properties of entity GreendaoBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Price = new Property(1, int.class, "price", false, "PRICE");
        public final static Property Number = new Property(2, int.class, "number", false, "NUMBER");
        public final static Property Desc = new Property(3, String.class, "desc", false, "DESC");
        public final static Property Cb = new Property(4, Boolean.class, "cb", false, "CB");
        public final static Property Img = new Property(5, String.class, "img", false, "IMG");
    }


    public GreendaoBeanDao(DaoConfig config) {
        super(config);
    }
    
    public GreendaoBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GREENDAO_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PRICE\" INTEGER NOT NULL ," + // 1: price
                "\"NUMBER\" INTEGER NOT NULL ," + // 2: number
                "\"DESC\" TEXT," + // 3: desc
                "\"CB\" INTEGER," + // 4: cb
                "\"IMG\" TEXT);"); // 5: img
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GREENDAO_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GreendaoBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPrice());
        stmt.bindLong(3, entity.getNumber());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(4, desc);
        }
 
        Boolean cb = entity.getCb();
        if (cb != null) {
            stmt.bindLong(5, cb ? 1L: 0L);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(6, img);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GreendaoBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPrice());
        stmt.bindLong(3, entity.getNumber());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(4, desc);
        }
 
        Boolean cb = entity.getCb();
        if (cb != null) {
            stmt.bindLong(5, cb ? 1L: 0L);
        }
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(6, img);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GreendaoBean readEntity(Cursor cursor, int offset) {
        GreendaoBean entity = new GreendaoBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // price
            cursor.getInt(offset + 2), // number
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // desc
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // cb
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // img
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GreendaoBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPrice(cursor.getInt(offset + 1));
        entity.setNumber(cursor.getInt(offset + 2));
        entity.setDesc(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCb(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setImg(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GreendaoBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GreendaoBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GreendaoBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
