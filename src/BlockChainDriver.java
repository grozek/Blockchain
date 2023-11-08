import java.io.PrintWriter;
import java.util.Scanner;

public class BlockChainDriver {
  public static void main(String[] args) {
    String startingAmt = args[1];
    BlockChain aBlockChain = new BlockChain(Integer.valueOf(startingAmt));
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scan = new Scanner(System.in);
    String input = "";

    do {
      pen.printf(aBlockChain.toString() + "\n"); 
      pen.printf("Command? ");
      input = scan.next();

      switch(input) {
        case "mine" :
          pen.printf("\n" + "Amount transferred? ");
          input = scan.next();
          Block tempBlock = aBlockChain.mine(Integer.valueOf(input));
          pen.printf("\n" + "amount = " + input + ", " + "nonce = " + tempBlock.nonce);
          break;

        case "append":
          pen.printf("\n" + "Amount transferred? ");
          input = scan.next();
          int amtTransferred = Integer.valueOf(input);
          pen.printf("\n" + "Nonce? ");
          input = scan.next();
          int nonce = Integer.valueOf(input);
          Block newBlock = new Block (aBlockChain.last.data.block+1, amtTransferred, aBlockChain.last.data.hash, nonce);
          aBlockChain.append(newBlock);
          break;

        case "remove" :
          aBlockChain.removeLast();
          break;

        case "check":
          if (aBlockChain.isValidBlockChain()){
            pen.printf("\n" + "Chain is valid!");
          }else{
            pen.printf("\n" + "Chain is invalid!");
          }
          break;

        case "report":
          pen.printf("\n");
          aBlockChain.printBalances();
          break;

        case "help":
          pen.printf("\n" + "Valid Commands" + "\n" +     
                    " \t mine: discovers the nonce for a given transaction" +
                    " \t append: appends a new block onto the end of the chain" +
                    " \t remove: removes the last block from the end of the chain" +
                    " \t check: checks that the block chain is valid" +
                    " \t report: reports the balances of Alexis and Blake" +
                    " \t help: prints this list of commands" +
                    " \t quit: quits the program" + "\n");
          break;
      }
    } while(input != "quit");

  } // main(String[])
} // class BlockChainDriver
