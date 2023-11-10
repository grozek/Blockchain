import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class to allow for interaction with a blockchain.
 * 
 * @author Gabriela Roznawska, Wenfei Lin
 */
public class BlockChainDriver {
  public static void main(String[] args) {
    String startingAmt = args[0];
    BlockChain aBlockChain = new BlockChain(Integer.valueOf(startingAmt));
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scan = new Scanner(System.in);
    String input = "";

    while (!input.equals("quit")) {
      pen.printf(aBlockChain.toString() + "\n");
      pen.printf("Command? ");
      input = scan.next();

      switch (input) {
        case "mine":
          pen.printf("Amount transferred? ");
          input = scan.next();
          Block tempBlock = aBlockChain.mine(Integer.valueOf(input));
          pen.printf("amount = " + input + ", " + "nonce = " + tempBlock.nonce + "\n" + "\n");
          break;

        case "append":
          pen.printf("Amount transferred? ");
          input = scan.next();
          int amtTransferred = Integer.valueOf(input);
          pen.printf("Nonce? ");
          input = scan.next();
          pen.printf("\n");
          Long nonce = Long.valueOf(input);
          Block newBlock = new Block(aBlockChain.last.data.block + 1, amtTransferred,
              aBlockChain.last.data.hash, nonce);
          aBlockChain.append(newBlock);
          break;

        case "remove":
          aBlockChain.removeLast();
          break;

        case "check":
          if (aBlockChain.isValidBlockChain()) {
            pen.printf("Chain is valid!" + "\n" + "\n");
          } else {
            pen.printf("Chain is invalid!" + "\n" + "\n");
          }
          break;

        case "report":
          aBlockChain.printBalances();
          pen.printf("\n");
          break;

        case "help":
          pen.printf(
              "Valid commands:" + "\n" + "    mine: discovers the nonce for a given transaction\n"
                  + "    append: appends a new block onto the end of the chain\n"
                  + "    remove: removes the last block from the end of the chain\n"
                  + "    check: checks that the block chain is valid\n"
                  + "    report: reports the balances of Alexis and Blake\n"
                  + "    help: prints this list of commands\n" + "    quit: quits the program"
                  + "\n" + "\n");
          break;
      }
    }

    scan.close();
  } // main(String[])
} // class BlockChainDriver