import java.util.Arrays;

public class Hash {
  byte[] hash;

  
  public Hash(byte[] data) {
    this.hash = data;
  } // Hash(byte[])

  public byte[] getData() {
    return this.hash;
  } // getData()

  public boolean isValid() {
    byte[] arrOf0s = {0, 0, 0};
    return Arrays.equals(arrOf0s, 0, 3, this.hash, 0, 3);
  } // isValid()

  public String toString() {
    String str = "";
    for(int i=0; i<=this.hash.length-1; i++) {
      str += (Byte.toUnsignedInt(this.hash[i]));
    }
    return String.format("%.2x", str);
  } // toString()

  public boolean equals(Object other) {
    if (other instanceof Hash) { // might need to change idk
      Hash obj = (Hash) other;
      return Arrays.equals(this.hash, obj.getData());
    }
    return false;
  } // equals(Object)

} // class Hash

