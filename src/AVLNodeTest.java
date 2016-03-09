import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by djanzen on 3/8/16.
 */
public class AVLNodeTest {
    @Test
    public void avlTest(){
        AVLNode root = new AVLNode("d");
        assertEquals(-1,root.insert("d"));
        assertEquals(1, root.insert("b"));
        assertEquals(2, root.insert("c"));
        assertEquals(1, root.insert("e"));
        assertEquals(2, root.insert("a"));
        assertEquals(-1, root.insert("a"));

        AVLNode root0 = new AVLNode("e");
        assertEquals(1, root0.insert("d"));
        assertEquals(2, root0.insert("c"));
        assertEquals(3, root0.insert("b"));
        assertEquals(4, root0.insert("a"));


    }
}