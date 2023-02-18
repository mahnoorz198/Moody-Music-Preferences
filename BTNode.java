class BTNode {
    BTNode left, right;
    int data;
    String songTrack;

    public BTNode(int n,String songTrack) {
        left = null;
        right = null;
        this.data = n;
        this.songTrack = songTrack;
    }

    /* Constructor */
    public BTNode(int n) {
        left = null;
        right = null;
        data = n;
    }

    /* Function to set left node */
    public void setLeft(BTNode n)
    {
        left = n;
    }
    /* Function to set right node */
    public void setRight(BTNode n)
    {
        right = n;
    }
    /* Function to get left node */
    public BTNode getLeft()
    {
        return left;
    }
    /* Function to get right node */
    public BTNode getRight()
    {
        return right;
    }
    /* Function to set data to node */
    public void setData(int d)
    {
        data = d;
    }
    /* Function to get data from node */
    public int getData()
    {
        return data;
    }
    public String getSongTrack(){
        return songTrack;
    }
}
