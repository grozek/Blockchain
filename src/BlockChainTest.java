import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;



public class BlockChainTest {
  
  @Test
  public void simpleTest (){
  String startingAmt = "300";
  BlockChain aBlockChain = new BlockChain(Integer.valueOf(startingAmt));
  Block tempBlock = aBlockChain.mine(Integer.valueOf(-150));
  assertEquals(tempBlock.block, 0);
  aBlockChain.toString();

  long nonce = 2016357;
  Block newBlk = new Block(1, -150, null, nonce);
  System.out.println(newBlk.toString());


}
}
