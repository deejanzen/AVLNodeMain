import java.util.ArrayList;

public class AVLNode{
    private String key;
    private AVLNode left;
    private AVLNode right;
    private int height;

    public AVLNode(String key){
        this.key = key;
        left = null;
        right = null;
        height = 0;
    }

    //duplicate checking done via return < 0
    public int insert(String newKey){
        //base
        if ( getKey().compareTo(newKey) > 0 && left == null) {
            left = new AVLNode(newKey);
            left.setHeight(0);
            this.setHeight(1);
            return 1;
        }

        if ( getKey().compareTo(newKey) < 0 && right == null) {
            right = new AVLNode(newKey);
            right.setHeight(0);
            this.setHeight(1);
            return 1;
        }

        // duplicate checking
        if ( getKey().equals(newKey) ) return -1;

        // recurse
        if ( getKey().compareTo(newKey) > 0 ) {
            int h = getLeft().insert(newKey);
            if (h > 0) {
                this.setHeight(h + 1);
                return h + 1;
            }
            else return -1;
        }
        else {
            int h = getRight().insert(newKey);
            if (h > 0) {
                this.setHeight(h + 1);
                return h + 1;
            }
            else return -1;
        }
    }//insert

    public void inOrderTraversal(){
        if (this.key == null) System.out.println("empty");
        System.out.print(getCount() +" elements: ");
        inOrderTraversalHelper(this);
        System.out.println();
    }
    private void inOrderTraversalHelper(AVLNode current){
        if (current == null) return;

        inOrderTraversalHelper(current.getLeft());
        System.out.print(current.getKey() + " ");
        inOrderTraversalHelper(current.getRight());
    }

    public void preOrderTraversal(){
        if (this.key == null) System.out.println("empty");
        System.out.print(getCount() +" elements: ");
        preOrderTraversalHelper(this);
        System.out.println();
    }

    private void preOrderTraversalHelper(AVLNode current){
        if (current == null) return;
        System.out.print(current.getKey() + " ");
        preOrderTraversalHelper(current.getLeft());
        preOrderTraversalHelper(current.getRight());
    }

    public int getCount(){
        return getCountHelper(this);
    }
    private int getCountHelper(AVLNode current){
        if (current == null) return 0;
        return 1 + getCountHelper(current.getLeft()) + getCountHelper(current.getRight());
    }

    public String find(String toFind){
        return findHelper(this, toFind);
    }
    private String findHelper(AVLNode current, String toFind){
        if (current == null) return null;
        if (current.getKey().equals(toFind)) return current.getKey();
        if (current.getKey().compareTo(toFind) > 0)
            return findHelper(current.getLeft(), toFind);
        else
            return findHelper(current.getRight(), toFind);

    }

    public void setHeight(int height){
        this.height = height;
    }
    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public AVLNode getLeft(){
        return left;
    }

    public void setLeft(AVLNode left){
        this.left = left;
    }

    public AVLNode getRight(){
        return right;
    }

    public void setRight(AVLNode right){
        this.right = right;
    }

    public int getHeight(){
        //base
        if (left == null && right == null ) return 0;
        //recurses
        if (left != null && right == null)
            return 1 + getLeft().getHeight();
        if (left == null && right != null)
            return 1 + getRight().getHeight();
            return Math.max(1 + getLeft().getHeight(), 1 + getRight().getHeight());
    }



    //toArray does in-order traversal via a helper method. Setup using arrayList<>
    public String[] toArray(){
        ArrayList<String> result = toArrayHelper(new ArrayList<String>());
        return result.toArray( new String[result.size()] );
    }

    private ArrayList<String> toArrayHelper(ArrayList<String> list){
        //base
        if (left == null && right == null){
            list.add(getKey());
            return list;
        }
        //recurse
        if (left != null)
            getLeft().toArrayHelper(list);

        list.add(getKey());

        if (right != null)
            getRight().toArrayHelper(list);

        return list;
    }
    //toArray_preOrder does, shockingly, a pre-order traversal via a helper method
    public String [] toArray_preOrder(){
        ArrayList<String> result = toArray_preOrderHelper(new ArrayList<String>());
        return result.toArray( new String[result.size()] );
    }

    private ArrayList<String> toArray_preOrderHelper(ArrayList<String> list){
        //base
        if (left == null && right == null){
            list.add(getKey());
            return list;
        }
        //recurse
        list.add(getKey());

        if (left != null)
            getLeft().toArray_preOrderHelper(list);

        if (right != null)
            getRight().toArray_preOrderHelper(list);

        return list;
    }
//    //toDotFile is generating a digraph. Boolean parameter per spec but unused. Using two helpers for vertices and edges
//    public String toDotFile(Boolean b){
//        String result = "digraph {\n" + "\t" + getInternalName() + "[penwidth=3];\n" + toDotFileHelperLabel(new String(""));
//        result += toDotFileHelperEdge(new String(""));
//        return result + "}\n";
//    }
//
//    public String toDotFileHelperLabel(String s){
//        if (left == null && right == null) return s + "\t" + getInternalName() + "[label=" + getKey() +"];\n";
//
//            s+= "\t" + getInternalName() + "[label=" + getKey() +"];\n";
//        if (left != null) {
//            s = getLeft().toDotFileHelperLabel(s);
//        }
//        if (right != null) {
//            s = getRight().toDotFileHelperLabel(s);
//        }
//
//        return s;
//
//    }
//    public String toDotFileHelperEdge(String s){
//        if (left == null && right == null) return s;
//
//        if (left != null) {
//            s += "\t" + getInternalName() + " -> " + getLeft().getInternalName()+ "[label=left]" + "\n";
//            s = getLeft().toDotFileHelperEdge(s);
//        }
//        if (right != null) {
//            s += "\t" +getInternalName() + " -> " + getRight().getInternalName()+ "[label=right]" + "\n";
//            s = getRight().toDotFileHelperEdge(s);
//        }
//
//        return s;
//
//    }
}//end AVLNode

