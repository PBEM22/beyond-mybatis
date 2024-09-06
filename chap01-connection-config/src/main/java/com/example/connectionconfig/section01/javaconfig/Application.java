package com.example.connectionconfig.section01.javaconfig;


import ch.qos.logback.classic.pattern.ClassNameOnlyAbbreviator;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Date;

public class Application {

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3307/menudb";
    private static String USER = "root";
    private static String PASSWORD = "1229";


    public static void main(String[] args) {

        /* DB 접속에 관한 설정 */
        // JdbcTransactionalFactory : 수동 커밋, ManagedTransactionFactory : 자동 커밋
        Environment environment = new Environment(
                "dev",  // 환경 정보 이름
                new JdbcTransactionFactory(),   // 트랜잭션 매니저 종류 (JDBC or MABAGES)
                new PooledDataSource(
                        DRIVER, URL, USER, PASSWORD     // Connection 생성 시 사용할 DB 연결 정보
                )          // Connection Pool 사용 유무(Pooled or UnPooled)
        );

        /* 생성한 환경 정보로 Mybatis 설정 객체 생성 */
        Configuration configuration = new Configuration(environment);

        /* 설정 객체에 매퍼 등록 */
        configuration.addMapper(Mapper.class);

        /* SqlSessionFactory : SqlSession 객체를 생성하기 위한 팩토리 역할의 인터페이스
        * SqlSessionFactoryBuilder */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        /* openSession() : SqlSession 타입의 인터페이스를 반환하는 메소드로 boolean 타입으로 인자 전달
        * - false : DML 수행 후 auto commit 옵션을 false로 지정 */
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        /* getMapper() : Configuration에 등록 된 매퍼를 동일 타입에 대해 반환 */
        Mapper mapper = sqlSession.getMapper(Mapper.class);

        /* Mapper 인터페이스에 작성 된 메소드 호출하여 select 쿼리 실행 후 반환 값 출력 */
        Date date = mapper.selectDate();
        System.out.println("date = " + date);

        /* SqlSession 객체 반납 */
        sqlSession.close();
    }
}
