package qp.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.StringReader;

import org.junit.jupiter.api.Test;
import qp.utils.SQLQuery;

class parserTest {
    @Test
    public void selectStarOnly() throws Exception {
        SQLQuery query = buildQuery("SELECT * FROM CUSTOMER");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectStarWhere() throws Exception {
        SQLQuery query = buildQuery("SELECT * FROM CUSTOMER WHERE CUSTOMER.gender = \"0\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(1, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectAttribute() throws Exception {
        SQLQuery query = buildQuery("SELECT CUSTOMER.age FROM CUSTOMER");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(1, query.getProjectList().size());
        assertEquals("CUSTOMER.age", query.getProjectList().get(0).toString());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectAttributeWhere() throws Exception {
        SQLQuery query = buildQuery("SELECT CUSTOMER.age FROM CUSTOMER WHERE CUSTOMER.cid = \"2\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(1, query.getSelectionList().size());
        assertEquals(1, query.getProjectList().size());
        assertEquals("CUSTOMER.age", query.getProjectList().get(0).toString());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectMoreAttributesWhere() throws Exception {
        SQLQuery query = buildQuery("SELECT CUSTOMER.age, CUSTOMER.gender FROM CUSTOMER WHERE CUSTOMER.cid = \"2\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(1, query.getSelectionList().size());
        assertEquals(2, query.getProjectList().size());
        assertEquals("CUSTOMER.age", query.getProjectList().get(0).toString());
        assertEquals("CUSTOMER.gender", query.getProjectList().get(1).toString());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectStarJoin() throws Exception {
        SQLQuery query = buildQuery("SELECT * FROM CUSTOMER, BILL");
        assertEquals(2, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals("BILL", query.getFromList().get(1));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectStarJoinWhere() throws Exception {
        SQLQuery query = buildQuery("SELECT * FROM CUSTOMER, BILL WHERE BILL.amount = \"100\"");
        assertEquals(2, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals("BILL", query.getFromList().get(1));
        assertEquals(1, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectDistinctStar() throws Exception {
        SQLQuery query = buildQuery("SELECT DISTINCT * FROM CUSTOMER");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertTrue(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectDistinctStarWhere() throws Exception {
        SQLQuery query = buildQuery("SELECT DISTINCT * FROM CUSTOMER WHERE CUSTOMER.cid = \"11\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(1, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertTrue(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectDistinctAttribute() throws Exception {
        SQLQuery query = buildQuery("SELECT DISTINCT CUSTOMER.age FROM CUSTOMER");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(1, query.getProjectList().size());
        assertEquals("CUSTOMER.age", query.getProjectList().get(0).toString());
        assertEquals(0, query.getNumJoin());
        assertTrue(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectDistinctAttributeWhere() throws Exception {
        SQLQuery query = buildQuery("SELECT DISTINCT CUSTOMER.age FROM CUSTOMER WHERE CUSTOMER.cid = \"2\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(1, query.getSelectionList().size());
        assertEquals(1, query.getProjectList().size());
        assertEquals("CUSTOMER.age", query.getProjectList().get(0).toString());
        assertEquals(0, query.getNumJoin());
        assertTrue(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectDistinctMoreAttributesWhere() throws Exception {
        SQLQuery query = buildQuery("SELECT DISTINCT CUSTOMER.age, CUSTOMER.gender FROM CUSTOMER WHERE CUSTOMER.cid = \"2\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(1, query.getSelectionList().size());
        assertEquals(2, query.getProjectList().size());
        assertEquals("CUSTOMER.age", query.getProjectList().get(0).toString());
        assertEquals("CUSTOMER.gender", query.getProjectList().get(1).toString());
        assertEquals(0, query.getNumJoin());
        assertTrue(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectDistinctStarJoin() throws Exception {
        SQLQuery query = buildQuery("SELECT DISTINCT * FROM CUSTOMER, BILL");
        assertEquals(2, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals("BILL", query.getFromList().get(1));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertTrue(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectDistinctStarJoinWhere() throws Exception {
        SQLQuery query = buildQuery("SELECT DISTINCT * FROM CUSTOMER, BILL WHERE BILL.amount = \"100\"");
        assertEquals(2, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals("BILL", query.getFromList().get(1));
        assertEquals(1, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertTrue(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(0,query.getOffset());
    }

    @Test
    public void selectStarLimit() throws Exception {
        SQLQuery query = buildQuery("SELECT * FROM CUSTOMER LIMIT \"8\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(8,query.getLimit());
        assertEquals(0,query.getOffset());
    }
    @Test
    public void selectStarOffset() throws Exception {
        SQLQuery query = buildQuery("SELECT * FROM CUSTOMER OFFSET \"8\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(-1,query.getLimit());
        assertEquals(8,query.getOffset());
    }
    @Test
    public void selectStarLimitOffset() throws Exception {
        SQLQuery query = buildQuery("SELECT * FROM CUSTOMER LIMIT \"8\" OFFSET \"3\"");
        assertEquals(1, query.getFromList().size());
        assertEquals("CUSTOMER", query.getFromList().get(0));
        assertEquals(0, query.getSelectionList().size());
        assertEquals(0, query.getProjectList().size());
        assertEquals(0, query.getNumJoin());
        assertFalse(query.isDistinct());
        assertEquals(0,query.getGroupByList().size());
        assertEquals(0,query.getOrderByList().size());
        assertEquals(8,query.getLimit());
        assertEquals(3,query.getOffset());
    }

    private SQLQuery buildQuery(String input) throws Exception {
        StringReader query = new StringReader(input);
        Scaner scanner = new Scaner(query);
        parser p = new parser(scanner);
        p.parse();
        return p.getSQLQuery();
    }
}