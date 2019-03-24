import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {


    @Test
    public void testSizeOf() {
        UnionFind disjointSet = new UnionFind(16);
        disjointSet.union(0, 1);
        disjointSet.union(1, 2);
        disjointSet.union(3, 4);
        disjointSet.union(3, 5);
        assertEquals(3, disjointSet.sizeOf(0));
        assertEquals(3, disjointSet.sizeOf(5));
        disjointSet.union(1, 4);
        assertEquals(6, disjointSet.sizeOf(5));
    }

    @Test
    public void testConnected() {
        UnionFind disjointSet = new UnionFind(16);
        disjointSet.union(0, 1);
        disjointSet.union(1, 2);
        disjointSet.union(3, 4);
        disjointSet.union(3, 5);
        disjointSet.union(1, 4);
        assertTrue(disjointSet.connected(1, 4));
        assertFalse(disjointSet.connected(2, 6));
    }

    @Test
    public void testFind() {
        UnionFind disjointSet = new UnionFind(16);
        disjointSet.union(0, 1);
        disjointSet.union(1, 2);
        disjointSet.union(3, 4);
        disjointSet.union(3, 5);
        disjointSet.union(1, 4);
        assertEquals(4, disjointSet.find(4));
        assertEquals(4, disjointSet.find(3));
        assertEquals(6, disjointSet.find(6));

        assertEquals(1, disjointSet.parent(2));
        int n = disjointSet.find(2);
        assertEquals(4, disjointSet.parent(2));
    }


}
