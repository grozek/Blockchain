import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class to allow for interaction with a blockchain.
 * 
 * @author Gabriela Roznawska
 * @author Wenfei Lin
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
          mineNonce(input, pen, scan, aBlockChain);
          break;

        case "append":
          appendNewBlock(input, pen, scan, aBlockChain);
          break;

        case "remove":
          aBlockChain.removeLast();
          break;

        case "check":
          checkBlockChain(pen, aBlockChain);
          break;

        case "report":
          aBlockChain.printBalances();
          pen.printf("\n");
          break;

        case "help":
          printMenu(pen);
          break;
      } // switch
    } // while
    scan.close();
  } // main(String[])

  /**
   * Mines and prints the nonce for a potential new block to be added after
   * prompting user for the amount transferred for the block being mined.
   * 
   * @param input input from user
   * @param pen for printing
   * @param scan for scanning the next input by the user
   * @param aBlockChain a block chain
   */
  public static void mineNonce(String input, PrintWriter pen, 
      Scanner scan, BlockChain aBlockChain) {
    pen.printf("Amount transferred? ");
    input = scan.next();

    Block tempBlock = aBlockChain.mine(Integer.valueOf(input));
    pen.printf("amount = " + input + ", " + "nonce = " + tempBlock.nonce + 
        "\n" + "\n");
  } // mineNonce(String, PrintWriter, Scanner, BlockChain)

  /**
   * Adds a new block onto the end of the blockchain and prompts user
   * for input on transaction amount and nonce of the new block.
   * 
   * @param input input from user
   * @param pen for printing
   * @param scan for scanning the next input by the user
   * @param aBlockChain a block chain
   */
  public static void appendNewBlock(String input, PrintWriter pen, 
      Scanner scan, BlockChain aBlockChain) {
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
  } // appendNewBlock(String, PrintWriter, Scanner, BlockChain)

  /**
   * Determines if the block chain contains valid transactions
   * and prints the result of that verification.
   * 
   * @param pen for printing
   * @param aBlockChain a block chain
   */
  public static void checkBlockChain(PrintWriter pen, BlockChain aBlockChain) {
    if (aBlockChain.isValidBlockChain()) {
      pen.printf("Chain is valid!" + "\n" + "\n");
    } else {
      pen.printf("Chain is invalid!" + "\n" + "\n");
    } // if/else
  } // checkBlockChain(PrintWriter, BlockChain)

  /**
   * Prints a menu of all the possible prompts a user can type for block 
   * chain operations.
   * 
   * @param pen for printing
   */
  private static void printMenu(PrintWriter pen) {
    pen.println("Valid commands:");
    pen.println("    mine: discovers the nonce for a given transaction");
    pen.println("    append: appends a new block onto the end of the chain");
    pen.println("    check: checks that the block chain is valid");
    pen.println("    report: reports the balances of Alexis and Blake");
    pen.println("    help: prints this list of commands");
    pen.println("    quit: quits the program");
    pen.println();
  } // printMenu(PrintWriter)
} // class BlockChainDriver