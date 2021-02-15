package sample;

public class Password {

    // instance variavles
    private Boolean wantUpperCase;
    private Boolean wantSymbols;
    private Boolean wantNumbers;
    private Boolean checkLowerCase;
    private Boolean checkUpperCase;
    private Boolean checkSymbols;
    private Boolean checkNumbers;
    private int passwordLength;

    /**
     * constructor
     * @param wantUpperCase
     * @param wantSymbols
     * @param wantNumbers
     * @param passwordLength
     */
    public Password(Boolean wantUpperCase, Boolean wantSymbols, Boolean wantNumbers, int passwordLength) {
        checkLowerCase = true;
        this.wantUpperCase = wantUpperCase;
        checkUpperCase = wantUpperCase;
        this.wantSymbols = wantSymbols;
        checkSymbols = wantSymbols;
        this.wantNumbers = wantNumbers;
        checkNumbers = wantNumbers;
        this.passwordLength = passwordLength;
    }

    /**
     * @return created password
     */
    public String returnPassword() {

        String password = "";
        int randInt = 0;

        String[] lowercase = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] uppercase = {"A", "B", "C", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] symbols = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "=", "_", "+", "<", ">", "/", "?", ";", ".", "[", "]", "{", "}", ":", ";"};
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

        while (checkLowerCase || checkUpperCase || checkSymbols || checkNumbers) {
            password = "";
            for (int i=0; i < passwordLength;) {
                randInt = (int) (Math.random() * 4);
                if (randInt == 0) {
                    if (wantUpperCase) {
                        password += uppercase[(int) (Math.random() * 25)];
                        i++;
                        checkUpperCase = false;
                    }
                } else if (randInt == 1) {
                    if (wantSymbols) {
                        password += symbols[(int) (Math.random() * 25)];
                        i++;
                        checkSymbols = false;
                    }
                } else if (randInt == 2){
                    if (wantNumbers) {
                        password += numbers[(int) (Math.random() * 9)];
                        i++;
                        checkNumbers = false;
                    }
                } else {
                    password += lowercase[(int) (Math.random() * 25)];
                    i++;
                    checkLowerCase = false;
                }
            }
        }
        return password;
    }
}