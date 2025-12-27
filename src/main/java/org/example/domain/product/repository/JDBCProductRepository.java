package org.example.domain.product.repository;

import org.example.domain.product.vo.ProductVO;
import org.example.global.config.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCProductRepository implements ProductRepository {
    @Override
    public List<ProductVO> findAll() {
        List<ProductVO> products = new ArrayList<>();
        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement("select id, name, price from product");
                ResultSet rs = ps.executeQuery()
        ) {
            while(rs.next()){
                ProductVO product = new ProductVO(rs.getLong("id"), rs.getString("name"), rs.getInt("price"));
                products.add(product);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public ProductVO findById(Long id) {
        String sql = "select * from product where id = ?";
        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);

        ) {
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(!rs.next())
                    throw new SQLException("조회할 컬럼이 없지롱 ㅠㅠ");
                return new ProductVO(rs.getLong("id"), rs.getString("name"), rs.getInt("price"));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductVO save(ProductVO product) {
        String sql = "insert into product (name, price) values (?, ?)";
        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if(!rs.next())
                    throw new SQLException("생성에 실패했지롱");
                Long id = rs.getLong(1);
                return new ProductVO(id, product.getName(), product.getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductVO update(ProductVO product) {
        String sql = "update product set name = ?, price = ? where id = ?";
        try(
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setLong(3, product.getId());

            if(ps.executeUpdate() == 0)
                throw new SQLException("수정된 대상이 존재하지 않지롱 ㅠㅠ");

            return product;
        } catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try(
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setLong(1, id);
            if(ps.executeUpdate() == 0)
                throw new SQLException("삭제된 컬럼이 없지롱 ㅠㅠ");

        } catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
