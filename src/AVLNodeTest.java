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
        assertEquals("d",root.find("d"));


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

        assertEquals("e",root.find("e"));
        assertEquals(null,root.find("z"));
        root.inOrderTraversal();
        root.preOrderTraversal();



        AVLNode root0 = new AVLNode("g");


        assertEquals(1, root0.insert("e"));


        assertEquals(1, root0.insert("h"));
        assertEquals("e", root0.findMin());
        assertEquals(2, root0.insert("a"));
        assertEquals(2, root0.insert("s"));
        root0.inOrderTraversal();
        root0.preOrderTraversal();
        assertEquals("a", root0.findMin());




    }
    @Test
    public void removeTests(){
        AVLNode root = new AVLNode("e");
        root.inOrderTraversal();
        assertEquals(-1,root.remove("a"));
        root.inOrderTraversal();
        assertEquals(-1,root.remove("b"));
        root.inOrderTraversal();
        assertEquals(-1,root.insert("e"));
        root.inOrderTraversal();
    }
    @Test
    public void removeTests0(){
        AVLNode root = new AVLNode("d");

        System.out.println(root.toDotFile(true));
        root.preOrder();
        assertEquals(1,root.insert("b"));
        System.out.println(root.toDotFile(true));
        root.preOrder();
        assertEquals(1,root.insert("f"));
        root.preOrder();
        assertEquals(2,root.insert("a"));
        assertEquals(2,root.insert("c"));
        assertEquals(2,root.insert("e"));
        assertEquals(2,root.insert("g"));

        System.out.println(root.toDotFile(true));

        root.preOrder();
        root.remove("d");
        root.preOrder();
        root.remove("e");
        root.preOrder();
        root.remove("a");
        root.preOrder();





    }
}