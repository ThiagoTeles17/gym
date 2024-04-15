package com.gym.gym.DAO;

import com.gym.gym.model.ClientModel;
import com.gym.gym.util.DBUtil;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {


    public List<ClientModel> clientsList = new ArrayList<>();

    public int clientsLength;

    public int clientsAtivosLength;

    public void RegisterClient(ClientModel client){
        String sql = "INSERT INTO `clientes` (`numMatricula`, `dataMatricula`, `idPlano`, `nome`, `sobrenome`, `rg`, `emissorRg`, `cpf`, `dataNascimento`, `sexo`, `cidadeNaturalidade`, `ufNaturalidade`, `cidadeEndereco`, `ufEndereco`, `nomeMae`, `nomePai`, `logradouro`, `numEndereco`, `bairroEndereco`, `complementoEndereco`, `telefone`, `celular`, `telefoneEmergencia`, `ativo`, `profilePic`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            
            ps = DBUtil.getConnection().prepareStatement(sql);
            ps.setString(1, String.valueOf(client.getNumMatricula()));
            ps.setDate(2,  client.getDataMatricula());
            ps.setInt(3, client.getIdPlano());
            ps.setString(4, client.getNome());
            ps.setString(5, client.getSobrenome());
            ps.setString(6, client.getRg());
            ps.setString(7, client.getEmissorRg());
            ps.setString(8, client.getCpf());
            ps.setDate(9, client.getDataNascimento());
            ps.setString(10, client.getSexo());
            ps.setString(11, client.getCidadeNaturalidade());
            ps.setString(12, !client.getUfNaturalidade().equals("null")? client.getUfNaturalidade() : "");
            ps.setString(13, client.getCidadeEndereco());
            ps.setString(14, client.getUfEndereco());
            ps.setString(15, client.getNomeMae());
            ps.setString(16, client.getNomePai());
            ps.setString(17, client.getLogradouro());
            ps.setString(18, client.getNumEndereco());
            ps.setString(19, client.getBairroEndereco());
            ps.setString(20, client.getComplementoEndereco());
            ps.setString(21, client.getTelefone());
            ps.setString(22, client.getCelular());
            ps.setString(23, client.getTelefoneEmergencia());
            ps.setBoolean(24, client.getAtivo());
            ps.setBlob(25, client.getProfilePic());

            ps.execute();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
    public void getClients()  {

        clientsList.clear();

        String sql = "SELECT * FROM clientes";

        Statement ps = null;

        try {
            ps = DBUtil.getConnection().createStatement();

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                ClientModel client = new ClientModel();
                client.setNome(rs.getString("nome"));
                client.setSobrenome(rs.getString("sobrenome"));
                client.setDataMatricula(rs.getDate("dataMatricula"));
                client.setIdPlano(rs.getInt("idPlano"));
                client.setRg(rs.getString("rg"));
                client.setEmissorRg(rs.getString("emissorRg"));
                client.setSexo(rs.getString("sexo"));
                client.setCidadeNaturalidade(rs.getString("cidadeNaturalidade"));
                client.setUfNaturalidade(rs.getString("ufNaturalidade"));
                client.setCidadeEndereco(rs.getString("cidadeEndereco"));
                client.setUfEndereco(rs.getString("ufEndereco"));
                client.setNomeMae(rs.getString("nomeMae"));
                client.setNomePai(rs.getString("nomePai"));
                client.setLogradouro(rs.getString("logradouro"));
                client.setNumEndereco(rs.getString("numEndereco"));
                client.setBairroEndereco(rs.getString("bairroEndereco"));
                client.setComplementoEndereco(rs.getString("complementoEndereco"));
                client.setTelefone(rs.getString("telefone"));
                client.setCelular(rs.getString("celular"));
                client.setTelefoneEmergencia(rs.getString("telefoneEmergencia"));
                client.setAtivo(rs.getBoolean("ativo"));
                client.setCpf(rs.getString("cpf") != null ? rs.getString("cpf").replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4") : " ");
                client.setNumMatricula(rs.getString("numMatricula"));
                client.setDataNascimento(rs.getDate("dataNascimento"));

                Blob blob = rs.getBlob("profilePic");
                if(blob != null){
                    InputStream inputStream = blob.getBinaryStream();
                    Image image = new Image(inputStream);
                    client.setProfilePicImg(image);

                }
                clientsList.add(client);
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }


        try {
            ps = DBUtil.getConnection().createStatement();
            ResultSet rs = ps.executeQuery("SELECT COUNT(*) FROM `clientes`");
            while (rs.next()){
                clientsLength = rs.getInt("COUNT(*)");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        try {
            ps = DBUtil.getConnection().createStatement();
            ResultSet rs = ps.executeQuery("SELECT COUNT(*) FROM `clientes` WHERE ativo=1");
            while (rs.next()){
                clientsAtivosLength = rs.getInt("COUNT(*)");
            }
            ps.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }




    }

    public void removeClient(String numMatricula, String nome){
        String sql = "DELETE FROM `clientes` WHERE numMatricula=? AND nome=?";

        PreparedStatement ps = null;

        try {
            ps = DBUtil.getConnection().prepareStatement(sql);
            ps.setString(1, numMatricula);
            ps.setString(2, nome);

            ps.execute();
            ps.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getLastMatricula(){
        String sql = "SELECT max(numMatricula) FROM `clientes`";

        Statement ps = null;

        try {
            ps = DBUtil.getConnection().createStatement();

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()){
                return rs.getInt("max(numMatricula)");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<ClientModel> getClientsList(){
        return clientsList;
    }

    public int getClientsLength() {
        return clientsLength;
    }

    public int getClientsAtivosLength() {
        return clientsAtivosLength;
    }
}
