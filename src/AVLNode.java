/**
 * Dustin Janzen
 * sp16 345
 * project 3
 */

public class AVLNode{
    private String key;
    private AVLNode left;
    private AVLNode right;
    private int height;

    public AVLNode(String key){
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    //duplicate checking done via return < 0
    public int insert(String newKey){
        if (this.getKey() == null) {
            this.key = newKey;
            return 0;
        }

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
        if (this.key == null) {
            System.out.println("0 elements:");
            return;
        }

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
        if (this.key == null || this == null) {
            System.out.println("0 elements:");
            return;
        }

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

    public AVLNode findMin(){
        if (this.key == null) return null;
        return findMinHelper(this);
    }

    private AVLNode findMinHelper(AVLNode current){
        if (current.getLeft() == null) return current;
        return findMinHelper(current.getLeft());
    }

    public int remove(String Data){
        if (this.find(Data) == null) return -1;
        AVLNode temp = removeHelper(this, Data);
        if (temp != null) {
            this.key = temp.getKey();
            this.left = temp.getLeft();
            this.right = temp.getRight();
        }
        else{
            this.key = null;
            this.left = null;
            this.right = null;
        }
        return 1;
    }//end remove

    //removeHelper recurses through tree. uses in-order succesor
    private AVLNode removeHelper(AVLNode current, String item){
        if (current == null) return null;
        //finding where item is
        if ( item.compareTo(current.getKey()) < 0 ){
            current.setLeft( removeHelper(current.getLeft(), item ) );
            return current;
        }
        else if ( item.compareTo(current.getKey()) > 0){
            current.setRight( removeHelper(current.getRight(), item ) );
            return current;
        }
        // found item
        else {
            //has no children
            if (current.getLeft() == null && current.getRight() == null){
                return null;
            }
            //has right child
            else if (current.getLeft() == null){
                return current.getRight();
            }
            //has left child
            else if (current.getRight() == null)
                return current.getLeft();
                //has two children
                //uses in-order successor: call findMinHelper(current.getRight())
            else {
                AVLNode succ = findMinHelper(current.getRight());
                current.setKey(succ.getKey());
                current.setRight( removeHelper(current.getRight(), succ.getKey()) );
                return current;
            }
        }
    }//end removeHelper

    public void preOrder(){
        if (this.key == null) {
            System.out.println("0 preOrder:");
            return;
        }

        System.out.print(getCount() +" preOrder: ");
        preOrderHelper(this);
        System.out.println();
    }

    private void preOrderHelper(AVLNode current){
        if (current == null) return;
        System.out.print(current.key + " " + current.height + " ");
        preOrderHelper(current.getLeft());
        preOrderHelper(current.getRight());
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
    //toDotFile is generating a digraph. Boolean parameter per spec but unused. Using two helpers for vertices and edges
    public String toDotFile(Boolean b){
        if (this.getKey() == null) return "";
        String result = "digraph {\n" + "\t" + this.key + "[penwidth=3];\n" + toDotFileHelperLabel(new String(""));
        result += toDotFileHelperEdge(new String(""));
        return result + "}\n";
    }

    public String toDotFileHelperLabel(String s){
        if (left == null && right == null) return s + "\t" + this.key + "[label=" + getKey() +"];\n";

            s+= "\t" + this.key + "[label=" + getKey() +"];\n";
        if (left != null) {
            s = getLeft().toDotFileHelperLabel(s);
        }
        if (right != null) {
            s = getRight().toDotFileHelperLabel(s);
        }

        return s;

    }
    public String toDotFileHelperEdge(String s){
        if (left == null && right == null) return s;

        if (left != null) {
            s += "\t" + this.key + " -> " + getLeft().getKey() + "[label=left]" + "\n";
            s = getLeft().toDotFileHelperEdge(s);
        }
        if (right != null) {
            s += "\t" + this.key + " -> " + getRight().getKey()+ "[label=right]" + "\n";
            s = getRight().toDotFileHelperEdge(s);
        }

        return s;

    }
}//end AVLNode

//    //toArray does in-order traversal via a helper method. Setup using arrayList<>
//    public String[] toArray(){
//        ArrayList<String> result = toArrayHelper(new ArrayList<String>());
//        return result.toArray( new String[result.size()] );
//    }
//
//    private ArrayList<String> toArrayHelper(ArrayList<String> list){
//        //base
//        if (left == null && right == null){
//            list.add(getKey());
//            return list;
//        }
//        //recurse
//        if (left != null)
//            getLeft().toArrayHelper(list);
//
//        list.add(getKey());
//
//        if (right != null)
//            getRight().toArrayHelper(list);
//
//        return list;
//    }
//    //toArray_preOrder does, shockingly, a pre-order traversal via a helper method
//    public String [] toArray_preOrder(){
//        ArrayList<String> result = toArray_preOrderHelper(new ArrayList<String>());
//        return result.toArray( new String[result.size()] );
//    }
//
//    private ArrayList<String> toArray_preOrderHelper(ArrayList<String> list){
//        //base
//        if (left == null && right == null){
//            list.add(getKey());
//            return list;
//        }
//        //recurse
//        list.add(getKey());
//
//        if (left != null)
//            getLeft().toArray_preOrderHelper(list);
//
//        if (right != null)
//            getRight().toArray_preOrderHelper(list);
//
//        return list;
//    }




