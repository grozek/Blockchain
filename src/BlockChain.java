import java.io.PrintWriter;

/**
 * A singly-linked structure that represents a blockchain made of blocks.
 * 
 * @author Gabriela Roznawska, Wenfei Lin
 */
public class BlockChain {
  Node first;
  Node last;

  /**
   * Creates a blockchain that possess a single block that starts with the given initial amount.
   * 
   * @param initial the initial transaction between Alexis and Blake
   */
  public BlockChain(int initial) {
    this.first = new Node(new Block(0, initial, null), null);
    this.last = this.first;
  } // BlockChain(int)

  /**
   * Mines a new candidate block to be added to the end of the chain.
   * 
   * @param amount the amount transferred between Alexis and Blake
   * @return a valid block that can be added to the blockchain with updated information
   */ 
  public Block mine(int amount) {
    Block tempBlock = this.last.data;
    Block candidateBlock = new Block(tempBlock.block + 1, amount, tempBlock.hash);

    return candidateBlock;
  } // mine(int)

  /**
   * Returns the size of the blockchain.
   * 
   * @return the number of blocks in the blockchain 
   */
  public int getSize() {
    return this.last.data.block + 1;
  } // getSize()

  /**
   * Adds this block to the list.
   * 
   * @param blk a block
   * @throws IllegalArgumentException if block (invalid) can't be added to the chain
   */
  public void append(Block blk) throws IllegalArgumentException {
    Node tempNode = new Node(blk, null);

    if ((blk.block == this.last.data.block + 1) && (blk.prevHash.equals(this.last.data.hash))
        && (blk.hash.isValid())) {
      this.last.next = tempNode; 
      this.last = tempNode; 
    } else {
      throw new IllegalArgumentException();
    }
  } // append(Block)

  /**
   * Removes the last block from the chain if there is more than one block in the chain.
   * 
   * @return true if successfully removes the last block from the chain;
   *         false if the chain only contains a single block (also does nothing)
   */
  public boolean removeLast() {
    if (this.first == this.last) {
      return false;
    } else {
      int prevBlockNum = this.last.data.block - 1;

      Node pointer = this.first;
      for (int i = 0; i < this.getSize(); i++) {
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

  /**
   * Returns the hash of the last block in the chain.
   * 
   * @return
   */
  public Hash getHash() {
    return this.last.data.hash;
  } // getHash()

  /**
   * Traverses the blockchain and ensures that its blocks are consistent and valid.
   * @return true if the blockchain contains valid transactions;
   *         false if the blockchain contains invalid transactions (if anyone's balance
   *         is negative)
   */
  public boolean isValidBlockChain() {
    Node pointer = this.first;
    int transactionValue = 0;

    do {
      transactionValue += pointer.data.amtTransferred;
      pointer = pointer.next;
    } while (pointer != null);
    {
      if (transactionValue <= 0) {
        return false;
      }
    }
    return true;
  } // isValidBlockChain()

  /**
   * Calculates and prints the balances of Alexis and Blake.
   */
  public void printBalances() {
    PrintWriter pen = new PrintWriter(System.out, true);
    int amtAlexis = 0;
    int amtBlake = 0;
    Node pointer = this.first;

    for (int i = 0; i < this.getSize(); i++) {
      if (pointer.data.amtTransferred < 0) {
        amtBlake -= pointer.data.amtTransferred;
        amtAlexis = amtAlexis + pointer.data.amtTransferred;
      } else {
        amtAlexis += pointer.data.amtTransferred;
        amtBlake = amtBlake - pointer.data.amtTransferred;
      }
      pointer = pointer.next;
    }
    pen.println("Alexis: " + amtAlexis + ", " + "Blake: " + amtBlake);
  } // printBalances()

  /**
   * Stringifies the whole blockchain.
   */
  public String toString() {
    String blockchainString = "";
    Node pointer = this.first;

    for (int i = 0; i < this.getSize(); i++) {
      blockchainString += pointer.data.toString() + "\n";
      pointer = pointer.next;
    }
    return blockchainString;
  } // toString()

  /**
   * A class to represent a node that contains data and a pointer.
   */
  static class Node {
    Block data;
    Node next;

    /**
     * Creates a node that contains the block, which can then be connected to 
     * a blockchain.
     * 
     * @param data a block
     * @param next pointer
     */
    public Node(Block data, Node next) {
      this.data = data;
      this.next = next;
    } // Node (Block, Node)
  } // class Node
} // class BlockChain
