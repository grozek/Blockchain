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
}
}
