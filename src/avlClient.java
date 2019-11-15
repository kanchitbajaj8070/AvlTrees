public class avlClient {
    public static void main(String[] args) {
        AvlTrees tree= new AvlTrees();
        for (int i = 1; i <= 10; i++) {
            tree.insert(i);
        }
        tree.preorder(tree.root);
        System.out.println();
        tree.remove(1);
        tree.preorder(tree.root);
    }
}
