package com.excilys.persistence.dao;


public class CompanyDAOTest {
//    @BeforeClass
//    public static void setUpDB() {
//        try {
//            DBUtil.executeSqlFile("test.sql", DBUtil.getConnection());
//        } catch (IOException | SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        DBUtil.databaseTester.onTearDown();
//    }
//
//    @Test
//    public void getAllWithSuccess() throws Exception {
//        // GIVEN
//        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//                "src/test/resources/datasets/companyDAO/getAll.xml")));
//        final int expectedSize = 2;
//        final Company expectedCompany1 = new Company(1L, "IBM");
//        final Company expectedCompany2 = new Company(2L, "Dell");
//
//        // WHEN
//        final List<Company> companies = CompanyDAO.INSTANCE.getAll();
//
//        // THEN
//        Assertions.assertThat(companies).isNotNull();
//        Assertions.assertThat(companies.size()).isEqualTo(expectedSize);
//        Assertions.assertThat(companies).contains(expectedCompany1, expectedCompany2);
//    }
//
//    @Test
//    public void getAllWhenNoEntityGiveEmptyList() throws Exception {
//        // GIVEN
//        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//                "src/test/resources/datasets/companyDAO/getAllNoEntity.xml")));
//
//        // WHEN
//        final List<Company> companies = CompanyDAO.INSTANCE.getAll();
//
//        // THEN
//        Assertions.assertThat(companies).isNotNull();
//        Assertions.assertThat(companies).isEmpty();
//    }
//
//    @Test
//    public void getByIdWithSuccess() throws Exception {
//        // GIVEN
//        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//                "src/test/resources/datasets/companyDAO/getById.xml")));
//        final long id = 2L;
//        final long expectedId = 2L;
//        final String expectedName = "Dell";
//
//        // WHEN
//        final Company company = CompanyDAO.INSTANCE.getById(id);
//
//        // THEN
//        Assertions.assertThat(company).isNotNull();
//        Assertions.assertThat(company.getId()).isEqualTo(expectedId);
//        Assertions.assertThat(company.getName()).isEqualTo(expectedName);
//    }
//
//    @Test
//    public void getByIdWhenNotFoundGivesANullResult() throws Exception {
//        // GIVEN
//        DBUtil.cleanlyInsert(new FlatXmlDataSetBuilder().build(new File(
//                "src/test/resources/datasets/companyDAO/getById.xml")));
//        final long id = 45456L;
//
//        // WHEN
//        final Company company = CompanyDAO.INSTANCE.getById(id);
//
//        // THEN
//        Assertions.assertThat(company).isNull();
//    }
}
