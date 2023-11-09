import java.nio.ByteBuffer;
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

  //the str+= assignment in the for loop did not work, the byte array doesnt either. string format is questionable
  public String toString() {
    byte[] tempArr = new byte[this.hash.length];
    for(int i=0; i<=this.hash.length-1; i++) {
      tempArr[i] += (Byte.toUnsignedInt(this.hash[i]));
    }
    // int tem = 456;
    //int tempInt = Integer.valueOf(tempArr);
    // byte[] tempArr = new byte[this.hash.length];
    // for(int i=0; i<=this.hash.length-1; i++) {
    //    tempArr[i] += (Byte.toUnsignedInt(this.hash[i]));
    //  }
    //  int i = ByteBuffer.wrap(this.hash).getInt();
      return String.format("%020x", tempArr); 
  } // toString()

  public boolean equals(Object other) {
    if (other instanceof Hash) { // might need to change idk
      Hash obj = (Hash) other;
      return Arrays.equals(this.hash, obj.getData());
    }
    return false;
  } // equals(Object)

} // class Hash

