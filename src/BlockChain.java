public class BlockChain {
  Node first;
  Node last;

  public BlockChain(int initial) {
    this.first.data = new Block(0, initial, null);
    this.first.next = null;
    this.last = this.first;
  // int block;
  // int amtTransferred;
  // Hash prevHash;
  // long nonce;
  // Hash hash;
  } // BlockChain(int)

  public Block mine(int amount) {
    Block tempBlock = this.last.data;

    this.last.next = this.last;
    this.last.data = new Block(tempBlock.block + 1, amount, tempBlock.prevHash);

    return this.last.data;
  } // mine(int)

  public int getSize() {

  } // getSize()

  public void append(Block blk) {

  } // append(Block)

  public boolean removeLast() {

  } // removeLast()

  public Hash getHash() {

  } // getHash()

  public boolean isValidBlockChain() {

  } // isValidBlockChain()

  public void printBalances() {

  } // printBalances()

  public String toString() {

  } // toString()

  static class Node {
    Block data;
    Node next;

    public Node(Block data, Node next) {
      this.data = data;
      this.next = next;
    } // Node (Block, Node)
  } // class Node
} // class BlockChain
