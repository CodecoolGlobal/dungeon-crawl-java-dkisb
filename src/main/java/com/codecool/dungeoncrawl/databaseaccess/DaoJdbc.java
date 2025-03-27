package com.codecool.dungeoncrawl.databaseaccess;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DaoJdbc {

    public static DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("dungeon_crawl");
        dataSource.setUser("postgres");
        dataSource.setPassword("2480");
        return dataSource;
    }

}
