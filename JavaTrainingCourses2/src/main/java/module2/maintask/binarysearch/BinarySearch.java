package module2.maintask.binarysearch;

public class BinarySearch {
    public BinarySearch(){}
    Node root;
    public void insert(int key){
        root = insertRecursively(root,key);
    }

    public void delete(int deleteIt){
        root = deleteNode(root, deleteIt);
    }

    private Node deleteNode(Node current, int deleteIt){
        if(current == null)
            return null;

        if (deleteIt < current.data){
            current.leftChild = deleteNode(current.leftChild, deleteIt);
        }else if(deleteIt > current.data){
            current.rightChild = deleteNode(current.rightChild, deleteIt);
        }else {
            if(current.leftChild == null)
                return current.rightChild;
            if (current.rightChild == null)
                return current.leftChild;

            current.data = rightMin(current.rightChild);
            current.rightChild = deleteNode(current.rightChild, current.data);
        }
        return current;
    }

    private int rightMin(Node rightChild) {
        int lowerRightVal = rightChild.data;
        while (rightChild.leftChild!=null){
            lowerRightVal = rightChild.leftChild.data;
            rightChild = rightChild.leftChild;
        }

        return lowerRightVal;
    }

    private  Node insertRecursively(Node root, int key){
        if (root == null)
            root = new Node(key);

        if (key < root.data)
            root.leftChild = insertRecursively(root.leftChild, key);
        else if (key > root.data)
            root.rightChild = insertRecursively(root.rightChild, key);

        return root;
    }

    public boolean search(int key){
        root = searchRecursively(root,key);
        if (root==null)
            return false;
        else
            return true;
    }

    private Node searchRecursively(Node root, int key){
        if (root == null || root.data == key)
            return root;

        if(key > root.data)
            return  searchRecursively(root.rightChild,key);
        return searchRecursively(root.leftChild, key);
    }

    public void inOrderTraverse(){
        recursivelyInOrderTraverse(root);
    }
    private void recursivelyInOrderTraverse(Node root){
        if (root == null){
            return;
        }
        recursivelyInOrderTraverse(root.leftChild);
        System.out.println(root.data);
        recursivelyInOrderTraverse(root.rightChild);
    }

    public void postOrderTraverse() {
        recursiblyPostOrderTraversal(root);
    }
    private void recursiblyPostOrderTraversal(Node current){
        if (current == null){
            return;
        }

        recursivelyInOrderTraverse(current.leftChild);
        recursivelyInOrderTraverse(current.rightChild);
        System.out.println(current.data);
    }
    public void preOrderTraverse() {
        recursiblyPreOrderTraversal(root);
    }
    private void recursiblyPreOrderTraversal(Node current){
        if (current == null){
            return;
        }

        System.out.println(current.data);
        recursivelyInOrderTraverse(current.leftChild);
        recursivelyInOrderTraverse(current.rightChild);
    }
}

