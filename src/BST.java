/**
 * A minimal implementation of a binary search tree storing Integers.
 * See the Python version for additional documentation; we haven't copied over any docstrings here.
 * You can also see
 * <a href="https://www.teach.cs.toronto.edu/~csc148h/notes/binary-search-trees/bst_implementation.html">CSC148 Course Notes Section 8.5 BST Implementation and Search</a>
 * if you want a refresher on BSTs, but it is not required to complete this assignment.
 */
public class BST {
    private Integer root;

    private BST left;
    private BST right;

    public BST(Integer root) {
        if (root != null) { // check to ensure we don't accidentally try to store null at the root!
            this.root = root;
            left = new BST();
            right = new BST();
        }
        // Note: each of the attributes will default to null if root == null
    }

    /**
     * Alternate constructor, so we don't have to explicitly pass in null.
     */
    public BST() {
        this(null);
    }


    // TODO Task: Implement the BST methods.

    public boolean isEmpty() {
        return (root == null);
    }

    public boolean contains(Integer item) {
        // provided code; do not change
        if (this.isEmpty()) {
            return false;
        } else if (item.equals(this.root)) { // see comment below
            // In general, we need to use .equals and not == to properly compare values.
            // Since we are storing integers, one _could_ implement this class such that
            // using == would be fine instead, but that has not been done here since we
            // are using Integer objects.
            return true;
        } else if (item.compareTo(this.root) < 0) {
            return this.left.contains(item);
        }
        return this.right.contains(item);

    }


    public void insert(Integer item) {
        if (this.isEmpty()) {
            this.root = item;
            this.left = new BST();
            this.right = new BST();
        }
        else if (item.compareTo(this.root) <= 0) {
            this.left.insert(item);
        }
        else {
            this.right.insert(item);
        }
    }

    private void deleteRoot() {
        if (this.left.isEmpty() && this.right.isEmpty()) {
            this.root = null;
            this.left = null;
            this.right = null;
        }
        else if (this.left.isEmpty()) {
            this.root = this.right.root;
            this.left = this.right.left;
            this.right = this.right.right;
        }
        else if (this.right.isEmpty()) {
            this.root = this.left.root;
            this.right = this.left.right;
            this.left = this.left.left;
        }
        else {
            this.root = this.left.extractMax();
        }
    }

    private Integer extractMax() {
        if (this.right.isEmpty()) {
            Integer maxValue = this.root;
            this.root = this.left.root;
            this.right = this.left.right;
            this.left = this.left.left;
            return maxValue;
        } else {
            return this.right.extractMax();
        }
    }

    public int height() {
        if (this.isEmpty()) {
            return 0;
        }
        return 1 + Math.max(this.left.height(), this.right.height());
    }

    public int count(Integer item) {
        if (this.isEmpty()) {
            return 0;
        }
        int currentCount = item.equals(this.root) ? 1 : 0;
        if (item.compareTo(this.root) <= 0) {
            return currentCount + this.left.count(item);
        } else {
            return currentCount + this.right.count(item);
        }
    }

    public int getLength() {
        if (this.isEmpty()) {
            return 0;
        }
        return 1 + this.left.getLength() + this.right.getLength();
    }

    public static void main(String[] args) {
        // Important: do not add any code to a "psvm" main method in this class,
        //            as it may prevent your code from running on MarkUs.
        //            If you want to test this code, you can either create a test file
        //            similar to the provided BSTSelfTest.java or create a main method
        //            in a local .java file which you don't push to MarkUs.
        //            In particular, if you include any code on MarkUs which calls:
        //            new Integer(x); // for any x
        //            then your code won't be able to be compiled and run, with an error
        //            similar to:
        //            "warning: [removal] Integer(int) in Integer has been deprecated and marked for removal"
    }

}
//test 2
