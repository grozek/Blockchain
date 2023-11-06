import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
  int block;
  int amtTransferred;
  Hash prevHash;
  Long nonce;
  Hash hash;
  
  public Block(int num, int amount, Hash prevHash) {
    this.block = num;
    this.amtTransferred = amount;
    this.prevHash = prevHash;


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

   
  public static byte[] calculateHash(String msg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("sha-256");
    md.update(msg.getBytes());
    byte[] hash = md.digest();
    return hash;
  } // calculateHash(String)

} // class Block
