public class Crush{
    public static void main(String[] args){
    try {
        // Code that expresses your crush on someone
        System.out.println("I have a crush on you.");
    } catch (Exception e) {
    // Exception handling code when caught by your crush
    System.out.println("Oops! Caught in the act.");
        }
    }
}

/////////////////////////


class RejectionException extends Exception {
    RejectionException(String message) {
        super(message);
    }
}

class IndifferenceException extends Exception {
    IndifferenceException(String message) {
        super(message);
    }
}

class ConfusionException extends Exception {
    ConfusionException(String message) {
        super(message);
    }
}

public class CrushExpression {

    public static void main(String[] args) {
        try {
            System.out.println("I have a crush on you.");

            // Uncomment one of the following lines to simulate a specific response for your feelings to your crush.
            // throw new RejectionException("Sorry, I don't feel the same way.");
            // throw new IndifferenceException("Oh, that's nice.");
            // throw new ConfusionException("I'm not sure how I feel.");

        } catch (RejectionException e) {
            // Exception handling code for rejection
            System.out.println("Oh no! You rejected me. Reason: " + e.getMessage());
        } catch (IndifferenceException e) {
            // Exception handling code for indifference
            System.out.println("Hmm, seems like you're not interested. Comment: " + e.getMessage());
        } catch (ConfusionException e) {
            // Exception handling code for confusion
            System.out.println("I'm getting mixed signals from you. Comment: " + e.getMessage());
        } catch (Exception e) {
            // Generic exception handling code
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
