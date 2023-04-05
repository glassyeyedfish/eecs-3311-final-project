package unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.objects.Modifier;
import project.objects.RestockTask;
import project.persistence.ModifierDatabase;
import project.persistence.ModifierDatabaseStub;
import project.persistence.RestockTaskDatabase;
import project.persistence.RestockTaskDatabaseStub;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RestockTaskDatabaseStubTest {
    private RestockTaskDatabase db;


    @BeforeEach
    public void initialize(){
        db = new RestockTaskDatabaseStub();
    }

    @Test
    void getTest(){
        RestockTask r = this.db.getRestockTask("oreo");
        assertEquals("oreo", r.getName());
        assertEquals(5, r.getMinQuantity());
        assertEquals(20, r.getRestockAmount());
    }

    @Test
    void getNonexistentTask(){
        RestockTask r = this.db.getRestockTask("not");
        assertNull(r);
    }

    @Test
    void addGetTest(){
        this.db.addRestockTask(new RestockTask("muffin", 7, 8));
        this.db.addRestockTask(new RestockTask("soup", 3, 9));
        RestockTask r = this.db.getRestockTask("muffin");
        assertEquals("muffin", r.getName());
        assertEquals(7, r.getMinQuantity());
        assertEquals(8, r.getRestockAmount());
    }

    @Test
    void getTaskListTest(){
        ArrayList<RestockTask> rs = (ArrayList<RestockTask>) this.db.getRestockTaskList();
        ArrayList<Integer> quants = new ArrayList<>();
        for(RestockTask r : rs){
            quants.add(r.getMinQuantity());
        }
        assertTrue(quants.contains(5));
        assertTrue(quants.contains(3));
    }

    @Test
    void getEmptyListTest(){
        db.empty();
        ArrayList<RestockTask> rs = (ArrayList<RestockTask>) this.db.getRestockTaskList();
        assertEquals(0, rs.size());
    }


    @Test
    void addTest(){
        this.db.addRestockTask(new RestockTask("muffin", 7, 8));
        this.db.addRestockTask(new RestockTask("soup", 3, 9));
        this.db.addRestockTask(new RestockTask("cantaloupe", 8, 10));
        this.db.addRestockTask(new RestockTask("milk", 4, 11));
        this.db.addRestockTask(new RestockTask("banana", 9, 12));

        ArrayList<RestockTask> rs = (ArrayList<RestockTask>) this.db.getRestockTaskList();
        assertEquals(7, rs.size());
        assertEquals("muffin", db.getRestockTask("muffin").getName());
        assertEquals(3, db.getRestockTask("soup").getMinQuantity());
        assertEquals(8, db.getRestockTask("cantaloupe").getMinQuantity());
        assertEquals(11, db.getRestockTask("milk").getRestockAmount());
        assertEquals(12, db.getRestockTask("banana").getRestockAmount());
    }

    @Test
    void addNull(){
        db.addRestockTask(null);
        ArrayList<RestockTask> rs = (ArrayList<RestockTask>) this.db.getRestockTaskList();
        assertEquals(3, rs.size());
    }

    @Test
    void removeTest(){
        db.removeRestockTask("oreo");
        ArrayList<RestockTask> rs = (ArrayList<RestockTask>) this.db.getRestockTaskList();
        ArrayList<Integer> quants = new ArrayList<>();
        for(RestockTask r : rs){
            quants.add(r.getMinQuantity());
        }
        assertFalse(quants.contains(5));

    }

    @Test
    void reomveNonexistentModTest(){
        db.removeRestockTask("no");
        ArrayList<RestockTask> rs = (ArrayList<RestockTask>) this.db.getRestockTaskList();
        assertEquals(2, rs.size());
    }

    @Test
    void replaceMinQuantTest(){
        RestockTask r = new RestockTask("oreo", 7, 20);
        db.replaceRestockTask(r);
        assertEquals("oreo", db.getRestockTask("oreo").getName());
        assertEquals(7, db.getRestockTask("oreo").getMinQuantity());
        assertEquals(20, db.getRestockTask("oreo").getRestockAmount());
    }

    @Test
    void replaceAmountTest(){
        RestockTask r = new RestockTask("oreo", 3, 8);
        db.replaceRestockTask(r);
        assertEquals("oreo", db.getRestockTask("oreo").getName());
        assertEquals(3, db.getRestockTask("oreo").getMinQuantity());
        assertEquals(8, db.getRestockTask("oreo").getRestockAmount());
    }
}
