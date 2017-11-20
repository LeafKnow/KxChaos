package com.yq.common.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yq.common.model.sys.SystemCount;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SYSTEM_COUNT".
*/
public class SystemCountDao extends AbstractDao<SystemCount, Void> {

    public static final String TABLENAME = "SYSTEM_COUNT";

    /**
     * Properties of entity SystemCount.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property StartCount = new Property(0, Long.class, "startCount", false, "START_COUNT");
    }


    public SystemCountDao(DaoConfig config) {
        super(config);
    }
    
    public SystemCountDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SYSTEM_COUNT\" (" + //
                "\"START_COUNT\" INTEGER);"); // 0: startCount
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SYSTEM_COUNT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SystemCount entity) {
        stmt.clearBindings();
 
        Long startCount = entity.getStartCount();
        if (startCount != null) {
            stmt.bindLong(1, startCount);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SystemCount entity) {
        stmt.clearBindings();
 
        Long startCount = entity.getStartCount();
        if (startCount != null) {
            stmt.bindLong(1, startCount);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public SystemCount readEntity(Cursor cursor, int offset) {
        SystemCount entity = new SystemCount( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0) // startCount
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SystemCount entity, int offset) {
        entity.setStartCount(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(SystemCount entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(SystemCount entity) {
        return null;
    }

    @Override
    public boolean hasKey(SystemCount entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
