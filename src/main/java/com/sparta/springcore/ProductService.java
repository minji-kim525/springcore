package com.sparta.springcore;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        ProductRepository productRepository=new ProductRepository();
        this.productRepository=productRepository;
    }

    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);


        productRepository.createProduct(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        int myprice = requestDto.getMyprice();
        productRepository.updateMyprice(id, myprice);

        return product;
    }

    public List<Product> getProducts() throws SQLException {
        List<Product> products = productRepository.getProducts();

        return products;
    }
}