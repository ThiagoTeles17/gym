package com.gym.gym.DAO;

import com.gym.gym.model.ClientModel;
import com.gym.gym.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UfCidadesDAO {

    List<String> Ufs = new ArrayList<>();
    List<String> cidadesByUf = new ArrayList<>();

    public List<String> getUFs(){
        String sql = "SELECT `uf` FROM estado";

        Statement ps = null;

        try {
            ps = DBUtil.getConnection().createStatement();

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                Ufs.add(rs.getString("uf"));


            }

        }
        catch (SQLException e){
            e.printStackTrace();

        }
        return Ufs;
    }

    public List<String> getCidadesByUf(String uf){
        cidadesByUf.clear();

        String ufID = "0";

        String sql = "SELECT `nome` FROM cidade WHERE idestado=?";

        PreparedStatement ps = null;

        try {

            ps = DBUtil.getConnection().prepareStatement("SELECT `idestado` FROM estado WHERE uf=?");
            ps.setString(1, uf);

            ResultSet ufIDrs = ps.executeQuery();

            while (ufIDrs.next()) ufID = ufIDrs.getString("idestado");

            ps = DBUtil.getConnection().prepareStatement(sql);
            ps.setString(1, ufID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                cidadesByUf.add(rs.getString("nome"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();

        }
        return cidadesByUf;
    }

}
