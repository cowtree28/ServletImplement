package org.example.domain.product.servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.product.repository.JDBCProductRepository;
import org.example.domain.product.servlet.dto.ProductCreateRequest;
import org.example.domain.product.servlet.dto.ProductDeleteRequest;
import org.example.domain.product.servlet.dto.ProductUpdateRequest;
import org.example.domain.product.vo.ProductVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductServlet extends HttpServlet {
    JDBCProductRepository repository = new JDBCProductRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        ObjectMapper mapper = new ObjectMapper();

        try(PrintWriter out = resp.getWriter()){
            List<ProductVO> products = repository.findAll();
            out.println(mapper.writeValueAsString(products));

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        req.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        try(
                PrintWriter out = resp.getWriter();
                BufferedReader reader = req.getReader();
        ) {
            ProductCreateRequest productCreateRequest = new ObjectMapper().readValue(reader, new TypeReference<ProductCreateRequest>() {});
            ProductVO product = repository.save(new ProductVO(null, productCreateRequest.getName(), productCreateRequest.getPrice()));
            out.println(mapper.writeValueAsString(product));

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        req.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();

        try(
            PrintWriter out = resp.getWriter();
            BufferedReader reader = req.getReader();
        ) {
            ProductUpdateRequest productUpdateRequest = new ObjectMapper().readValue(reader, new TypeReference<ProductUpdateRequest>() {});
            ProductVO product = new ProductVO(productUpdateRequest.getId(), productUpdateRequest.getName(), productUpdateRequest.getPrice());
            repository.update(product);
            out.println(mapper.writeValueAsString(product));
        } catch (Exception e){
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        req.setCharacterEncoding("UTF-8");

        try(
            PrintWriter out = resp.getWriter();
            BufferedReader reader = req.getReader();
        ) {
            ProductDeleteRequest productDeleteRequest = new ObjectMapper().readValue(reader, new TypeReference<ProductDeleteRequest>() {});
            repository.delete(productDeleteRequest.getId());
        } catch (Exception e){
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
