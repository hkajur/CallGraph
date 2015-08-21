package com.callgraph;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

/**
 * <tt>ModuleRecord</tt>
 *
 * @author Venkata Harish K Kajur
 * @version 1.0
 */
public class ModuleRecord extends DBUtils {

    private static final Logger logger = Logger.getLogger(ModuleRecord.class);

    private Set<Integer> lines;
    /**
     *
     * Constructs a Module Record in order to insert into the ModuleInfo table
     *
     * Sets the table name, visible array and fillable array
     *
     */
    public ModuleRecord(){

        this.table = "ModuleInfo";

        this.visible = new String[]{
            "ModuleName",
            "FunctionName",
            "CommandName"
        };

        this.fillable = new String[]{
            "InfoID",
            "ModuleName",
            "FunctionName",
            "CommandName",
            "LineNo"
        };

        lines = new HashSet<Integer>(10);
    }

    /**
     * Constructs a Module Record in order to insert into the ModuleInfo table
     *
     * Calls the default constructor to set the table name, visible and fillable array
     * Sets the values hashtable to the one provided in the constructor
     *
     * @param values    hash table which contains all the values for the ModuleInfo record
     */
    public ModuleRecord(Map<String, Object> values){
        this();
        this.values = values;
    }

    /**
     * Sets the column name and column value to be inserted into the database
     * Returns the reference to this module record
     *
     * @param name      string sets the name of the column to be inserted
     * @param value     string sets the value for the column to be inserted
     * @return          reference to the current object
     */
    public ModuleRecord setString(String name, String value){
        values.put(name, value);
        return this;
    }

    /**
     * Sets the column name and column value to be inserted into the database
     * Returns the reference to this module record
     *
     * @param name      string sets the name of the column to be inserted
     * @param value     integer sets the value for the column to be inserted
     */
    public ModuleRecord setInteger(String name, int value){
        values.put(name, value);
        return this;
    }

    /**
     * Sets the column id of the table in database to the value provided by the parameter
     * Returns the reference to this module record
     *
     * @param id     integer to be set for the value of the id
     */
    public ModuleRecord setId(int id){
        values.put("InfoID", id);
        return this;
    }

    /**
     * Returns the info id from the ModuleRecord
     *
     * @return      integer which represents the primary key
     */
    public int getId(){
        return Integer.parseInt(values.get("InfoID").toString());
    }

    public ModuleRecord setModule(String name){
        values.put("ModuleName", name);
        return this;
    }

    public String getModule(){
        if(values.containsKey("ModuleName"))
            return values.get("ModuleName").toString();
        return null;
    }

    public ModuleRecord setFunction(String name){
        values.put("FunctionName", name);
        return this;
    }

    public String getFunction(){
        if(values.containsKey("FunctionName"))
            return values.get("FunctionName").toString();
        return null;
    }

    public ModuleRecord setCommand(String name){
        values.put("CommandName", name);
        return this;
    }

    public String getCommand(){
        if(values.containsKey("CommandName"))
            return values.get("CommandName").toString();
        return null;
    }

    public ModuleRecord setLine(int line){
        values.put("LineNo", line);
        return this;
    }

    public int getLine(){
        if(values.containsKey("LineNo"))
            return Integer.parseInt(values.get("LineNo").toString());
        return -1;
    }

    /*public ModuleRecord setDBType(String type){
        values.put("DB_TYPE", type);
        return this;
    }

    public String getDBType(){
        if(values.containsKey("DB_TYPE"))
            return values.get("DB_TYPE").toString();
        return null;
    }*/

    public String toString(){

        return String.format("InfoID: %4d\tModule: %-22s\tFunction: %-10s\tCommand: %-30s\tLine: %6d",
                values.get("InfoID"),
                values.get("ModuleName"),
                values.get("FunctionName"),
                values.get("CommandName"),
                values.get("LineNo")/*,
                values.get("DB_TYPE")*/
            );
    }

