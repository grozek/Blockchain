import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * A class for the data contained in each node of the blockchain.
 * 
 * @author Gabriela Roznawska, Wenfei Lin
 */
public class Block {

  int block;
  int amtTransferred;
  Hash prevHash;
  long nonce;
  Hash hash;

  /**
   * Creates a new Block from the given block number, amount transferred, and the hash of the
   * previous block in the blockchain by mining for a nonce and generating the new hash, using the
   * same inputs.
   * 
   * @param num the number of the block
   * @param amount the amount transferred between Alexis to Blake
   * @param prevHash the hash of the previous block in the blockchain
   */
  public Block(int num, int amount, Hash prevHash) {
    this.block = num;
    this.amtTransferred = amount;
    this.prevHash = prevHash;

    Long nonce = (long) 1;
    int sizeOfNonce = 8;
    byte[] nonceByteArr;
    Hash tempHash;
    Random rand = new Random();

    do {
      nonce = rand.nextLong();
      nonceByteArr = ByteBuffer.allocate(sizeOfNonce).putLong(nonce).array();

      try {
        tempHash =
            new Hash(calculateHash(this.block, this.amtTransferred, this.prevHash, nonceByteArr));
        this.hash = tempHash;
      } catch (NoSuchAlgorithmException e) { // This won't happen though
        PrintWriter pen = new PrintWriter(System.out, true);
        pen.println("The algorithm for computing the hash is invalid.");
      }
    } while (!this.hash.isValid());
    this.nonce = nonce;
  } // Block(int, int, Hash)

  /**
   * Creates a new Block from the given block number, amount transferred, the hash of the previous
   * block in the blockchain, and the nonce by generating the new hash, using the same inputs.
   * 
   * @param num the number of the block
   * @param amount the amount transferred between Alexis and Blake
   * @param prevHash the hash of the previous block in the blockchain
   * @param nonce the nonce of the block
   */
  public Block(int num, int amount, Hash prevHash, long nonce) {
    this.block = num;
    this.amtTransferred = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;
    int sizeOfNonce = 8;
    Hash tempHash;
    byte[] nonceByteArr;

    do {
      try {
        nonceByteArr = ByteBuffer.allocate(sizeOfNonce).putLong(nonce).array();
        tempHash =
            new Hash(calculateHash(this.block, this.amtTransferred, this.prevHash, nonceByteArr));
        this.hash = tempHash;
      } catch (NoSuchAlgorithmException e) { // This will won't happen though
        PrintWriter pen = new PrintWriter(System.out, true);
        pen.println("The algorithm for computing the hash is invalid.");
      }
    } while (!this.hash.isValid());
  } // Block(int, int, Hash, long)

  /**
   * Returns the number of the block.
   * 
   * @return the number of the block
   */
  public int getNum() {
    return this.block;
  } // getNum()

  /**
   * Returns the amount transferred between Alexis and Blake.
   * 
   * @return the amount transferred between Alexis and Blake
   */
  public int getAmount() {
    return this.amtTransferred;
  } // getAmount()

  /**
   * Returns the nonce of the block.
   * 
   * @return the nonce of the block
   */
  public long getNonce() {
    return this.nonce;
  } // getNonce()

  /**
   * Returns the hash of the previous block in the blockchain.
   * 
   * @return the hash of the previous block in the blockchain
   */
  public Hash getPrevHash() {
    return this.prevHash;
  } // getPrevHash()

  /**
   * Returns the hash of the block.
   * 
   * @return the hash of the block
   */
  public Hash getHash() {
    return this.hash;
  } // getHash()

  /**
   * Creates a string representation of the block (with information on the block number, the amount
   * transferred between Alexis and Blake, the nonce of the block, the hash of the previous block in the
   * blockchain, and the hash of the block).
   * 
   * @return string representing the block
   */
  public String toString() {
    return "Block " + this.block + " (Amount: " + this.amtTransferred + ", Nonce: " + this.nonce
        + ", prevHash: " + this.prevHash + ", hash: " + this.hash + ")";
  } // toString()

  /**
   * Calculates the hash by taking into account the block number, amount transferred between 
   * Alexis and Blake, the hash of the previous block in the blockchain, and the nonce of the 
   * block.
   * 
   * @param block the block number
   * @param amtTransferred the amount transferred between Alexis and Blake
   * @param prevHash the hash of the previous block in the blockchain
   * @param nonce the nonce of the block
   * @return the calculated hash
   * @throws NoSuchAlgorithmException
   */
  public static byte[] calculateHash(int block, int amtTransferred, Hash prevHash, byte[] nonce)
      throws NoSuchAlgorithmException {
    String algorithm = "sha-256";
    MessageDigest md = MessageDigest.getInstance(algorithm);

    if (block == 0) {
      md.update(ByteBuffer.allocate(8).putInt(block).array());
      md.update(ByteBuffer.allocate(8).putInt(amtTransferred).array());
      md.update(nonce);

    } else {
      md.update(ByteBuffer.allocate(8).putInt(block).array());
      md.update(ByteBuffer.allocate(8).putInt(amtTransferred).array());
      md.update(prevHash.getData());
      md.update(nonce);
    }

    byte[] hash = md.digest();
    return hash;
  } // calculateHash(String)
} // class Block