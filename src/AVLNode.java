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
    private String internalName;

    public AVLNode(String key){
        this.key = key;
        this.left = null;
        this.right = null;
        this.height = 0;
        internalName = "Vertex_" + hashCode();
    }


    public String getInternalName(){
        return internalName;
    }

    private void setInternalName(String internalName) {
        this. internalName = internalName;
    }

    //duplicate checking done via return < 0
    public int insert1(String newKey){
        if (this.getKey() == null) {
            this.key = newKey;
            return 0;
        }

        if ( getKey().compareTo(newKey) > 0 && left == null) {
            left = new AVLNode(newKey);
            left.setHeight(0);
            if (this.height == 0 )this.setHeight(1);
            return 1;
        }

        if ( getKey().compareTo(newKey) < 0 && right == null) {
            right = new AVLNode(newKey);
            right.setHeight(0);
            if (this.height == 0 ) this.setHeight(1);
            return 1;
        }

        // duplicate checking
        if ( getKey().equals(newKey) ) return -1;

        // recurse
        if ( getKey().compareTo(newKey) > 0 ) {
            int h = getLeft().insert(newKey);
            if (h > 0  ) {
                if (h + 1 > this.getHeight())
                this.setHeight(h + 1);
                return h + 1;
            }
            else return -1;
        }
        else {
            int h = getRight().insert(newKey);
            if (h > 0 ) {
                if (h + 1 > this.getHeight())
                this.setHeight(h + 1);
                return h + 1;
            }
            else return -1;
        }
    }//insert
    public int insert(String newKey){
        if (this.getKey() == null) {
            this.key = newKey;
            this.calcHeight();
            return 1;
        }

        if ( getKey().compareTo(newKey) > 0 && left == null) {
            left = new AVLNode(newKey);
            left.setHeight(0);
            this.calcHeight();
            return height;
        }

        if ( getKey().compareTo(newKey) < 0 && right == null) {
            right = new AVLNode(newKey);
            right.setHeight(0);
            this.calcHeight();
            return height;
        }

        // duplicate checking
        if ( getKey().equals(newKey) ) return -1;

        // recurse
        if ( getKey().compareTo(newKey) > 0 ) {
            if (getLeft().insert(newKey) > 0  ) {
                this.calcHeight();
                this.balance();
                return height;
            }
            else return -1;
        }
        else {
            if (getRight().insert(newKey) > 0 ) {
                this.calcHeight();
                this.balance();
                return height;
            }
            else return -1;
        }
    }//insert1

    public void balance(){
        int balance = this.leftSubHeight() - this.rightSubHeight();
        if (balance == -2){
            //right heavy. check which side
            //left
            if (this.getRight().leftSubHeight() < this.getRight().rightSubHeight() ){
                AVLNode oldRoot = new AVLNode(this.getKey());

                AVLNode pointer1;
                AVLNode pointer2;
                pointer1 = this.getLeft();
                pointer2 = this.getRight().getLeft();

                this.setKey(this.getRight().getKey());
                this.setLeft(oldRoot);
                this.setRight(this.getRight().getRight());
                oldRoot = null;

                //rehang p1 p2
                if(pointer1 != null) this.insertNode(pointer1);
                if(pointer2 != null) this.insertNode(pointer2);

                this.getLeft().calcHeight();
                this.getRight().calcHeight();
                this.calcHeight();

            }
            //right left. else if??
            else{
                AVLNode oldRoot = new AVLNode(this.getKey());
                oldRoot.setLeft(this.getLeft());

                AVLNode pointer1;
                AVLNode pointer2;
                pointer1 = this.getRight().getLeft().getLeft();
                pointer2 = this.getRight().getLeft().getRight();

                this.setKey(this.getRight().getLeft().getKey());
                this.setLeft(oldRoot);

                this.getRight().setLeft(null);

                if(pointer1 != null) this.insertNode(pointer1);
                if(pointer2 != null) this.insertNode(pointer2);

                this.getLeft().calcHeight();
                this.getRight().calcHeight();
                this.calcHeight();

            }
        }//right heavy
        else if (balance == 2){
            //left heavy. check
            //right
            if (this.getLeft().leftSubHeight() > this.getLeft().rightSubHeight() ){
                AVLNode oldRoot = new AVLNode(this.getKey());

                AVLNode pointer1;
                AVLNode pointer2;
                pointer1 = this.getRight();
                pointer2 = this.getLeft().getRight();

                this.setKey(this.getLeft().getKey());
                this.setRight(oldRoot);
                this.setLeft(this.getLeft().getLeft());

                if(pointer1 != null) this.insertNode(pointer1);
                if(pointer2 != null) this.insertNode(pointer2);

                this.getLeft().calcHeight();
                this.getRight().calcHeight();
                this.calcHeight();
            }
            //left right
            else{
                AVLNode oldRoot = new AVLNode(this.getKey());
                oldRoot.setRight(this.getRight());

                AVLNode pointer1;
                AVLNode pointer2;
                pointer1 = this.getLeft().getRight().getLeft();
                pointer2 = this.getLeft().getRight().getRight();

                this.setKey(this.getLeft().getRight().getKey());
                this.setRight(oldRoot);

                this.getLeft().setRight(null);

                if(pointer1 != null) this.insertNode(pointer1);
                if(pointer2 != null) this.insertNode(pointer2);

                this.getLeft().calcHeight();
                this.getRight().calcHeight();
                this.calcHeight();
            }
        }//left heavy
    }

    private void insertNode(AVLNode current){
        if (current.getKey().compareTo(this.getKey()) < 0){
            if ( this.getLeft() == null ){this.setLeft(current);return;}
            else this.getLeft().insertNode(current);
        }
        else {
            if (this.getRight() == null){ this.setRight(current);return;}
            else this.getRight().insertNode(current);
        }
    }

    public int leftSubHeight(){
        if ( this.getLeft() == null) return -1;
        return this.getLeft().getHeight();
    }
    public int rightSubHeight(){
        if ( this.getRight() == null) return -1;
        return this.getRight().getHeight();
    }

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
        if (this.key == null) return null;
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
            this.height = temp.getHeight();
        }
        else{
            this.key = null;
            this.left = null;
            this.right = null;
        }
        this.calcHeight();
        return 1;
    }//end remove

    //removeHelper recurses through tree. uses in-order succesor
    private AVLNode removeHelper(AVLNode current, String item){
        if (current == null) return null;
        //finding where item is
        if ( item.compareTo(current.getKey()) < 0 ){
            current.setLeft(removeHelper(current.getLeft(), item ));
            current.calcHeight();
            current.balance();
            return current;
        }
        else if ( item.compareTo(current.getKey()) > 0){
            current.setRight( removeHelper(current.getRight(), item ) );
            current.calcHeight();
            current.balance();
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
                current.setInternalName(succ.getInternalName());
                current.setRight( removeHelper(current.getRight(), succ.getKey()) );
                current.calcHeight();
                current.balance();
                return current;
            }
        }
    }//end removeHelper

    private void calcHeight(){
        if (this.getLeft() == null && this.getRight() == null) this.setHeight(0);
        if (this.getLeft() != null && this.getRight() == null) this.setHeight(1 +  this.getLeft().getHeight());
        if (this.getLeft() == null && this.getRight() != null) this.setHeight(1 + this.getRight().getHeight());
        if (this.getLeft() != null && this.getRight() != null)
            this.setHeight(1 + Math.max(this.getLeft().getHeight(),this.getRight().getHeight()));
    }

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
    public int getHeight(){
        return this.height;
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
        if (this.getKey() == null) return "digraph {\n}";
        String result = "digraph {\n" + "\t" + getInternalName() + "[penwidth=3];\n" + toDotFileHelperLabel(new String(""));
        result += toDotFileHelperEdge(new String(""));
        return result + "}\n";
    }

    public String toDotFileHelperLabel(String s){
        if (left == null && right == null) return s + "\t" + getInternalName() + "[label=\"" + getKey() + " h=" + this.height +"\"];\n";

        s+= "\t" + getInternalName() + "[label=\"" + getKey() + " h=" + this.height +"\"];\n";
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
            s += "\t" + getInternalName() + " -> " + getLeft().getInternalName() + "[label=left]" + "\n";
            s = getLeft().toDotFileHelperEdge(s);
        }
        if (right != null) {
            s += "\t" + getInternalName() + " -> " + getRight().getInternalName()+ "[label=right]" + "\n";
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

//                if(pointer1 != null) {
//                    if (pointer1.getKey().compareTo(this.getKey()) < 0) {
//                        if (pointer1.getKey().compareTo(this.getLeft().getKey()) < 0)
//                            this.getLeft().setLeft(pointer1);
//                        else
//                            this.getLeft().setRight(pointer1);
//                    }
//                    else{
//                        if (pointer1.getKey().compareTo(this.getRight().getKey()) < 0)
//                            this.getRight().setLeft(pointer1);
//                        else
//                            this.getRight().setRight(pointer1);
//                    }
//                }
//rehang p2
//                if(pointer2 != null) {
//                    if (pointer2.getKey().compareTo(this.getKey()) < 0) {
//                        if (pointer2.getKey().compareTo(this.getLeft().getKey()) < 0)
//                            this.getLeft().setLeft(pointer2);
//                        else
//                            this.getLeft().setRight(pointer2);
//                    }
//                    else{
//                        if (pointer2.getKey().compareTo(this.getRight().getKey()) < 0)
//                            this.getRight().setLeft(pointer2);
//                        else
//                            this.getRight().setRight(pointer2);
//                    }
//                }




