package dao;

import fechamentodecaixa.FilesFolder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Dia;
import models.Fechamento;
import models.Saida;

public class DAO {

    private static String dbPath = "jdbc:sqlite:" + FilesFolder.path + "database/atual/caixa.db";

    public static boolean insertFechamento(Fechamento fechamento, String data) {
        try {
            int idDia;
            String sql;
            PreparedStatement ps;

            Connection conn = DriverManager.getConnection(dbPath);

            sql = "INSERT INTO fechamento(turno, entrada, valorCaixa, valorDisplay, cartao) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, fechamento.getTurno());
            ps.setFloat(2, fechamento.getEntrada());
            ps.setFloat(3, fechamento.getValorCaixa());
            ps.setFloat(4, fechamento.getValorDisplay());
            ps.setFloat(5, fechamento.getCartao());

            ps.executeUpdate();
            fechamento.setIdFechamento(ps.getGeneratedKeys().getInt(1));

            ps = conn.prepareStatement("SELECT * FROM dia WHERE data = ?");
            ps.setString(1, data);
            ResultSet rsDia = ps.executeQuery();

            if (rsDia.next()) {
                idDia = rsDia.getInt("idDia");

                if (fechamento.getTurno() == 0) {
                    sql = "UPDATE dia SET idFechamentoManha = ? WHERE idDia = ?";
                } else {
                    sql = "UPDATE dia SET idFechamentoTarde = ? WHERE idDia = ?";
                }
                ps = conn.prepareStatement(sql);
                ps.setInt(1, fechamento.getIdFechamento());
                ps.setInt(2, idDia);
                ps.executeUpdate();
            } else {
                sql = "INSERT INTO dia(data, idFechamentoManha, idFechamentoTarde) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, data);
                ps.setInt(2, fechamento.getTurno() == 0 ? fechamento.getIdFechamento() : 0);
                ps.setInt(3, fechamento.getTurno() == 1 ? fechamento.getIdFechamento() : 0);
                ps.executeUpdate();

                idDia = ps.getGeneratedKeys().getInt(1);
            }

            sql = "UPDATE fechamento SET idDia = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idDia);
            ps.executeUpdate();
            
            insertSaidas(conn, fechamento.getIdFechamento(), fechamento.getSaidas());
            /*
            sql = "INSERT INTO saida(idFechamento, descricao, valor) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            for (Saida s : fechamento.getSaidas()) {
                ps.setInt(1, fechamento.getIdFechamento());
                ps.setString(2, s.getDescricao());
                ps.setFloat(3, s.getValor());
                ps.executeUpdate();
            }
             */

            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean updateFechamento(Fechamento fechamento) {
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            String sql = "UPDATE fechamento SET entrada = ?, valorCaixa = ?, valorDisplay = ?, cartao = ? WHERE idFechamento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setFloat(1, fechamento.getEntrada());
            ps.setFloat(2, fechamento.getValorCaixa());
            ps.setFloat(3, fechamento.getValorDisplay());
            ps.setFloat(4, fechamento.getCartao());
            ps.setInt(5, fechamento.getIdFechamento());

            ps.executeUpdate();
            
            
            sql = "DELETE FROM saida WHERE idFechamento = ?";
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, fechamento.getIdFechamento());
            
            ps.executeUpdate();
            
            insertSaidas(conn, fechamento.getIdFechamento(), fechamento.getSaidas());
            
            
            sql = "DELETE FROM ficaCaixa WHERE idFechamento = ?";
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, fechamento.getIdFechamento());
            
            ps.executeUpdate();
            
            
            conn.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Fechamento[] selectAllFechamentos() {
        try {
            ArrayList<Fechamento> arrayFechamentos = new ArrayList<Fechamento>();

            Connection conn = DriverManager.getConnection(dbPath);
            Statement stmt = conn.createStatement();
            ResultSet rsFechamentos = stmt.executeQuery("SELECT * FROM fechamento");
            ResultSet rsAux;

            while (rsFechamentos.next()) {
                int idFechamento = rsFechamentos.getInt("idFechamento");

                ArrayList<Saida> arraySaidas = new ArrayList<Saida>();
                rsAux = stmt.executeQuery("SELECT * from saida WHERE idFechamento = " + String.valueOf(idFechamento));
                while (rsAux.next()) {
                    arraySaidas.add(new Saida(
                            rsAux.getInt("idSaida"),
                            idFechamento,
                            rsAux.getString("descricao"),
                            rsAux.getFloat("valor")
                    ));
                }

                arrayFechamentos.add(new Fechamento(
                        idFechamento,
                        rsFechamentos.getInt("idDia"),
                        rsFechamentos.getInt("turno"),
                        rsFechamentos.getFloat("entrada"),
                        rsFechamentos.getFloat("valorCaixa"),
                        rsFechamentos.getFloat("valorDisplay"),
                        rsFechamentos.getFloat("cartao"),
                        arraySaidas.toArray(new Saida[0])
                ));
            }

            conn.close();

            return arrayFechamentos.toArray(new Fechamento[0]);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean fechamentoJaCadastrado(String data, int turno) {
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            String sql = "SELECT * FROM dia WHERE data = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, data);

            ResultSet rs = ps.executeQuery();

            boolean result;
            if (rs.next()) {
                if (turno == 0) {
                    int idFechamentoManha = rs.getInt("idFechamentoManha");
                    result = idFechamentoManha != 0;
                } else {
                    int idFechamentoTarde = rs.getInt("idFechamentoTarde");
                    result = idFechamentoTarde != 0;
                }
            } else {
                result = false;
            }

            conn.close();

            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Dia[] selectAllDias() {
        try {
            ArrayList<Dia> arrayDias = new ArrayList<>();

            Connection conn = DriverManager.getConnection(dbPath);
            Statement stmt = conn.createStatement();
            ResultSet rsDias = stmt.executeQuery("SELECT * FROM dia");

            PreparedStatement ps;
            ResultSet rsFechamento;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            while (rsDias.next()) {
                int idDia = rsDias.getInt("idDia");

                Calendar data = Calendar.getInstance();
                data.setTime(sdf.parse(rsDias.getString("data")));

                Fechamento fechamentoManha, fechamentoTarde;
                int idFechamentoManha = rsDias.getInt("idFechamentoManha");
                int idFechamentoTarde = rsDias.getInt("idFechamentoTarde");

                if (idFechamentoManha != 0) {
                    ps = conn.prepareStatement("SELECT * FROM fechamento WHERE idFechamento = ?");
                    ps.setInt(1, idFechamentoManha);
                    rsFechamento = ps.executeQuery();

                    fechamentoManha = new Fechamento(
                            rsFechamento.getInt("idFechamento"),
                            rsFechamento.getInt("idDia"),
                            rsFechamento.getInt("turno"),
                            rsFechamento.getFloat("entrada"),
                            rsFechamento.getFloat("valorCaixa"),
                            rsFechamento.getFloat("valorDisplay"),
                            rsFechamento.getFloat("cartao"),
                            selectSaidasFromFechamento(conn, idFechamentoManha)
                    );
                } else {
                    fechamentoManha = null;
                }

                if (idFechamentoTarde != 0) {
                    ps = conn.prepareStatement("SELECT * FROM fechamento WHERE idFechamento = ?");
                    ps.setInt(1, idFechamentoTarde);
                    rsFechamento = ps.executeQuery();

                    fechamentoTarde = new Fechamento(
                            rsFechamento.getInt("idFechamento"),
                            rsFechamento.getInt("idDia"),
                            rsFechamento.getInt("turno"),
                            rsFechamento.getFloat("entrada"),
                            rsFechamento.getFloat("valorCaixa"),
                            rsFechamento.getFloat("valorDisplay"),
                            rsFechamento.getFloat("cartao"),
                            selectSaidasFromFechamento(conn, idFechamentoTarde)
                    );
                } else {
                    fechamentoTarde = null;
                }

                arrayDias.add(new Dia(
                        idDia,
                        data,
                        fechamentoManha,
                        fechamentoTarde
                ));
            }

            conn.close();

            return arrayDias.toArray(new Dia[0]);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static boolean insertSaidas(Connection conn, int idFechamento,  Saida[] saidas) {
        try {
            String sql = "INSERT INTO saida(idFechamento, descricao, valor) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Saida s : saidas) {
                ps.setInt(1, idFechamento);
                ps.setString(2, s.getDescricao());
                ps.setFloat(3, s.getValor());
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    private static Saida[] selectSaidasFromFechamento(Connection conn, int idFechamento) {
        try {
            ArrayList<Saida> arraySaidas = new ArrayList<Saida>();
            Statement stmt = conn.createStatement();
            ResultSet rsAux = stmt.executeQuery("SELECT * from saida WHERE idFechamento = " + String.valueOf(idFechamento));
            while (rsAux.next()) {
                arraySaidas.add(new Saida(
                        rsAux.getInt("idSaida"),
                        rsAux.getInt("idFechamento"),
                        rsAux.getString("descricao"),
                        rsAux.getFloat("valor")
                ));
            }

            return arraySaidas.toArray(new Saida[0]);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String selectDataFromDia(int idDia) {
        try {
            Connection conn = DriverManager.getConnection(dbPath);
            PreparedStatement ps = conn.prepareStatement("SELECT data FROM dia WHERE idDia = ?");

            ps.setInt(1, idDia);
            ResultSet rs = ps.executeQuery();
            
            String data = rs.getString("data");
            
            conn.close();

            return data;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
