public class Main {

    public static void main(String[] args) {
        int counter = 0;

        // write your code here
        for (Secret secret : Secret.values()) {
            if(secret.toString().substring(0, 4).equals("STAR")) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}


/*enum Secret {
    STAR, CRASH, START, // ...
}*/
