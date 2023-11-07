import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    // if statement that this is block 0 does sth diff
    int nonce = 1;
    int sizeOfNonce = 4;
    byte[] nonceByteArr;

    while (!this.hash.isValid()) {
      nonceByteArr = ByteBuffer.allocate(sizeOfNonce).putInt(nonce).array();
    }


  } // Block(int, int, Hash)

  public Block(int num, int amount, Hash prevHash, long nonce) {

  } // Block(int, int, Hash, long)

  public int getNum() {

  } // getNum()

  public int getAmount() {

  } // getAmount()

  public long getNonce() {

  } // getNonce()

  public Hash getPrevHash() {

  } // getPrevHash()

  public Hash getHash() {

  } // getHash()

  public String toString() {

  } // toString()

   
  public static byte[] calculateHash(int block, int amtTransferred, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    if(block == 0){
      md.update((Integer.toString(block)).getBytes()); // update w/ block num
      md.update((Integer.toString(amtTransferred)).getBytes()); // update w/ amtTransferred
      md.update((Long.toString(nonce)).getBytes()); // update w/ nonce
    }
    else{
    md.update((Integer.toString(block)).getBytes()); // update w/ block num
    md.update((Integer.toString(amtTransferred)).getBytes()); // update w/ amtTransferred
    md.update((prevHash.toString()).getBytes()); // update w/ prevHash
    md.update((Long.toString(nonce)).getBytes()); // update w/ nonce
    }
    byte[] hash = md.digest(); 
    return hash;
   // calculateHash(String)

} // class Block
}