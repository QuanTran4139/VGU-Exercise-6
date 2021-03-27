package com.vgu.sqm.questionnaire.core;

import org.json.simple.JSONObject;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Module {
    private String ModuleID;
    private String ModuleName;

    public Module(String ModuleID, String ModuleName){
        this.ModuleID = ModuleID;
        this.ModuleName = ModuleName;
    }

    public Hashtable<String, String> getJsonData() throws SQLException, NamingException {
        Connection db = com.vgu.sqm.questionnaire.core.Configuration.getAcademiaConnection();
        int statusCode;

        try {

            PreparedStatement st = db.prepareCall(
                    "SELECT * FROM module;");
            ResultSet rs = st.executeQuery();
            Hashtable<String,String> module = new Hashtable<>();
            while (rs.next()) {
                String id = rs.getString("ModuleId");
                String name = rs.getString("ModuleName");
                System.out.println(id);
                System.out.println(name);
                module.put(id,name);
            }
            return module;

        }
        finally {
            db.close();
        }
    }
}
