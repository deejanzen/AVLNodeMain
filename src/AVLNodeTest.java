import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by djanzen on 3/8/16.
 */
public class AVLNodeTest {
    @Test
    public void avlTest(){
        AVLNode root = new AVLNode("d");//1
        assertEquals(1,root.getCount());

        assertEquals(-1,root.insert("d"));//still 1
        assertEquals(1,root.getCount());

        assertEquals(1, root.insert("b"));//2
        assertEquals(2, root.getCount());


        assertEquals(2, root.insert("c"));//3
        assertEquals(3, root.getCount());



        assertEquals(1, root.insert("e"));
        root.inOrderTraversal();
        root.preOrderTraversal();
        assertEquals(2, root.insert("a"));
        assertEquals(5, root.getCount());
        assertEquals(-1, root.insert("a"));
        assertEquals(5, root.getCount());
        root.inOrderTraversal();
        root.preOrderTraversal();



        AVLNode root0 = new AVLNode("e");


        assertEquals(1, root0.insert("d"));


        assertEquals(2, root0.insert("c"));
        assertEquals(3, root0.insert("b"));
        assertEquals(4, root0.insert("a"));
        root0.inOrderTraversal();
        root0.preOrderTraversal();




    }
}