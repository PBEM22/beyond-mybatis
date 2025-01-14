package com.example.springmybatis.section01.factorybean;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final SqlSessionTemplate sqlSession;

    public MenuService(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<MenuDTO> findAllMenuByOrderableStatus(String orderableStatus) {
        return sqlSession.getMapper(MenuMapper.class).findAllMenuByOrderableStatus(orderableStatus);
    }
}