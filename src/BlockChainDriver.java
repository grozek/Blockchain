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
      input = scan.nextLine();

      switch(input) {
        case "mine" :

          break;
        case "append":
          pen.printf("\n" + "Amount transferred? ");
          input = scan.next();
          int amtTransferred = Integer.valueOf(input);
          pen.printf("\n" + "Nonce? ");
          input = scan.next();
          int nonce = Integer.valueOf(input);

          //Block newBlock = new Block();

          
          break;
        case "remove" :

          break;
        case "check":

          break;
        case "report":

          break;
        case "help":

          break;
      }
    } while(input != "quit");

  } // main(String[])
} // class BlockChainDriver
