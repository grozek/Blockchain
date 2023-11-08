import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class Block {
  int block;
  int amtTransferred;
  Hash prevHash;
  long nonce;
  Hash hash;
  
  public Block(int num, int amount, Hash prevHash) {
    this.block = num;
    this.amtTransferred = amount;
    this.prevHash = prevHash;

    int nonce = 1;
    int sizeOfNonce = 4;
    byte[] nonceByteArr;
    Hash tempHash;
    Random rand = new Random();
    do {
      nonceByteArr = ByteBuffer.allocate(sizeOfNonce).putInt(nonce).array();
      nonce = rand.nextInt();
      try {
        tempHash = new Hash(calculateHash(this.block, this.amtTransferred, this.prevHash, nonceByteArr));
        this.hash = tempHash;
      } catch (NoSuchAlgorithmException e) { // This will won't happen though
        PrintWriter pen = new PrintWriter(System.out, true);
        pen.println("The algorithm for computing the hash is invalid.");
      }
    } while (!this.hash.isValid());
    this.nonce = nonce - 1;
  } // Block(int, int, Hash)

  public Block(int num, int amount, Hash prevHash, long nonce) {
    this.block = num;
    this.amtTransferred = amount;
    this.prevHash = prevHash;
    this.nonce = nonce;

    Hash tempHash;
    byte[] nonceByteArr;

    try {
      nonceByteArr = Long.toString(nonce).getBytes();
      tempHash = new Hash(calculateHash(this.block, this.amtTransferred, this.prevHash, nonceByteArr));
      this.hash = tempHash;
    } catch (NoSuchAlgorithmException e) { // This will won't happen though
      PrintWriter pen = new PrintWriter(System.out, true);
      pen.println("The algorithm for computing the hash is invalid.");
    }
    
  } // Block(int, int, Hash, long)

  public int getNum() {
    return this.block;
  } // getNum()

  public int getAmount() {
    return this.amtTransferred;
  } // getAmount()

  public long getNonce() {
    return this.nonce;
  } // getNonce()

  public Hash getPrevHash() {
    return this.prevHash;
  } // getPrevHash()

  public Hash getHash() {
    return this.hash;
  } // getHash()

  public String toString() {
    return "Block " + this.block + " (Amount: " + this.amtTransferred + ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash: " + this.hash + ")";
  } // toString()

   
  public static byte[] calculateHash(int block, int amtTransferred, Hash prevHash, byte[] nonce) throws NoSuchAlgorithmException {
    String algorithm = "sha-256";
    MessageDigest md = MessageDigest.getInstance(algorithm);
    if(block == 0){
      md.update((Integer.toString(block)).getBytes()); // update w/ block num
      md.update((Integer.toString(amtTransferred)).getBytes()); // update w/ amtTransferred
      md.update(nonce); // update w/ nonce

    }
    else{
    md.update((Integer.toString(block)).getBytes()); // update w/ block num
    md.update((Integer.toString(amtTransferred)).getBytes()); // update w/ amtTransferred
    md.update((prevHash.toString()).getBytes()); // update w/ prevHash
    md.update(nonce); // update w/ nonce
    }
    byte[] hash = md.digest(); 
    return hash;
  } // calculateHash(String)
}// class Block