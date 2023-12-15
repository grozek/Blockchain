import java.util.Arrays;

/**
 * A class to represent the cryptographic hash of blocks in blockchains.
 * 
 * @author Gabriela Roznawska
 * @author Wenfei Lin
 */
public class Hash {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  byte[] hash;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new Hash object given the hash
   * 
   * @param data the hash
   */
  public Hash(byte[] data) {
    this.hash = data;
  } // Hash(byte[])

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Retrieves the hash of the object
   *
   * @return the hash of the object
   */
  public byte[] getData() {
    return this.hash;
  } // getData()

  /**
   * Checks if the hash of the object contains three zeroes in the front
   *
   * @return true if the hash has three zeroes in the front; false if the 
   *         hash does not have three zeroes in the front
   */
  public boolean isValid() {
    byte[] arrOf0s = {0, 0, 0};
    return Arrays.equals(arrOf0s, 0, 3, this.hash, 0, 3);
  } // isValid()

  /**
   * Converts the hash to a string
   *
   * @return stringified version of the hash
   */
  public String toString() {
    String str = "";

    for (int i = 0; i <= this.hash.length - 1; i++) {
      str += String.format("%02x", (Byte.toUnsignedInt(this.hash[i])));
    } // for

    return str;
  } // toString()

  /**
   * Checks if the hash of the object is structurally equal to the 
   * hash of another object
   *
   * @param other another Hash object
   * @return true if the two hashes are the same; false if the two hashes 
   *         are not the same
   */
  public boolean equals(Object other) {
    if (other instanceof Hash) {
      Hash obj = (Hash) other;
      return Arrays.equals(this.hash, obj.getData());
    } // if

    return false;
  } // equals(Object)
} // class Hash