    /**
     * Returns an ModuleRecord object from the database with the following id
     *
     * @param id    integer which is the id of the row to search for
     * @return      ModuleRecord object with the given id
     */
    public static ModuleRecord find(int id){

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            pstmt = DBUtils.getInstance().prepareStatement("SELECT * FROM ModuleInfo WHERE InfoID = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()){

                ModuleRecord temp = new ModuleRecord();

                temp.setId          (   rs.getInt   (     "InfoID"      )   );
                temp.setModule      (   rs.getString(    "ModuleName"   )   );
                temp.setFunction    (   rs.getString(   "FunctionName"  )   );
                temp.setCommand     (   rs.getString(   "CommandName"   )   );
                temp.setLine        (   rs.getInt   (     "LineNo"      )   );
                //temp.setDBType      (   rs.getString(     "DB_TYPE"     )   );

                return temp;
            }

        } catch(Exception ex){
            handleException(ex);
        } finally {
            try { if(rs     != null){     rs.close();  } } catch(Exception ex){ handleException(ex); }
            try { if(pstmt  != null){  pstmt.close();  } } catch(Exception ex){ handleException(ex); }
        }

        return null;
    }

    /**
     * Returns true if the row id specified is successfully deleted from database
     * Otherwise returns false if unsuccessful in deleting the row or unable to find it
     *
     * @param id    integer which is the id of the row to search for
     * @return      boolean indicating if the operation to database was successful
     */
    public static boolean delete(int id){

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            String delQuery = String.format("DELETE FROM ModuleInfo WHERE %s = ?", "InfoId");

            pstmt = DBUtils.getInstance().prepareStatement(delQuery);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            if(rows != 0)
                return true;

        } catch(Exception ex){
            handleException(ex);
        } finally {
            try { if(rs     != null){     rs.close();  } } catch(Exception ex){ handleException(ex); }
            try { if(pstmt  != null){  pstmt.close();  } } catch(Exception ex){ handleException(ex); }
        }

        return false;
    }

    /**
     * Returns a array list of Module Record objects that is the representation of all rows
     *
     * @return      list of module record objects
     */
    public static List<ModuleRecord> all(){

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<ModuleRecord> list = new ArrayList<ModuleRecord>();

        try {

            pstmt = DBUtils.getInstance().prepareStatement("SELECT * FROM ModuleInfo");
            rs = pstmt.executeQuery();

            while(rs.next()){

                ModuleRecord temp = new ModuleRecord();

                temp.setId          (   rs.getInt   (   "InfoID"        )   );
                temp.setModule      (   rs.getString(   "ModuleName"    )   );
                temp.setFunction    (   rs.getString(   "FunctionName"  )   );
                temp.setCommand     (   rs.getString(   "CommandName"   )   );
                temp.setLine        (   rs.getInt   (   "LineNo"        )   );
                //temp.setDBType      (   rs.getString(   "DB_TYPE"       )   );

                list.add(temp);
            }

        } catch(Exception ex){
            handleException(ex);
        } finally {
            try { if(rs     != null){     rs.close();  } } catch(Exception ex){ handleException(ex); }
            try { if(pstmt  != null){  pstmt.close();  } } catch(Exception ex){ handleException(ex); }
        }

        return list;
    }

    public static List<ModuleRecord> distinctFunctions(String name){

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<ModuleRecord> list = new ArrayList<ModuleRecord>();

        try {

            String qry = "SELECT DISTINCT(CommandName) AS CommandName, ModuleName, LineNo FROM ModuleInfo WHERE FunctionName REGEXP ? ORDER BY ModuleName";

            pstmt = DBUtils.getInstance().prepareStatement(qry);
            pstmt.setString(1, name);
            pstmt.setString(2, "DEFAULT");

            rs = pstmt.executeQuery();

            while(rs.next()){

                ModuleRecord temp = new ModuleRecord();

                temp.setId          (                   -1                  );
                temp.setModule      (   rs.getString(   "ModuleName"    )   );
                temp.setFunction    (                   name                );
                temp.setCommand     (   rs.getString(   "CommandName"   )   );
                temp.setLine        (   rs.getInt   (   "LineNo"        )   );
                //temp.setDBType      (                "DEFAULT"              );

                list.add(temp);
            }

        } catch(Exception ex){
            handleException(ex);
        } finally {
            try { if(rs     != null){     rs.close();  } } catch(Exception ex){ handleException(ex); }
            try { if(pstmt  != null){  pstmt.close();  } } catch(Exception ex){ handleException(ex); }
        }

        return list;
    }

    public static void main(String[] args){

        /*List<ModuleRecord> list = ModuleRecord.distinctFunctions("wait4input");

        for(ModuleRecord module : list){
            System.out.println(module);
        }*/

        List<ModuleRecord> list = ModuleRecord.all();
    }

}
