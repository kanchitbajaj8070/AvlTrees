public class AvlTrees {
    class Node {
        int data;
        Node left;
        Node right;
 int height;
        Node(int data) {
            this.data = data;

        this.height=1;}
    }

    public Node root;

    public int height(Node nn) {
        if (nn == null)
            return 0;
        else
            return (1 + Math.max(height(nn.left), height(nn.right)));
    }


    public void insert(int item) {
        root = insert(root, item);
    }
    Node rightRotate(Node c)
    {
        Node b=c.left;
        Node t3= b.right;
        b.right=c;
        c.left=t3;
        c.height=Math.max(height(c.left),height(c.right))+1;
        b.height=Math.max(height(b.left),height(b.right))+1;
        return b;
    }
    Node leftRotate(Node c)
    {
        Node b=c.right;
        Node t3= b.left;
        b.left=c;
        c.right=t3;
        c.height=Math.max(height(c.left),height(c.right))+1;
        b.height=Math.max(height(b.left),height(b.right))+1;
        return b;
    }
    int balanceFactor(Node node)
    {
        if(node==null)
            return 0;
        return height(node.left)-height(node.right);
    }

    private Node insert(Node root, int item) {

        if (root == null) {
            return new Node(item);
        }
        if (item < root.data) {
            root.left = insert(root.left, item);

        }
        if (item > root.data) {
            root.right = insert(root.right, item);

        }
        root.height=Math.max(height(root.left),height(root.right))+1;
       int bf=balanceFactor(root);
       if(bf>1&&root.left!=null&&item<root.left.data)//LL
       return rightRotate(root);
       if(bf<-1&&root.right!=null&&item>root.right.data)//rr
           return leftRotate(root);
       if(bf>1&&root.left!=null&&root.left!=null&&item>root.left.data) {//lr
            root.left=leftRotate(root.left);
           return rightRotate(root);
       }
if(bf<-1&&root.right!=null&&item<root.right.data)//Rl
{
    root.right=rightRotate(root.right);
    return leftRotate(root);//rl
}
 return root;
    }
    public void preorder(Node nn)
    {
        if(nn==null)
            return;
        System.out.print(nn.data+"  ");
        preorder(nn.left);

    preorder(nn.right);}
    public void remove(int key)
    {
        remove(root,null,false,key);


    }
    private void remove(Node node,Node parent,boolean isLeftChild,int key) {
        if (node == null) {
            System.out.println("Not present");
            return;
        }
        if (key > node.data) {
            remove(node.right, node, false, key);
        } else if (key < node.data) {
            remove(node.left, node, true, key);
        } else {
            if (node.left == null && node.right == null) {
                if (isLeftChild)
                    parent.left = null;
                else
                    parent.right = null;
            } else if (node.left != null && node.right == null) {
                if (isLeftChild)
                    parent.left = node.left;
                else
                    parent.right = node.left;
            } else if (node.right != null && node.left == null) {
                if (isLeftChild) {
                    parent.left = node.right;
                } else {
                    parent.right = node.right;
                }
            } else {
                System.out.println("in case 4");
                int maximumOnleft = maximum(node.left);
                System.out.println("max on lrft is " + maximumOnleft);
                node.data = maximumOnleft;
                remove(node.left, node, true, maximumOnleft);//remove predecessor of to be removed node
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int bf = balanceFactor(node);
        if (bf > 1 && node.left != null && key< node.left.data)//LL
            rightRotate(node);
        if (bf < -1 && node.right != null &&key > node.right.data)//rr
            leftRotate(node);
        if (bf > 1 && node.left != null && node.left != null && key > node.left.data) {//lr
            node.left = leftRotate(node.left);
            rightRotate(node);
        }
    }
    public int maximum(Node node)
    {      if(node.right==null)
        return node.data;
        return maximum(node.right);

    }
}
