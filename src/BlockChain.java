import java.io.PrintWriter;

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
    return this.last.data.block + 1;
  } // getSize()

  public void append(Block blk) throws IllegalArgumentException{
    Node tempNode = new Node (blk, null);
    Node pointer = this.first;
    int transactionValue = 0;

    for(int i=0; i < this.getSize()-1; i++) {
      transactionValue += pointer.data.amtTransferred;
      pointer = pointer.next;
    }

    if ((blk.block == this.last.data.block + 1) 
        && (blk.prevHash == this.last.data.prevHash) 
        && (blk.hash.isValid())
        && (transactionValue + blk.amtTransferred >= 0)){
      this.last.next = tempNode; // connects new block to the end of the chain
      this.last = tempNode; // moves the last pointer
    } else {
      throw new IllegalArgumentException();
    } 
  } // append(Block)

  public boolean removeLast() {
    if (this.first == this.last) {
      return false;
    } else {
      int prevBlockNum = this.last.data.block - 1;

      Node pointer = this.first;
      for(int i=0; i < this.getSize()-1; i++) {
        if (pointer.data.block == prevBlockNum) {
          pointer.next = null;
          this.last = pointer;
          break;
        }
        pointer = pointer.next;
      }
    }
    return true;
  } // removeLast()

  public Hash getHash() {
    return this.last.data.hash;

  } // getHash()

  public boolean isValidBlockChain() {
    Node pointer = this.first;
    Node nextPointer = this.first.next;
    int transactionValue = 0;

    for(int i=0; i < this.getSize()-1; i++) {
      if (!((pointer.data.block ==  nextPointer.data.block - 1) 
        && (pointer.data.prevHash == this.last.data.prevHash) 
        && (pointer.data.hash.isValid())
        && (transactionValue + pointer.data.amtTransferred >= 0))){
          return false;
        }
        if(i == pointer.data.block-1){
          pointer = pointer.next;
          nextPointer = null;
        }else{
        pointer = pointer.next;
        nextPointer = nextPointer.next;
        }
    }
    return true;
   }// isValidBlockChain()

  public void printBalances() {
    PrintWriter pen = new PrintWriter(System.out, true);
    int amtAlexis = 0;
    int amtBlake = 0;
    Node pointer = this.first;
    for(int i=0; i < this.getSize()-1; i++) {
      if (pointer.data.amtTransferred < 0){
        amtBlake -= pointer.data.amtTransferred; 
        amtAlexis = amtAlexis + pointer.data.amtTransferred; 
      }else{
        amtAlexis += pointer.data.amtTransferred;
        amtBlake = amtBlake - pointer.data.amtTransferred;
      }
      pointer = pointer.next;
    }
    pen.println("Alexis: " +  amtAlexis + ", " + "Blake: " + amtBlake + ".");
  } // printBalances()

  public String toString() {
    String blockchainString = "";
    Node pointer = this.first;
    for(int i=0; i < this.getSize()-1; i++) {
      blockchainString += pointer.data.toString() + "\n";
      pointer = pointer.next;
    }
    return blockchainString;
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
