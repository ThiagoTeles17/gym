package com.gym.gym.DAO;

import com.gym.gym.model.PlanModel;
import com.gym.gym.model.PlanModel;
import com.gym.gym.util.DBUtil;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class PlanDAO {

    public List<PlanModel> plansList = new ArrayList<>();

    public int plansLength;

    private int alunosAtivos;
    
    public void RegisterPlan(PlanModel plan){
        String sql = "INSERT INTO `planos` (`id`, `nome`, `descricao`, `valor`) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = null;

        try {
            
            statement = DBUtil.getConnection().prepareStatement(sql);
            statement.setInt(1, plan.getId());
            statement.setString(2,  plan.getNome());
            statement.setString(3, plan.getDescricao());
            statement.setDouble(4, plan.getValor());

            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
    public void getPlans()  {

        plansList.clear();

        String sql = "SELECT * FROM planos";

        Statement statement = null;

        PreparedStatement ps = null;


        try {
            statement = DBUtil.getConnection().createStatement();

            //Prepare statement query to get alunosAtivos in plan
            ps = DBUtil.getConnection().prepareStatement("SELECT COUNT(*) FROM `clientes` WHERE idPlano=? AND ativo=1");

            ResultSet rs = statement.executeQuery(sql);

            //Set plan attributes
            while (rs.next()){
                PlanModel plan = new PlanModel();
                plan.setId(rs.getInt("id"));
                plan.setNome(rs.getString("nome"));
                plan.setDescricao(rs.getString("descricao"));
                plan.setValor(rs.getDouble("valor"));
                ps.setInt(1, rs.getInt("id"));
                ResultSet ativosRs = ps.executeQuery();
                while(ativosRs.next()){
                    plan.setAlunosAtivos(ativosRs.getInt("COUNT(*)"));
                }
                plansList.add(plan);
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removePlan(int id, String nome){
        String sql = "DELETE FROM `planos` WHERE id=? AND nome=?";

        PreparedStatement statement = null;

        try {
            statement = DBUtil.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, nome);

            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getLastId(){
        String sql = "SELECT max(id) FROM `planos`";

        Statement statement = null;

        try {
            statement = DBUtil.getConnection().createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                return rs.getInt("max(id)");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void EditPlan(PlanModel plan){
        String sql = "UPDATE `planos` SET id=?, nome=?, descricao=?, valor=?";

        PreparedStatement statement = null;

        try {
            statement = DBUtil.getConnection().prepareStatement(sql);
            statement.setInt(1, plan.getId());
            statement.setString(2,  plan.getNome());
            statement.setString(3, plan.getDescricao());
            statement.setDouble(4, plan.getValor());

            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }

    public List<PlanModel> getPlansList(){
        return plansList;
    }

    public int getPlansLength() {
        Statement statement = null;
        try {
            statement = DBUtil.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM `planos`");
            while (rs.next()){
                plansLength = rs.getInt("COUNT(*)");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return plansLength;
    }
    public PlanModel getPlanById(int id){
        Statement statement = null;
        PlanModel planModel = new PlanModel();
        try {
            statement = DBUtil.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `planos` WHERE id='"+id+"'");
            while (rs.next()){
                planModel.setId(rs.getInt("id"));
                planModel.setNome(rs.getString("nome"));
                planModel.setDescricao(rs.getString("descricao"));
                planModel.setValor(rs.getDouble("valor"));
                planModel.setModalidadePagamento(rs.getString("modalidadePagamento"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return planModel;
    };

}
