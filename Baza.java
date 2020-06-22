import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Baza {

	private Connection povezava;
    private Statement stmt;

    public Baza(){

        try {
            Class.forName("org.sqlite.JDBC");
            povezava = DriverManager.getConnection("jdbc:sqlite:Sudoku.db");

            String sqlUkaz0 = "CREATE TABLE IF NOT EXISTS Uporabnik " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "imeUporabnika CHAR(30))";

            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlUkaz0);
            
            System.out.println("Povezava s SQLite vzpostavljena");

        }catch(Exception e) {
            System.out.println("Prišlo je do napake: " + e.getMessage());
        }
    }
    
    public void disconect(){//to se mora zgodit preden se program ugasne
        try {
            povezava.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void dodajUporabnika(String upIme){
        String sqlInsertUporabnik = "INSERT INTO Uporabnik (imeUporabnika) VALUES ('" + upIme + "');";
        try {
            stmt = povezava.createStatement();
            stmt.executeUpdate(sqlInsertUporabnik);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int countUporabnik(String uporabnik) {
        int count = 0;
        String sqlIsciUporabnika = "SELECT COUNT(*) FROM Uporabniki WHERE imeUporabnika = '" + uporabnik + "'";
        try {
            ArrayList<String> rezultati = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery(sqlIsciUporabnika);

            //Loopam èez use rezultate
            while (rs.next()) {
                rezultati.add(rs.getString(1));
            }
            //Error v queryu
            if(rezultati.size() == 0){
                throw new Exception("Napaèen query");
            }

            if(rezultati.size() == 1){
                count = Integer.parseInt(rezultati.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
